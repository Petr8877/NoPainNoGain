package finalProject.NoPainNoGain.util.converters;

import finalProject.NoPainNoGain.core.dto.user.UserRegistrationDto;
import finalProject.NoPainNoGain.entity.UserEntity;
import finalProject.NoPainNoGain.usersEnum.RoleUser;
import finalProject.NoPainNoGain.usersEnum.Status;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class RegistrationDtoToEntity implements Converter<UserRegistrationDto , UserEntity > {
    @Override
    public UserEntity convert(UserRegistrationDto source) {
        LocalDateTime timeNow = LocalDateTime.now();
        return new UserEntity(UUID.randomUUID(), source.mail(), source.fio(), source.password(), timeNow, timeNow,
                RoleUser.USER, Status.WAITING_ACTIVATION);
    }
}
