package lv.autentica.domain.cages;

import lv.autentica.domain.User;
import lv.autentica.domain.animal.AnimalType;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ZOO_CAGE_TYPES")
public class CageType {

    @Id
    @SequenceGenerator(name="id_seq", sequenceName="ZOO_CTP_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="id_seq")
    @Column(name = "ID", columnDefinition = "NUMBER(12)")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "ATP_ID", nullable = false)
    private AnimalType animalType;

    @Column(name = "TYPE_NAME", columnDefinition = "VARCHAR2(200)")
    private String cageType;

    @OneToMany(mappedBy = "cageType")
    private Set<Cage> cages;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    public String getCageType() {
        return cageType;
    }

    public void setCageType(String cageType) {
        this.cageType = cageType;
    }

    public Set<Cage> getCages() {
        return cages;
    }

    public void setCages(Set<Cage> cages) {
        this.cages = cages;
    }
}
