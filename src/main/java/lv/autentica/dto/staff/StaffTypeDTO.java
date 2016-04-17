package lv.autentica.dto.staff;

import lv.autentica.domain.staff.StaffType;

/**
 * Created by german on 4/16/16 for MyTi project.
 */
public class StaffTypeDTO {

    public StaffTypeDTO(StaffType staffType) {
        this.id = staffType.getId();
        this.parentTypeId = staffType.getManager().getId();
        this.typeName = staffType.getTypeName();
    }
    public StaffTypeDTO() {
    }

    private Long id;
    private Long parentTypeId;
    private String typeName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentTypeId() {
        return parentTypeId;
    }

    public void setParentTypeId(Long parentTypeId) {
        this.parentTypeId = parentTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
