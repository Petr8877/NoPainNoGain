package finalProject.NoPainNoGain.web.controllers;

import finalProject.NoPainNoGain.core.dto.user.SaveUserDTO;
import finalProject.NoPainNoGain.core.dto.user.UserDTO;
import finalProject.NoPainNoGain.core.dto.PageDTO;
import finalProject.NoPainNoGain.service.api.IUsersService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/users")
public class UsersController {

    private final IUsersService service;

    public UsersController(IUsersService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addNewUser(@RequestBody UserDTO userDTO) {
        service.createUser(userDTO);
    }

    @RequestMapping(method = RequestMethod.GET)
    public PageDTO getUsersPage(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        return service.getUsersPage(page, size);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public SaveUserDTO saveUserDTO(@PathVariable("id") UUID id) {
        return service.getUser(id);
    }

    @RequestMapping(path = "/{id}/dt_update/{dt_update}", method = RequestMethod.PUT)
    public void updateUser(@PathVariable("id") UUID id, @PathVariable("dt_update") long dtUpdate,
                           @RequestBody UserDTO userDTO) {
        service.updateUser(id, dtUpdate, userDTO);
    }
}
