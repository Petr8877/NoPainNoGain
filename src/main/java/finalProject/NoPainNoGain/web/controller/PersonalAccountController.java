package finalProject.NoPainNoGain.web.controller;

import finalProject.NoPainNoGain.core.dto.user.LoginDto;
import finalProject.NoPainNoGain.core.dto.user.UserRegistrationDto;
import finalProject.NoPainNoGain.service.api.IRegistrationUserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class PersonalAccountController {

    private final IRegistrationUserService service;

    public PersonalAccountController(IRegistrationUserService service) {
        this.service = service;
    }

    @PostMapping(path = "/registration")
    public void registration(@RequestBody @Validated UserRegistrationDto userRegistrationDTO) {
        service.registrationUser(userRegistrationDTO);
    }

    @GetMapping(path = "/verification")
    public void verification(String code, String mail) {
        service.verification(code, mail);
    }

    @PostMapping(path = "/login")
    public void login(@RequestBody @Validated LoginDto loginDTO) {
        service.login(loginDTO.mail(), loginDTO.password());
    }

    @GetMapping(path = "/me")
    public void aboutMe() {
        // НАПИСАТЬ РЕАЛИЗАЦИЮ
    }
}
