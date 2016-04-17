package lv.autentica.dto.cage;

import lv.autentica.domain.cages.CageClean;

/**
 * Created by german on 4/17/16 for MyTi project.
 */
public class CageCleanDTO {
    private Long id;
    private Long cageId;
    private Long workerId;

    public CageCleanDTO() {
    }

    public CageCleanDTO(CageClean cageClean) {
        this.id = cageClean.getId();
        this.cageId = cageClean.getCage().getId();
        this.workerId = cageClean.getWorker().getId();
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

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }
}
