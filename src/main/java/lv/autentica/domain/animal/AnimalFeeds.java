package lv.autentica.domain.animal;

import lv.autentica.domain.User;
import lv.autentica.domain.staff.Staff;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ZOO_ANIMAL_FEEDS")
public class AnimalFeeds {

    @Id
    @SequenceGenerator(name="id_seq", sequenceName="ZOO_ANIMAL_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="id_seq")
    @Column(name = "ID", columnDefinition = "NUMBER(12)")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ANIMAL_ID", nullable = false)
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "STAFF_ID", nullable = false)
    private Staff worker;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Column(name = "FEED_TIME")
    private LocalDateTime created;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Staff getWorker() {
        return worker;
    }

    public void setWorker(Staff worker) {
        this.worker = worker;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
