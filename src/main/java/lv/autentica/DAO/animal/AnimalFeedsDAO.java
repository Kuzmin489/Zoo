package lv.autentica.DAO.animal;

import lv.autentica.DAO.BaseDAO;
import lv.autentica.domain.animal.AnimalFeeds;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class AnimalFeedsDAO extends BaseDAO {

    public List<AnimalFeeds> getAll() {
        return super.getAll(AnimalFeeds.class);
    }

    public void create (AnimalFeeds animalFeed) {
        sessionFactory.getCurrentSession()
                .createSQLQuery("CALL ZOO_ADM_UTILITY.ADD_ANIMAL_FEED(:p_animal_id,:p_staff_id, :p_user_id)")
                .setParameter("p_animal_id",animalFeed.getAnimal().getId())
                .setParameter("p_staff_id",animalFeed.getWorker().getId())
                .setParameter("p_user_id",2L)
                .executeUpdate();
    }

    public void update (AnimalFeeds animalFeed) {
        sessionFactory.getCurrentSession()
                .createSQLQuery("CALL ZOO_ADM_UTILITY.UPD_ANIMAL_FEED(:p_id, :p_animal_id,:p_staff_id, :p_user_id)")
                .setParameter("p_id",animalFeed.getId())
                .setParameter("p_animal_id",animalFeed.getAnimal().getId())
                .setParameter("p_staff_id",animalFeed.getWorker().getId())
                .setParameter("p_user_id",2L)
                .executeUpdate();
    }

    public void delete (AnimalFeeds animalFeed) {
        sessionFactory.getCurrentSession()
                .createSQLQuery("CALL ZOO_ADM_UTILITY.DEL_ANIMAL_FEED(:p_id)")
                .setParameter("p_id",animalFeed.getId())
                .executeUpdate();
    }


}
