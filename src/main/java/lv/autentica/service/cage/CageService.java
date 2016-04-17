package lv.autentica.service.cage;

import lv.autentica.DAO.cage.CageDAO;
import lv.autentica.DAO.cage.CageTypeDAO;
import lv.autentica.domain.cages.Cage;
import lv.autentica.domain.cages.CageType;
import lv.autentica.dto.cage.CageDTO;
import lv.autentica.exception.CageException;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CageService {

    @Autowired
    CageTypeDAO cageTypeDAO;

    @Autowired
    CageDAO cageDAO;
    public void upsertCage(CageDTO cageDTO) throws CageException {
        Cage cage = new Cage();
        try{
            cage.setCageType(cageTypeDAO.get(CageType.class,cageDTO.getCageTypeId()));
            cage.setCapacity(cageDTO.getCapacity());
            cage.setName(cageDTO.getName());
            cage.setId(cageDTO.getId());

            if(cage.getId() == null) {
                cageDAO.create(cage);
            } else {
                cageDAO.update(cage);
            }
        } catch (JDBCException e ){
            throw new CageException(e.getSQLException());
        } catch (Exception e ){
            throw new CageException(e);
        }
    }

}
