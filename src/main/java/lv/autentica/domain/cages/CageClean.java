package lv.autentica.domain.cages;

import lv.autentica.domain.User;
import lv.autentica.domain.staff.Staff;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ZOO_CAGE_CLEANS")
public class CageClean {

    @Id
    @SequenceGenerator(name="id_seq", sequenceName="ZOO_CAGE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="id_seq")
    @Column(name = "ID", columnDefinition = "NUMBER(12)")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "STAFF_ID", nullable = false)
    private Staff worker;

    @ManyToOne
    @JoinColumn(name = "CAGE_ID", nullable = false)
    private Cage cage;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Column(name = "CLEAN_TIME")
    private LocalDateTime cleanTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Staff getWorker() {
        return worker;
    }

    public void setWorker(Staff worker) {
        this.worker = worker;
    }

    public Cage getCage() {
        return cage;
    }

    public void setCage(Cage cage) {
        this.cage = cage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCleanTime() {
        return cleanTime;
    }

    public void setCleanTime(LocalDateTime cleanTime) {
        this.cleanTime = cleanTime;
    }
}
