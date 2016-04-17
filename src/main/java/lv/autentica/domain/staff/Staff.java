package lv.autentica.domain.staff;

import lv.autentica.domain.User;
import lv.autentica.domain.animal.AnimalFeeds;
import lv.autentica.domain.cages.CageClean;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ZOO_STAFF")
public class Staff {

    @Id
    @SequenceGenerator(name="id_seq", sequenceName="ZOO_STAFF_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="id_seq")
    @Column(name = "ID", columnDefinition = "NUMBER(12)")
    private Long id;

    @Column(name = "FIRST_NAME",columnDefinition = "VARCHAR2(100)")
    private String firstName;

    @Column(name = "LAST_NAME", columnDefinition = "VARCHAR2(150)")
    private String lastName;

    @Column(name = "SOC_SEC_NUM", columnDefinition = "NUMBER(11)")
    private Long ssnNumber;

    @Column(name = "PHONE", columnDefinition = "NUMBER(8)")
    private Long phone;

    @Column(name = "SALARY", columnDefinition = "NUMBER(6,2)")
    private Double salary;

    @Column(name = "ADDRESS", columnDefinition = "VARCHAR2(200)")
    private String address;

    @ManyToOne
    @JoinColumn(name = "TYPE_ID", nullable = false)
    private StaffType position;

    @OneToMany(mappedBy = "worker")
    private Set<AnimalFeeds> animalFeeds;

    @OneToMany(mappedBy = "worker")
    private Set<CageClean> cageClean;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    public Long getId() {
        return id;
    }

    public Staff setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Staff setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Staff setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Long getSsnNumber() {
        return ssnNumber;
    }

    public Staff setSsnNumber(Long ssnNumber) {
        this.ssnNumber = ssnNumber;
        return this;
    }

    public Long getPhone() {
        return phone;
    }

    public Staff setPhone(Long phone) {
        this.phone = phone;
        return this;
    }

    public Double getSalary() {
        return salary;
    }

    public Staff setSalary(Double salary) {
        this.salary = salary;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Staff setAddress(String address) {
        this.address = address;
        return this;
    }

    public StaffType getPosition() {
        return position;
    }

    public Staff setPosition(StaffType position) {
        this.position = position;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Staff setUser(User user) {
        this.user = user;
        return this;
    }

    public Set<AnimalFeeds> getAnimalFeeds() {
        return animalFeeds;
    }

    public Staff setAnimalFeeds(Set<AnimalFeeds> animalFeeds) {
        this.animalFeeds = animalFeeds;
        return this;
    }

    public Set<CageClean> getCageClean() {
        return cageClean;
    }

    public Staff setCageClean(Set<CageClean> cageClean) {
        this.cageClean = cageClean;
        return this;
    }
    public String getFullName() {
        return firstName + " " + lastName;
    }
}
