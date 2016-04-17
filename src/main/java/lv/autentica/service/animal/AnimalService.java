package lv.autentica.service.animal;


import lv.autentica.DAO.animal.AnimalDAO;
import lv.autentica.DAO.animal.AnimalTypeDAO;
import lv.autentica.domain.animal.Animal;
import lv.autentica.domain.animal.AnimalType;
import lv.autentica.dto.animal.AnimalDTO;
import lv.autentica.exception.AnimalException;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnimalService {

    @Autowired
    AnimalTypeDAO animalTypeDAO;

    @Autowired
    AnimalDAO animalDAO;

    public void upsertAnimal(AnimalDTO animalDTO) throws AnimalException {
        try {
            Animal animal = new Animal();
            animal.setAge(animalDTO.getAge());
            animal.setAnimalType(animalTypeDAO.get(AnimalType.class, animalDTO.getTypeId()));
            animal.setComments(animalDTO.getComments());
            animal.setFeatures(animalDTO.getFeatures());
            animal.setName(animalDTO.getName());
            animal.setSex(animalDTO.getSex());
            animal.setWeight(animalDTO.getWeight());
            animal.setId(animalDTO.getId());

            if(animal.getId() == null) {
                animalDAO.create(animal);
            } else  {
                animalDAO.update(animal);
            }
        } catch (JDBCException e ){
            throw new AnimalException(e.getSQLException());
        } catch (Exception e ) {
            throw new AnimalException(e);
        }
    }
}
