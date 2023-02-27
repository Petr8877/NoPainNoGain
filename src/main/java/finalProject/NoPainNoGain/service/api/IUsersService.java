package finalProject.NoPainNoGain.service.api;

import finalProject.NoPainNoGain.core.dto.user.SaveUserDTO;
import finalProject.NoPainNoGain.core.dto.user.UserDTO;
import finalProject.NoPainNoGain.core.dto.PageDTO;
import finalProject.NoPainNoGain.core.exception.MultipleErrorResponse;

import java.util.UUID;

public interface IUsersService {

    void createUser(UserDTO userDTO) throws MultipleErrorResponse;

    PageDTO<SaveUserDTO> getUsersPage(Integer page, int size);

    SaveUserDTO getUser(UUID id);

    void updateUser(UUID id, long dtUpdate, UserDTO userDTO);
}
