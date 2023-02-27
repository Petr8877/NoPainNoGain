package finalProject.NoPainNoGain.service.api;

import finalProject.NoPainNoGain.core.dto.user.UserRegistrationDTO;

public interface IRegistrationUserService {

    void registrationUser(UserRegistrationDTO userRegistrationDTO);

    void verification(String code, String mail);

    void login(String mail, String password);
}
