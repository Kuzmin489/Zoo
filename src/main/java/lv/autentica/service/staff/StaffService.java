package lv.autentica.service.staff;

import lv.autentica.DAO.staff.StaffDAO;
import lv.autentica.DAO.staff.StaffTypeDAO;
import lv.autentica.domain.staff.Staff;
import lv.autentica.domain.staff.StaffType;
import lv.autentica.dto.staff.StaffDTO;
import lv.autentica.exception.StaffException;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StaffService {

    @Autowired
    private StaffDAO staffDAO;

    @Autowired
    private StaffTypeDAO staffTypeDAO;

    public void upsertStaff(StaffDTO staff) throws StaffException {
        Staff worker = new Staff();
        worker.setAddress(staff.getAddress())
                .setFirstName(staff.getName())
                .setLastName(staff.getLastName())
                .setPhone(staff.getPhone())
                .setSalary(staff.getSalary())
                .setSsnNumber(staff.getSsn())
                .setId(staff.getId())
                .setPosition(staffTypeDAO.get(StaffType.class,staff.getStaffTypeId()));
        try {
            if (staff.getId() == null) {
                staffDAO.create(worker);
            } else {
                staffDAO.update(worker);
            }
        }catch (JDBCException e ){
         throw new StaffException(e.getSQLException());
        }
        catch (Exception e ){
            throw new StaffException(e.getMessage());
        }
    }
}
