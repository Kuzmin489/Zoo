package lv.autentica.service.staff;

import lv.autentica.DAO.staff.StaffDutiesDAO;
import lv.autentica.DAO.staff.StaffTypeDAO;
import lv.autentica.domain.staff.StaffDuties;
import lv.autentica.domain.staff.StaffType;
import lv.autentica.dto.staff.StaffDutyDTO;
import lv.autentica.exception.StaffException;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by german on 4/16/16 for MyTi project.
 */
@Component
public class StaffDutyService {

    @Autowired
    StaffDutiesDAO staffDutiesDAO;

    @Autowired
    StaffTypeDAO staffTypeDAO;

    public void upsertStaffType(StaffDutyDTO staffDutyDTO) throws StaffException {
        StaffDuties staffDuty = new StaffDuties();
        staffDuty.setId(staffDutyDTO.getId());
        staffDuty.setDescription(staffDutyDTO.getDescription());
        staffDuty.setPosition(staffTypeDAO.get(StaffType.class,staffDutyDTO.getPositionId()));
        try {
            if(staffDutyDTO.getId() == null) {
                staffDutiesDAO.create(staffDuty);
            } else {
                staffDutiesDAO.update(staffDuty);
            }

        }catch (JDBCException e ) {
            throw new StaffException(e.getSQLException());
        } catch (Exception e ) {
            throw new StaffException(e);
        }
    }
}
