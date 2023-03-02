package finalProject.NoPainNoGain.service.api;

import finalProject.NoPainNoGain.core.dto.user.UserRegistrationDto;

public interface IRegistrationUserService {

    void registrationUser(UserRegistrationDto userRegistrationDTO);

    void verification(String code, String mail);

    void login(String mail, String password);
}
