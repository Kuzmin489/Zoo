package lv.autentica.service.cage;

import lv.autentica.DAO.cage.CageCleanDAO;
import lv.autentica.DAO.cage.CageDAO;
import lv.autentica.DAO.staff.StaffDAO;
import lv.autentica.domain.cages.Cage;
import lv.autentica.domain.cages.CageClean;
import lv.autentica.domain.staff.Staff;
import lv.autentica.dto.cage.CageCleanDTO;
import lv.autentica.exception.CageException;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by german on 4/17/16 for MyTi project.
 */
@Component
public class CageCleanService {

    @Autowired
    StaffDAO staffDAO;

    @Autowired
    CageDAO cageDAO;

    @Autowired
    CageCleanDAO cageCleanDAO;

    public void upsertCageClean(CageCleanDTO cageCleanDTO) throws CageException {
        CageClean cageClean = new CageClean();
        try {
            cageClean.setId(cageCleanDTO.getId());
            cageClean.setWorker(staffDAO.get(Staff.class,cageCleanDTO.getWorkerId()));
            cageClean.setCage(cageDAO.get(Cage.class,cageCleanDTO.getCageId()));

            if(cageClean.getId() == null) {
                cageCleanDAO.create(cageClean);
            } else  cageCleanDAO.update(cageClean);
        } catch (JDBCException e ){
            throw new CageException(e.getSQLException());
        } catch (Exception e ){
            throw new CageException(e);
        }
    }
}
