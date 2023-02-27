package finalProject.NoPainNoGain.core.dto.user;

public class LoginDTO {

    private String mail;

    private String password;

    public LoginDTO() {
    }

    public LoginDTO(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }
}
