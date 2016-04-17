package lv.autentica.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity()
@Table(name = "ZOO_USERS")
public class User {

    @Id
    @SequenceGenerator(name = "id_seq", sequenceName = "ZOO_USER_SEQ")
    @Column(name = "ID", columnDefinition = "NUMBER(12)")
    private Long id;

    @Column(name = "LOGIN",columnDefinition = "VARCHAR2(50)")
    private String login;

    @Column(name = "PASS",columnDefinition = "VARCHAR2(50)")
    private String pass;

    @Column(name = "ACCESS_LVL", columnDefinition = "NUMBER(5)")
    private Long acess;

    @Column(name = "DATE_CREATED")
    private LocalDateTime created;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Long getAcess() {
        return acess;
    }

    public void setAcess(Long acess) {
        this.acess = acess;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
