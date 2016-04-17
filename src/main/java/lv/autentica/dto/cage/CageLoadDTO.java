package lv.autentica.dto.cage;

import java.util.Set;

public class CageLoadDTO {

    private Long id;
    private Long cageId;
    private Set<Long> animalIdList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCageId() {
        return cageId;
    }

    public void setCageId(Long cageId) {
        this.cageId = cageId;
    }

    public Set<Long> getAnimalIdList() {
        return animalIdList;
    }

    public void setAnimalIdList(Set<Long> animalIdList) {
        this.animalIdList = animalIdList;
    }
}
