package finalProject.NoPainNoGain.service;

import finalProject.NoPainNoGain.core.dto.user.UserRegistrationDTO;
import finalProject.NoPainNoGain.core.exception.MultipleErrorResponse;
import finalProject.NoPainNoGain.core.exception.MyError;
import finalProject.NoPainNoGain.core.exception.SingleErrorResponse;
import finalProject.NoPainNoGain.entity.UserEntity;
import finalProject.NoPainNoGain.repository.RegistrationUserRepo;
import finalProject.NoPainNoGain.service.api.IRegistrationUserService;
import finalProject.NoPainNoGain.usersEnum.RoleUser;
import finalProject.NoPainNoGain.usersEnum.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
public class RegistrationUserService implements IRegistrationUserService {
    @Autowired
    private EmailServiceIml serviceIml;

    private final RegistrationUserRepo repo;

    public RegistrationUserService(RegistrationUserRepo repo) {
        this.repo = repo;
    }

    @Override
    public void registrationUser(UserRegistrationDTO userRegistrationDTO) {
        check(userRegistrationDTO);
        if (!repo.existsByMail(userRegistrationDTO.getMail())) {
            String fio = userRegistrationDTO.getFio();
            String mail = userRegistrationDTO.getMail();
            String password = userRegistrationDTO.getPassword();
            UUID uuid = UUID.randomUUID();
            LocalDateTime dtCreate = LocalDateTime.now();
            RoleUser role = RoleUser.USER;
            Status status = Status.WAITING_ACTIVATION;
            repo.save(new UserEntity(uuid, mail, fio, password, dtCreate, dtCreate, role, status));
            serviceIml.sendSimpleMessage(mail, "Регистрация прошла успешно!", createLink(mail, uuid));
        } else {
            throw new SingleErrorResponse("Данная почта уже была использована для регистрации");
        }
//        emailService.sendSimpleEmail(mail, "Регистрация прошла успешно!", createLink(mail, uuid));

    }

    @Override
    public void verification(String code, String mail) {
        if (repo.existsById(UUID.fromString(code))) {
            UserEntity user = repo.findById(UUID.fromString(code)).get();
            if (Objects.equals(user.getMail(), mail)) {
                if (user.getStatus() == Status.WAITING_ACTIVATION) {
                    user.setStatus(Status.ACTIVATED);
                    user.setDtUpdate(LocalDateTime.now());
                    repo.save(user);
                } else if (user.getStatus() == Status.DEACTIVATED) {
                    throw new SingleErrorResponse("Пользователь деактивирован");
                } else {
                    throw new SingleErrorResponse("Данный пользователь уже был активирован");
                }
            } else {
                throw new SingleErrorResponse("Указан не корректный адрес электронной почты");
            }
        } else {
            throw new SingleErrorResponse("Такого пользователя нет в базе");
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
        return "Для вверификации пройдите по ссылке ниже" + "\n" + "\n" +
                "http://localhost:8080/api/v1/users/verification?code=" + uuid + "&mail=" +
                mail;
    }

    private void check(UserRegistrationDTO registrationDTO) {
        MultipleErrorResponse errorResponse = new MultipleErrorResponse();

        if (registrationDTO == null) {
            throw new SingleErrorResponse("Заполните ворму регистрации");
        }
        if (registrationDTO.getMail() == null || registrationDTO.getMail().isBlank()) {
            if (errorResponse.getLogref() == null) {
                errorResponse.setLogref("Прроверьте верность внесенных данных");
            }
            errorResponse.setErrors(new MyError("Поле не заполнено", "mail"));
        }
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9!#$%&'*+/=?^_`{|},~\\-]+(?:\\\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~\\-]+)*@+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?+\\.+[a-zA-Z]*$");
        if (!pattern.matcher(registrationDTO.getMail()).matches()) {
            if (errorResponse.getLogref() == null) {
                errorResponse.setLogref("Прроверьте верность внесенных данных");
            }
            errorResponse.setErrors(new MyError("Проверьте верность введенного email", "mail"));
        }
        if (registrationDTO.getFio() == null) {
            if (errorResponse.getLogref() == null) {
                errorResponse.setLogref("Прроверьте верность внесенных данных");
            }
            errorResponse.setErrors(new MyError("Поле не заполнено", "fio"));
        }
        if (registrationDTO.getPassword() == null) {
            if (errorResponse.getLogref() == null) {
                errorResponse.setLogref("Прроверьте верность внесенных данных");
            }
            errorResponse.setErrors(new MyError("Поле не заполнено", "password"));
        }
        if (errorResponse.getLogref() != null) {
            throw errorResponse;
        }
    }
}
