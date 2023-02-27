package finalProject.NoPainNoGain.core.dto.user;

public class UserRegistrationDTO {

    private String mail;

    private String fio;

    private String password;

    public UserRegistrationDTO() {
    }

    public UserRegistrationDTO(String mail, String fio, String password) {
        this.mail = mail;
        this.fio = fio;
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
}
