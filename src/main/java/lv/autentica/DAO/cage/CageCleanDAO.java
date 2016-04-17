package lv.autentica.DAO.cage;

import lv.autentica.DAO.BaseDAO;
import lv.autentica.domain.cages.CageClean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class CageCleanDAO extends BaseDAO {
    public List<CageClean> getALL() {
        return super.getAll(CageClean.class);
    }

    public void create(CageClean cageClean) {
        sessionFactory.getCurrentSession()
                .createSQLQuery("CALL ZOO_ADM_UTILITY.ADD_CAGE_CLEAN(:p_staff_id,:p_cage_id, :p_user_id)")
                .addEntity(CageClean.class)
                .setParameter("p_staff_id", cageClean.getWorker().getId())
                .setParameter("p_cage_id", cageClean.getCage().getId())
                .setParameter("p_user_id",2L)
                .executeUpdate();
    }

    public void update(CageClean cageClean) {
        sessionFactory.getCurrentSession()
                .createSQLQuery("CALL ZOO_ADM_UTILITY.UPD_CAGE_CLEAN(:p_id,:p_staff_id,:p_cage_id, :p_user_id)")
                .addEntity(CageClean.class)
                .setParameter("p_id", cageClean.getId())
                .setParameter("p_staff_id", cageClean.getWorker().getId())
                .setParameter("p_cage_id", cageClean.getCage().getId())
                .setParameter("p_user_id",2L)
                .executeUpdate();
    }

    public void delete(CageClean cageClean) {
        sessionFactory.getCurrentSession()
                .createSQLQuery("CALL ZOO_ADM_UTILITY.DEL_CAGE(:p_id)")
                .addEntity(CageClean.class)
                .setParameter("p_id", cageClean.getId())
                .executeUpdate();
    }
}
