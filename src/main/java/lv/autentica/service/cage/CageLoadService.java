package lv.autentica.service.cage;

import lv.autentica.DAO.cage.CageDAO;
import lv.autentica.DAO.cage.CageLoadDAO;
import lv.autentica.domain.cages.Cage;
import lv.autentica.domain.cages.CageLoad;
import lv.autentica.dto.cage.CageLoadDTO;
import lv.autentica.exception.CageException;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by german on 4/18/16 for MyTi project.
 */
@Component
public class CageLoadService {

    @Autowired
    CageLoadDAO cageLoadDAO;

    @Autowired
    CageDAO cageDAO;


    public void upsertCageLoad(CageLoadDTO cageLoadDTO) throws CageException {
        CageLoad cageLoad = new CageLoad();
        try{
            cageLoad.setId(cageLoadDTO.getId());
            cageLoad.setCage(cageDAO.get(Cage.class,cageLoadDTO.getCageId()));
            cageLoad.setAnimalsList(StringUtils.join(cageLoadDTO.getAnimalIdList(),","));
            if(cageLoad.getId() == null) {
                cageLoadDAO.create(cageLoad);
            } else {
                cageLoadDAO.update(cageLoad);
            }
        } catch (JDBCException e ){
            throw new CageException(e.getSQLException());
        } catch (Exception e ){
            throw new CageException(e);
        }
    }
}
