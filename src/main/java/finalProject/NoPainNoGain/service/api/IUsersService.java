package finalProject.NoPainNoGain.service.api;

import finalProject.NoPainNoGain.core.dto.user.SaveUserDto;
import finalProject.NoPainNoGain.core.dto.user.UserDto;
import finalProject.NoPainNoGain.core.dto.PageDto;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IUsersService {

    void createUser(UserDto userDTO);

    SaveUserDto getUser(UUID id);

    void updateUser(UUID id, long dtUpdate, UserDto userDTO);

    PageDto<SaveUserDto> getUsersPage(Pageable pageable);
}
