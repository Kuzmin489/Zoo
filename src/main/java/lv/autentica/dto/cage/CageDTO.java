package lv.autentica.dto.cage;

import lv.autentica.domain.cages.Cage;

/**
 * Created by german on 4/17/16 for MyTi project.
 */
public class CageDTO {
    private Long id;
    private Long cageTypeId;
    private String name;
    private Long capacity;

    public CageDTO() {

    }

    public CageDTO(Cage cage) {
        this.id = cage.getId();
        this.cageTypeId = cage.getCageType().getId();
        this.name = cage.getName();
        this.capacity = cage.getCapacity();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCageTypeId() {
        return cageTypeId;
    }

    public void setCageTypeId(Long cageTypeId) {
        this.cageTypeId = cageTypeId;
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
}
