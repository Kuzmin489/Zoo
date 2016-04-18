package lv.autentica.domain.cages;
import lv.autentica.domain.User;
import lv.autentica.domain.animal.Animal;
import org.hibernate.annotations.Type;
import org.hibernate.mapping.List;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "ZOO_CAGE_LOADS")
public class CageLoad {

    @Id
    @SequenceGenerator(name="id_seq", sequenceName="ZOO_LDC_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="id_seq")
    @Column(name = "ID", columnDefinition = "NUMBER(12)")
    private Long id;


    @ManyToOne
    @JoinColumn(name = "CAGE_ID", nullable = false)
    private Cage cage;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @OneToMany(mappedBy="cage")
    private Set<Animal> animals;

    @Transient
    private String animalsList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(Set<Animal> animals) {
        this.animals = animals;
    }

    public String getAnimalsList() {
        return animalsList;
    }

    public void setAnimalsList(String animalsList) {
        this.animalsList = animalsList;
    }
}
