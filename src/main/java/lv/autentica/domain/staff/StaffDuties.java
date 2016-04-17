package lv.autentica.domain.staff;

import lv.autentica.domain.User;

import javax.persistence.*;

@Entity
@Table(name = "ZOO_DUTIES")
public class StaffDuties {

    @Id
    @SequenceGenerator(name="id_seq", sequenceName="ZOO_DUTY_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="id_seq")
    @Column(name = "ID", columnDefinition = "NUMBER(12)")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "STP_ID", nullable = false)
    private StaffType position;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Column(name = "DESCRIPTION",columnDefinition = "VARCHAR2(150)")
    private String description;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StaffType getPosition() {
        return position;
    }

    public void setPosition(StaffType position) {
        this.position = position;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }




}
