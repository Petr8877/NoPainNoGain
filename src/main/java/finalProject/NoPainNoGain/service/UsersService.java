package finalProject.NoPainNoGain.service;


import finalProject.NoPainNoGain.core.dto.user.SaveUserDTO;
import finalProject.NoPainNoGain.core.dto.user.UserDTO;
import finalProject.NoPainNoGain.core.dto.PageDTO;
import finalProject.NoPainNoGain.core.exception.MultipleErrorResponse;
import finalProject.NoPainNoGain.core.exception.MyError;
import finalProject.NoPainNoGain.core.exception.SingleErrorResponse;
import finalProject.NoPainNoGain.entity.UserEntity;
import finalProject.NoPainNoGain.repository.UserRepo;
import finalProject.NoPainNoGain.service.api.IUsersService;
import finalProject.NoPainNoGain.usersEnum.RoleUser;
import finalProject.NoPainNoGain.usersEnum.Status;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Pattern;

@Service
public class UsersService implements IUsersService {

    private final UserRepo dao;

    public UsersService(UserRepo dao) {
        this.dao = dao;
    }

    @Override
    public void createUser(UserDTO userDTO) throws MultipleErrorResponse {
        check(userDTO);
        String fio = userDTO.getFio();
        String mail = userDTO.getMail();
        String password = userDTO.getPassword();
        UUID uuid = UUID.randomUUID();
        LocalDateTime dtCreate = LocalDateTime.now();
        RoleUser roleUser = userDTO.getRole();
        Status status = userDTO.getStatus();
        UserEntity userEntity = new UserEntity(uuid, mail, fio, password, dtCreate, dtCreate, roleUser, status);
        dao.save(userEntity);

    }

    @Override
    public PageDTO<SaveUserDTO> getUsersPage(Integer page, int size) {
        List<SaveUserDTO> usersPages = new ArrayList<>();
        List<UserEntity> userEntities = (List<UserEntity>) dao.findAll();
        long totalElements = userEntities.size();
        int totalPage = (int) Math.floor((double) totalElements / size);
        boolean first = false;
        boolean last = false;
        long numberOfElements = size;
        if (page == 0) {
            first = true;
        }
        if (page == totalPage) {
            last = true;
        }
        if (last) {
            numberOfElements = totalElements - ((long) size * page);
        }
        if (page > totalPage || page < 0) {
            throw new SingleErrorResponse("Страницы № " + page + " не существует. Последняя страница № " + totalPage);
        }
        for (int i = size * page; i < userEntities.size(); i++) {
            UserEntity user = userEntities.get(i);
            usersPages.add(new SaveUserDTO(user.getUuid(), user.getDtCreate(), user.getDtUpdate(),
                    user.getMail(), user.getFio(), user.getRole(), user.getStatus()));
        }
        return new PageDTO<>(page, size, totalPage, totalElements, first, numberOfElements, last, usersPages);
    }

    @Override
    public SaveUserDTO getUser(UUID id) {
        if (dao.existsById(id)) {
            UserEntity user = dao.findById(id).get();
            return new SaveUserDTO(user.getUuid(), user.getDtCreate(), user.getDtUpdate(), user.getMail(), user.getFio(), user.getRole(), user.getStatus());
        } else {
            throw new SingleErrorResponse("Нет пользавателя для обновления с таким id");
        }
    }

    @Override
    public void updateUser(UUID id, long dtUpdate, UserDTO userDTO) {
        if (dao.existsById(id)) {
            UserEntity entity = dao.findById(id).get();
            if (Objects.equals(entity.getDtUpdate(), dtUpdate)) {
                entity.setFio(userDTO.getFio());
                entity.setMail(userDTO.getMail());
                entity.setPassword(userDTO.getPassword());
                entity.setRole(userDTO.getRole());
                entity.setStatus(userDTO.getStatus());
                entity.setDtUpdate(LocalDateTime.now());
                dao.save(entity);
            } else {
                throw new SingleErrorResponse("Введена не верная версия");
            }
        } else {
            throw new SingleErrorResponse("Нет опльзователя для обновления с таким id");
        }
    }

    private void check(UserDTO userDTO) throws MultipleErrorResponse {
        MultipleErrorResponse errorResponse = new MultipleErrorResponse();

        if (userDTO == null) {
            throw new NullPointerException("NULL");
        }
        if (userDTO.getMail() == null || userDTO.getMail().isBlank()) {
            if (errorResponse.getLogref() == null) {
                errorResponse.setLogref("Прроверьте верность внесенных данных");
            }
            errorResponse.setErrors(new MyError("Поле не заполнено", "mail"));
        }
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9!#$%&'*+/=?^_`{|},~\\-]+(?:\\\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~\\-]+)*@+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?+\\.+[a-zA-Z]*$");
        if (!pattern.matcher(userDTO.getMail()).matches()) {
            if (errorResponse.getLogref() == null) {
                errorResponse.setLogref("Прроверьте верность внесенных данных");
            }
            errorResponse.setErrors(new MyError("Проверьте верность введенного email", "mail"));
        }
        if (userDTO.getFio() == null) {
            if (errorResponse.getLogref() == null) {
                errorResponse.setLogref("Прроверьте верность внесенных данных");
            }
            errorResponse.setErrors(new MyError("Поле не заполнено", "fio"));
        }
        if (userDTO.getPassword() == null) {
            if (errorResponse.getLogref() == null) {
                errorResponse.setLogref("Прроверьте верность внесенных данных");
            }
            errorResponse.setErrors(new MyError("Поле не заполнено", "password"));
        }
        if (userDTO.getRole() == null) {
            if (errorResponse.getLogref() == null) {
                errorResponse.setLogref("Прроверьте верность внесенных данных");
            }
            errorResponse.setErrors(new MyError("Поле не заполнено", "role"));
        }
        if (userDTO.getStatus() == null) {
            if (errorResponse.getLogref() == null) {
                errorResponse.setLogref("Прроверьте верность внесенных данных");
            }
            errorResponse.setErrors(new MyError("Поле не заполнено", "status"));
        }
        if (errorResponse.getLogref() != null) {
            throw errorResponse;
        }
    }
}
