package lv.autentica.domain.cages;


import lv.autentica.domain.User;
import lv.autentica.domain.animal.Animal;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ZOO_CAGES")
public class Cage {

    @Id
    @SequenceGenerator(name="id_seq", sequenceName="ZOO_CAGE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="id_seq")
    @Column(name = "ID", columnDefinition = "NUMBER(12)")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "TYPE_ID", nullable = false)
    private CageType cageType;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @OneToMany(mappedBy = "cage")
    private Set<CageClean> cageClean;

    @OneToMany(fetch = FetchType.EAGER, mappedBy="cage",cascade = CascadeType.ALL)
    private Set<Animal> animals;


    @Column(name = "NAME", columnDefinition = "VARCHAR2(100)")
    private String name;

    @Column(name = "UNIT_CAPACITY", columnDefinition = "NUMBER(12)")
    private Long capacity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CageType getCageType() {
        return cageType;
    }

    public void setCageType(CageType cageType) {
        this.cageType = cageType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public Set<CageClean> getCageClean() {
        return cageClean;
    }

    public void setCageClean(Set<CageClean> cageClean) {
        this.cageClean = cageClean;
    }

    public Set<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(Set<Animal> animals) {
        this.animals = animals;
    }
}
