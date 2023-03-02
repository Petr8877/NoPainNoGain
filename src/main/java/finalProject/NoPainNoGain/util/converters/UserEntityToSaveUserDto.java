package finalProject.NoPainNoGain.util.converters;

import finalProject.NoPainNoGain.core.dto.user.SaveUserDto;
import finalProject.NoPainNoGain.entity.UserEntity;
import finalProject.NoPainNoGain.util.Utils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserEntityToSaveUserDto implements Converter<UserEntity, SaveUserDto> {

    @Override
    public SaveUserDto convert(UserEntity source) {
        return new SaveUserDto(source.getUuid(), source.getDtCreate(),
                Utils.convertLocalDateTimeToLong(source.getDtUpdate()), source.getMail(), source.getFio(),
                source.getRole(), source.getStatus());
    }

}
