package lv.autentica.dto.cage;

import lv.autentica.domain.cages.CageType;

public class CageTypeDTO {

    private Long id;
    private Long animalId;
    private String cageName;

    public CageTypeDTO() {

    }

    public CageTypeDTO(CageType cageType) {
        this.id = cageType.getId();
        this.animalId = cageType.getAnimalType().getId();
        this.cageName = cageType.getCageType();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Long animalId) {
        this.animalId = animalId;
    }

    public String getCageName() {
        return cageName;
    }

    public void setCageName(String cageName) {
        this.cageName = cageName;
    }
}
