package finalProject.NoPainNoGain.web.controllers;

import finalProject.NoPainNoGain.core.dto.user.LoginDTO;
import finalProject.NoPainNoGain.core.dto.user.UserRegistrationDTO;
import finalProject.NoPainNoGain.service.api.IRegistrationUserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
public class PersonalAccountController {

    private final IRegistrationUserService service;

    public PersonalAccountController(IRegistrationUserService service) {
        this.service = service;
    }

    @RequestMapping(path = "/registration", method = RequestMethod.POST)
    public void registration(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        service.registrationUser(userRegistrationDTO);
    }

    @RequestMapping(path = "/verification", method = RequestMethod.GET)
    public void verification(String code, String mail) {
        service.verification(code, mail);
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public void login(@RequestBody LoginDTO loginDTO) {
        service.login(loginDTO.getMail(), loginDTO.getPassword());
    }

    @RequestMapping(path = "/me", method = RequestMethod.GET)
    public void aboutMe() {
        // НАПИСАТЬ РЕАЛИЗАЦИЮ
    }
}
