package finalProject.NoPainNoGain.service.impl;


import finalProject.NoPainNoGain.core.dto.user.SaveUserDto;
import finalProject.NoPainNoGain.core.dto.user.UserDto;
import finalProject.NoPainNoGain.core.dto.PageDto;
import finalProject.NoPainNoGain.core.exception.SingleErrorResponse;
import finalProject.NoPainNoGain.entity.UserEntity;
import finalProject.NoPainNoGain.repository.UserRepository;
import finalProject.NoPainNoGain.service.api.IUsersService;
import finalProject.NoPainNoGain.util.Utils;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.*;

@Service
public class UsersServiceImpl implements IUsersService {

    private final UserRepository userRepository;

    private final ConversionService conversionService;

    public UsersServiceImpl(UserRepository userRepository, ConversionService conversionService) {
        this.userRepository = userRepository;
        this.conversionService = conversionService;
    }

    @Override
    public void createUser(@Validated UserDto userDTO) {
        userRepository.save(Objects.requireNonNull(conversionService.convert(userDTO, UserEntity.class)));
    }

    @Override
    public SaveUserDto getUser(UUID id) {
        SaveUserDto saveUserDto = userRepository.existsById(id) ? conversionService.convert(userRepository.findById(id), SaveUserDto.class) : null;
        if (saveUserDto != null) {
            return saveUserDto;
        } else {
            throw new SingleErrorResponse("Нет пользавателя с таким id");
        }
    }

    @Override
    public void updateUser(UUID id, long dtUpdate, UserDto userDTO) {
        UserEntity entity = userRepository.findById(id).orElseThrow(() ->
                new SingleErrorResponse("Нет пользователя для обновления с таким id"));
        if (Objects.equals(Utils.convertLocalDateTimeToLong(entity.getDtUpdate()), dtUpdate)) {
            userRepository.save(Objects.requireNonNull(conversionService.convert(userDTO, UserEntity.class)));
        } else {
            throw new SingleErrorResponse("Введена не верная версия");
        }
    }

    @Override
    public PageDto<SaveUserDto> getUsersPage(Pageable pageable) {
        Page<UserEntity> allPage = userRepository.findAllPage(pageable);
        List<SaveUserDto> content = new ArrayList<>();
        for (UserEntity user : allPage) {
            content.add(conversionService.convert(user, SaveUserDto.class));
        }
        return new PageDto<>(allPage.getNumber(), allPage.getSize(), allPage.getTotalPages(),
                allPage.getTotalElements(), allPage.isFirst(), allPage.getNumberOfElements(),
                allPage.isLast(), content);
    }
}
