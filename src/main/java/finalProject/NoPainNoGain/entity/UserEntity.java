package finalProject.NoPainNoGain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import finalProject.NoPainNoGain.usersEnum.RoleUser;
import finalProject.NoPainNoGain.usersEnum.Status;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema = "app", name = "user")
public class UserEntity {

    @Id
    private UUID uuid;

//    @JsonProperty("dt_create")
    private LocalDateTime dtCreate;

//    @JsonProperty("dt_update")
    private LocalDateTime dtUpdate;
    private String mail;

    private String fio;

    @JsonIgnore
    private String password;

    @Enumerated(EnumType.STRING)
    private RoleUser role;

    @Enumerated(EnumType.STRING)
    private Status status;

    public UserEntity() {
    }

    public UserEntity(UUID uuid, String mail, String fio, String password, LocalDateTime dtCreate, LocalDateTime dtUpdate, RoleUser role, Status status) {
        this.uuid = uuid;
        this.mail = mail;
        this.fio = fio;
        this.password = password;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.role = role;
        this.status = status;
    }

    public UUID getUuid() {
        return uuid;
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

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public RoleUser getRole() {
        return role;
    }

    public Status getStatus() {
        return status;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(RoleUser role) {
        this.role = role;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
    }
}
