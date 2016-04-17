package lv.autentica.service.cage;

import lv.autentica.DAO.animal.AnimalTypeDAO;
import lv.autentica.DAO.cage.CageTypeDAO;
import lv.autentica.domain.animal.AnimalType;
import lv.autentica.domain.cages.CageType;
import lv.autentica.dto.cage.CageTypeDTO;
import lv.autentica.exception.CageException;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CageTypeService {

    @Autowired
    CageTypeDAO cageTypeDAO;

    @Autowired
    AnimalTypeDAO animalTypeDAO;

    public void upsertCageType(CageTypeDTO cageTypeDTO) throws CageException {
        CageType cageType = new CageType();
        try {
            cageType.setId(cageTypeDTO.getId());
            cageType.setAnimalType(animalTypeDAO.get(AnimalType.class,cageTypeDTO.getAnimalId()));
            cageType.setCageType(cageTypeDTO.getCageName());
            if(cageType.getId() == null) {
                cageTypeDAO.create(cageType);
            } else {
                cageTypeDAO.update(cageType);
            }
        } catch (JDBCException e ) {
            throw new CageException(e.getSQLException());
        } catch (Exception e ) {
            throw new CageException(e);
        }
    }

}
