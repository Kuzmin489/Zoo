package lv.autentica.service.animal;

import lv.autentica.DAO.animal.AnimalDAO;
import lv.autentica.DAO.animal.AnimalFeedsDAO;
import lv.autentica.DAO.staff.StaffDAO;
import lv.autentica.domain.animal.Animal;
import lv.autentica.domain.animal.AnimalFeeds;
import lv.autentica.domain.staff.Staff;
import lv.autentica.dto.animal.AnimalFeedDTO;
import lv.autentica.exception.AnimalException;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnimalFeedService {

    @Autowired
    AnimalDAO animalDAO;

    @Autowired
    StaffDAO staffDAO;

    @Autowired
    AnimalFeedsDAO animalFeedsDAO;

    public void upsertAnimalFeed(AnimalFeedDTO animalFeedDTO) throws AnimalException {
        AnimalFeeds animalFeed = new AnimalFeeds();
        try {
            animalFeed.setWorker(staffDAO.get(Staff.class,animalFeedDTO.getWorkerId()));
            animalFeed.setAnimal(animalDAO.get(Animal.class,animalFeedDTO.getAnimalId()));
            animalFeed.setId(animalFeedDTO.getId());

            if(animalFeed.getId() == null) {
                animalFeedsDAO.create(animalFeed);
            } else  {
                animalFeedsDAO.update(animalFeed);
            }
        } catch (JDBCException e ){
            throw new AnimalException(e.getSQLException());
        } catch (Exception e ){
            throw new AnimalException(e);
        }
    }
}
