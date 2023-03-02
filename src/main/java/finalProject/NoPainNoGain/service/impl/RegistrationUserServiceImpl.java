package finalProject.NoPainNoGain.service.impl;

import finalProject.NoPainNoGain.core.dto.user.UserRegistrationDto;
import finalProject.NoPainNoGain.core.exception.SingleErrorResponse;
import finalProject.NoPainNoGain.entity.UserEntity;
import finalProject.NoPainNoGain.repository.RegistrationUserRepository;
import finalProject.NoPainNoGain.service.api.IRegistrationUserService;
import finalProject.NoPainNoGain.usersEnum.Status;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Service
public class RegistrationUserServiceImpl implements IRegistrationUserService {

    private final EmailServiceImpl serviceIml;

    private final ConversionService conversionService;

    private final RegistrationUserRepository repo;

    public RegistrationUserServiceImpl(EmailServiceImpl serviceIml, ConversionService conversionService, RegistrationUserRepository repo) {
        this.serviceIml = serviceIml;
        this.conversionService = conversionService;
        this.repo = repo;
    }

    @Override
    public void registrationUser(UserRegistrationDto userRegistrationDTO) {
        if (!repo.existsByMail(userRegistrationDTO.mail())) {
            UserEntity save = repo.save(Objects.requireNonNull(conversionService.convert(userRegistrationDTO, UserEntity.class)));
            serviceIml.sendSimpleMessage(save.getMail(), "Регистрация прошла успешно!", createLink(save.getMail(), save.getUuid()));
        } else {
           throw  new SingleErrorResponse("Данная почта уже была использована для регистрации");
        }
    }

    @Override
    public void verification(String code, String mail) {
        UserEntity user = repo.findById(UUID.fromString(code)).orElseThrow(() -> new SingleErrorResponse("Такого пользователя нет в базе"));
        if (Objects.equals(user.getMail(), mail)) {
            switch (user.getStatus()) {
                case WAITING_ACTIVATION -> {
                    user.setStatus(Status.ACTIVATED);
                    user.setDtUpdate(LocalDateTime.now());
                    repo.save(user);
                }
                case DEACTIVATED -> throw new SingleErrorResponse("Пользователь деактивирован");
                case ACTIVATED -> throw new SingleErrorResponse("Данный пользователь уже был активирован");
            }
            } else {
                throw new SingleErrorResponse("Указан не корректный адрес электронной почты");
            }
    }

    @Override
    public void login(String mail, String password) {
        if (repo.existsByMail(mail)) {
            UserEntity userByMail = repo.findByMail(mail);
            if (Objects.equals(userByMail.getStatus(), Status.ACTIVATED)) {
                if (Objects.equals(userByMail.getPassword(), password)) {
                    /////////////////////////////////////////////////////////////////////////
                    /////////////////////////////////////////////////////////////////////////
                    System.out.println("GOOOOD!!!");
                    /////////////////////////////////////////////////////////////////////////
                    /////////////////////////////////////////////////////////////////////////
                } else {
                    throw new SingleErrorResponse("Введен не верный пароль");
                }
            } else if (Objects.equals(userByMail.getStatus(), Status.WAITING_ACTIVATION)) {
                throw new SingleErrorResponse("Данный пользовтель деактивирован. За дополнительной информаццией обратитесь к адинистратору");
            } else {
                throw new SingleErrorResponse("Данный пользовтель не активизирован. Письмо с активизацией было направлено на электронную почту указанную при регистрации");
            }
            {

            }
        } else {
            throw new SingleErrorResponse("Введен не верный логин");
        }
    }

    private String createLink(String mail, UUID uuid) {
        return """
                Для вверификации пройдите по ссылке ниже
                                
                http://localhost:8080/api/v1/users/verification?code=""" + uuid + "&mail=" + mail;
    }
}
