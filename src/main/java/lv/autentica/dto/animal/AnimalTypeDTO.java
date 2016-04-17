package lv.autentica.dto.animal;

import lv.autentica.domain.animal.AnimalType;

public class AnimalTypeDTO {
    private String typeName;
    private Long baseTypeId;
    private Long id;

    public AnimalTypeDTO() {
    }

    public AnimalTypeDTO(AnimalType animalType) {
        this.typeName = animalType.getTypeName();
        this.baseTypeId = animalType.getBaseType().getId();
        this.id = animalType.getId();
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Long getBaseTypeId() {
        return baseTypeId;
    }

    public void setBaseTypeId(Long baseTypeId) {
        this.baseTypeId = baseTypeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
