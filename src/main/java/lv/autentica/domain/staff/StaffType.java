package lv.autentica.domain.staff;

import lv.autentica.domain.User;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ZOO_STAFF_TYPES")
public class StaffType {

    @Id
    @SequenceGenerator(name="id_seq", sequenceName="ZOO_STP_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="id_seq")
    @Column(name = "ID", columnDefinition = "NUMBER(12)")
    private Long id;

    @Column(name = "TYPE_NAME", columnDefinition = "VARCHAR2(100)")
    private String TypeName;

    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="STP_ID")
    private StaffType manager;

    @OneToMany(mappedBy="manager")
    private Set<StaffType> subordinates;

    @OneToMany(mappedBy = "position")
    private Set<Staff> workers;

    @OneToMany(mappedBy = "position")
    private Set<StaffDuties> duties;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String typeName) {
        TypeName = typeName;
    }

    public StaffType getManager() {
        return manager;
    }

    public void setManager(StaffType manager) {
        this.manager = manager;
    }

    public Set<StaffType> getSubordinates() {
        return subordinates;
    }

    public void setSubordinates(Set<StaffType> subordinates) {
        this.subordinates = subordinates;
    }

    public Set<Staff> getWorkers() {
        return workers;
    }

    public void setWorkers(Set<Staff> workers) {
        this.workers = workers;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<StaffDuties> getDuties() {
        return duties;
    }

    public void setDuties(Set<StaffDuties> duties) {
        this.duties = duties;
    }
}
