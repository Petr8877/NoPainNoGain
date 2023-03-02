package finalProject.NoPainNoGain.util.converters;

import finalProject.NoPainNoGain.core.dto.user.UserDto;
import finalProject.NoPainNoGain.entity.UserEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class UserDtoToUserEntity implements Converter<UserDto, UserEntity> {
    @Override
    public UserEntity convert(UserDto source) {
        LocalDateTime timeNow = LocalDateTime.now();
        return new UserEntity(UUID.randomUUID(), source.mail(), source.fio(), source.password(), timeNow, timeNow,
                source.role(), source.status());
    }

}
