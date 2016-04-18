package lv.autentica.domain.animal;


import lv.autentica.domain.User;
import lv.autentica.domain.cages.Cage;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ZOO_ANIMALS")
public class Animal {

    @Id
    @SequenceGenerator(name="id_seq", sequenceName="ZOO_ANIMAL_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="id_seq")
    @Column(name = "ID", columnDefinition = "NUMBER(12)")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "TYPE_ID", nullable = false)
    private AnimalType animalType;

    @ManyToOne
    @JoinColumn(name = "CAGE_ID", nullable = true)
    private Cage cage;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Column(name = "NAME", columnDefinition = "VARCHAR2(150)")
    private String name;

    @Column(name = "SEX", columnDefinition = "VARCHAR2(50)")
    private String sex;

    @Column(name = "AGE_YEARS", columnDefinition = "NUMBER(8.2)")
    private Double age;

    @Column(name = "WEIGHT_KG", columnDefinition = "NUMBER(8.2)")
    private Double weight;

    @Column(name = "FEATURES", columnDefinition = "VARCHAR2(300)")
    private String features;

    @Column(name = "COMMENTS", columnDefinition = "VARCHAR2(500)")
    private String comments;

/*    @OneToMany(mappedBy = "animal")
    private Set<AnimalFeeds> animalFeeds;*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Double getAge() {
        return age;
    }

    public void setAge(Double age) {
        this.age = age;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
/*
    public Set<AnimalFeeds> getAnimalFeeds() {
        return animalFeeds;
    }

    public void setAnimalFeeds(Set<AnimalFeeds> animalFeeds) {
        this.animalFeeds = animalFeeds;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cage getCage() {
        return cage;
    }

    public void setCage(Cage cage) {
        this.cage = cage;
    }
}
