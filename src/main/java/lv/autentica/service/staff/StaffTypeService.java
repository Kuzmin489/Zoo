package lv.autentica.service.staff;

import lv.autentica.DAO.staff.StaffTypeDAO;
import lv.autentica.domain.staff.StaffType;
import lv.autentica.dto.staff.StaffTypeDTO;
import lv.autentica.exception.StaffException;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by german on 4/16/16 for MyTi project.
 */
@Component
public class StaffTypeService {
    @Autowired
    StaffTypeDAO staffTypeDAO;
    public void upsertStaffType(StaffTypeDTO staffTypeDTO) throws StaffException {
        StaffType staffType = new StaffType();
        staffType.setId(staffTypeDTO.getId());
        staffType.setTypeName(staffTypeDTO.getTypeName());
        staffType.setManager(staffTypeDAO.get(StaffType.class,staffTypeDTO.getParentTypeId()));
        try {
            if(staffTypeDTO.getId() == null) {
                staffTypeDAO.create(staffType);
            } else {
                staffTypeDAO.update(staffType);
            }

        }catch (JDBCException e ) {
            throw new StaffException(e.getSQLException());
        } catch (Exception e ) {
            throw new StaffException(e);
        }
    }
}
