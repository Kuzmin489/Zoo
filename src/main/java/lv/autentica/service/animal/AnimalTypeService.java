package lv.autentica.service.animal;

import lv.autentica.DAO.animal.AnimalTypeDAO;
import lv.autentica.domain.animal.AnimalType;
import lv.autentica.dto.animal.AnimalTypeDTO;
import lv.autentica.exception.AnimalException;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnimalTypeService {
    @Autowired
    private AnimalTypeDAO animalTypeDAO;

    public void upsertAnimal(AnimalTypeDTO animalTypeDTO) throws AnimalException {
        AnimalType animalType = new AnimalType();
        animalType.setId(animalTypeDTO.getId());
        animalType.setTypeName(animalTypeDTO.getTypeName());
        try {
            animalType.setBaseType(animalTypeDAO.get(AnimalType.class, animalTypeDTO.getBaseTypeId()));
            if(animalType.getId() == null) {
                animalTypeDAO.create(animalType);
            } else {
                animalTypeDAO.update(animalType);
            }
        } catch (JDBCException e ){
            throw new AnimalException(e.getSQLException());
        } catch (Exception e ){
            throw new AnimalException(e);
        }
    }
}
