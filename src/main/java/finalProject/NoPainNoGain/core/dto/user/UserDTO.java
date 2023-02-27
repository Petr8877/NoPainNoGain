package finalProject.NoPainNoGain.core.dto.user;


import finalProject.NoPainNoGain.usersEnum.RoleUser;
import finalProject.NoPainNoGain.usersEnum.Status;

public class UserDTO {
    private String mail;

    private String fio;

    private RoleUser role;

    private Status status;

    private String password;

    public UserDTO() {
    }

    public UserDTO(String mail, String fio, RoleUser role, Status status, String password) {
        this.mail = mail;
        this.fio = fio;
        this.role = role;
        this.status = status;
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public String getFio() {
        return fio;
    }

    public String getPassword() {
        return password;
    }

    public RoleUser getRole() {
        return role;
    }

    public Status getStatus() {
        return status;
    }
}
