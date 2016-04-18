package lv.autentica.DAO.animal;

import lv.autentica.DAO.BaseDAO;
import lv.autentica.domain.animal.AnimalType;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class AnimalTypeDAO extends BaseDAO {
    public List<AnimalType> getAll () {
        return super.getAll(AnimalType.class);
    }

    public void create(AnimalType animalType) {
        sessionFactory.getCurrentSession()
                .createSQLQuery("CALL ZOO_ADM_UTILITY.ADD_ANIMAL_TYPE(:p_parent_id,:p_type_name, :p_user_id)")
                .setParameter("p_parent_id",animalType.getBaseType().getId())
                .setParameter("p_type_name",animalType.getTypeName())
                .setParameter("p_user_id",22L)
                .executeUpdate();
    }

    public void update(AnimalType animalType) {
        sessionFactory.getCurrentSession()
                .createSQLQuery("CALL ZOO_ADM_UTILITY.UPD_ANIMAL_TYPE(:p_id,:p_parent_id,:p_type_name, :p_user_id)")
                .setParameter("p_id",animalType.getId())
                .setParameter("p_parent_id",animalType.getBaseType().getId())
                .setParameter("p_type_name",animalType.getTypeName())
                .setParameter("p_user_id",22L)
                .executeUpdate();
    }
    public void delete(AnimalType animalType) {
        sessionFactory.getCurrentSession()
                .createSQLQuery("CALL ZOO_ADM_UTILITY.DEL_ANIMAL_TYPE(:p_id)")
                .setParameter("p_id",animalType.getId())
                .executeUpdate();
    }
}
