package lv.autentica.domain.animal;

import lv.autentica.domain.User;
import lv.autentica.domain.cages.CageType;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ZOO_ANIMAL_TYPES")
public class AnimalType {
    @Id
    @SequenceGenerator(name="id_seq", sequenceName="ZOO_ATP_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="id_seq")
    @Column(name = "ID", columnDefinition = "NUMBER(12)")
    private Long id;

    @Column(name = "TYPE_NAME", columnDefinition = "VARCHAR2(100)")
    private String typeName;

    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="ATP_ID")
    private AnimalType baseType;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @OneToMany(mappedBy="baseType")
    private Set<AnimalType> subTypes;

    @OneToMany(mappedBy = "animalType")
    private Set<Animal> animals;

    @OneToMany(mappedBy = "animalType")
    private Set<CageType> cageTypes;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AnimalType getBaseType() {
        return baseType;
    }

    public void setBaseType(AnimalType baseType) {
        this.baseType = baseType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<AnimalType> getSubTypes() {
        return subTypes;
    }

    public void setSubTypes(Set<AnimalType> subTypes) {
        this.subTypes = subTypes;
    }

    public Set<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(Set<Animal> animals) {
        this.animals = animals;
    }

    public Set<CageType> getCageTypes() {
        return cageTypes;
    }

    public void setCageTypes(Set<CageType> cageTypes) {
        this.cageTypes = cageTypes;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
