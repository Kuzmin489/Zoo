package lv.autentica.DAO.cage;

import lv.autentica.DAO.BaseDAO;
import lv.autentica.domain.cages.Cage;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by german on 4/9/16 for MyTi project.
 */
@Component
@Transactional
public class CageDAO extends BaseDAO {
    public List<Cage> getAll() {
        return super.getAll(Cage.class);
    }

    public void create(Cage cage) {
        sessionFactory.getCurrentSession()
                .createSQLQuery("CALL ZOO_ADM_UTILITY.ADD_CAGE(:p_type_id,:p_name,:p_capacity, :p_user_id)")
                .addEntity(Cage.class)
                .setParameter("p_type_id", cage.getCageType().getId())
                .setParameter("p_name", cage.getName())
                .setParameter("p_capacity",cage.getCapacity())
                .setParameter("p_user_id",2L)
                .executeUpdate();
    }

    public void update(Cage cage) {
        sessionFactory.getCurrentSession()
                .createSQLQuery("CALL ZOO_ADM_UTILITY.UPD_CAGE(:p_id,:p_type_id,:p_name,:p_capacity, :p_user_id)")
                .addEntity(Cage.class)
                .setParameter("p_id", cage.getId())
                .setParameter("p_type_id", cage.getCageType().getId())
                .setParameter("p_name", cage.getName())
                .setParameter("p_capacity",cage.getCapacity())
                .setParameter("p_user_id",2L)
                .executeUpdate();
    }

    public void delete(Cage cage) {
        sessionFactory.getCurrentSession()
                .createSQLQuery("CALL ZOO_ADM_UTILITY.DEL_CAGE(:p_id)")
                .addEntity(Cage.class)
                .setParameter("p_id", cage.getId())
                .executeUpdate();
    }
}
