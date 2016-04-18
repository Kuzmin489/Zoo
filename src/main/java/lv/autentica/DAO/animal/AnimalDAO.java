package lv.autentica.DAO.animal;

import lv.autentica.DAO.BaseDAO;
import lv.autentica.domain.animal.Animal;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class AnimalDAO extends BaseDAO {
    public List<Animal> getAll() {
        return super.getAll(Animal.class);
    }

    public void create(Animal animal) {
        sessionFactory.getCurrentSession().
                createSQLQuery("CALL ZOO_ADM_UTILITY.ADD_ANIMAL(:p_type_id ,:p_name ,:p_sex ," +
                        ":p_age_years ,:p_weight_kg ,:p_features ,:p_comments,:p_user_id)")
                .setParameter("p_type_id",animal.getAnimalType().getId())
                .setParameter("p_name",animal.getName())
                .setParameter("p_sex",animal.getSex())
                .setParameter("p_age_years", animal.getAge())
                .setParameter("p_weight_kg",animal.getWeight())
                .setParameter("p_features",animal.getFeatures())
                .setParameter("p_comments",animal.getComments())
                .setParameter("p_user_id",22L)
                .executeUpdate();
    }

    public void update(Animal animal) {
        sessionFactory.getCurrentSession().
                createSQLQuery("CALL ZOO_ADM_UTILITY.UPD_ANIMAL(:p_id,:p_type_id ,:p_name ,:p_sex ," +
                        ":p_age_years ,:p_weight_kg ,:p_features ,:p_comments,:p_user_id)")
                .setParameter("p_id",animal.getId())
                .setParameter("p_type_id",animal.getAnimalType().getId())
                .setParameter("p_name",animal.getName())
                .setParameter("p_sex",animal.getSex())
                .setParameter("p_age_years", animal.getAge())
                .setParameter("p_weight_kg",animal.getWeight())
                .setParameter("p_features",animal.getFeatures())
                .setParameter("p_comments",animal.getComments())
                .setParameter("p_user_id",22L)
                .executeUpdate();
    }
    public void delete(Animal animal) {
        sessionFactory.getCurrentSession().
                createSQLQuery("CALL ZOO_ADM_UTILITY.DEL_ANIMAL(:p_id)")
                .setParameter("p_id",animal.getId())
                .executeUpdate();
    }

}
