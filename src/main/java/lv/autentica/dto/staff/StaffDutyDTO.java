package lv.autentica.dto.staff;

import lv.autentica.domain.staff.StaffDuties;

public class StaffDutyDTO {
    public StaffDutyDTO() {
    }

    public StaffDutyDTO(StaffDuties staffDuty) {
        this.id = staffDuty.getId();
        this.positionId = staffDuty.getPosition().getId();
        this.description = staffDuty.getDescription();
    }
    private Long id;
    private Long positionId;
    private String description;


    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
