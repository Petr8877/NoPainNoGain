package finalProject.NoPainNoGain.core.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import finalProject.NoPainNoGain.usersEnum.RoleUser;
import finalProject.NoPainNoGain.usersEnum.Status;

import java.time.*;
import java.util.UUID;

public class SaveUserDTO {

    private UUID uuid;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss.SS")
    private LocalDateTime dtCreate;

    private long dtUpdate;

    private String mail;

    private String fio;

    private RoleUser role;

    private Status status;

    public SaveUserDTO() {
    }

    public SaveUserDTO(UUID uuid, LocalDateTime dtCreate, long dtUpdate, String mail, String fio, RoleUser role, Status status) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.mail = mail;
        this.fio = fio;
        this.role = role;
        this.status = status;
    }

    public UUID getUuid() {
        return uuid;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public long getDtUpdate() {
        return this.dtUpdate;
    }

    public String getMail() {
        return mail;
    }

    public String getFio() {
        return fio;
    }

    public RoleUser getRole() {
        return role;
    }

    public Status getStatus() {
        return status;
    }
}
