package lv.autentica.dto.cage;

import lv.autentica.domain.animal.Animal;
import lv.autentica.domain.cages.CageLoad;

import java.util.Set;
import java.util.stream.Collectors;

public class CageLoadDTO {

    private Long id;
    private Long cageId;
    private Set<Long> animalIdList;

    public CageLoadDTO() {
    }

    public CageLoadDTO(CageLoad cageLoad) {
        this.id = cageLoad.getId();
        this.cageId = cageLoad.getCage().getId();
        this.animalIdList = cageLoad.getCage().getAnimals().stream().map(Animal::getId).collect(Collectors.toSet());
    }

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
