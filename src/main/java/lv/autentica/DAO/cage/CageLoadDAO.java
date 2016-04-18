package lv.autentica.DAO.cage;

import lv.autentica.DAO.BaseDAO;
import lv.autentica.domain.cages.CageLoad;
import lv.autentica.dto.cage.CageLoadDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CageLoadDAO extends BaseDAO {
    public List<CageLoad> getAll() {
        return super.getAll(CageLoad.class);
    }

    public void create(CageLoad cageLoad) {
        sessionFactory.getCurrentSession()
                .createSQLQuery("CALL ZOO_ADM_UTILITY.ADD_CAGE_LOAD(:p_cage_id,:p_animals, :p_user_id)")
                .addEntity(CageLoad.class)
                .setParameter("p_cage_id", cageLoad.getCage().getId())
                .setParameter("p_animals", cageLoad.getAnimalsList())
                .setParameter("p_user_id",22L)
                .executeUpdate();
    }

    public void update(CageLoad cageLoad) {
        sessionFactory.getCurrentSession()
                .createSQLQuery("CALL ZOO_ADM_UTILITY.UPD_CAGE_LOAD(:p_id,:p_cage_id,:p_animals, :p_user_id)")
                .addEntity(CageLoad.class)
                .setParameter("p_id",cageLoad.getId())
                .setParameter("p_cage_id", cageLoad.getCage().getId())
                .setParameter("p_animals", cageLoad.getAnimalsList())
                .setParameter("p_user_id",22L)
                .executeUpdate();
    }

    public void delete(CageLoad cageLoad) {
        sessionFactory.getCurrentSession()
                .createSQLQuery("CALL ZOO_ADM_UTILITY.DEL_CAGE_LOAD(:p_id)")
                .addEntity(CageLoad.class)
                .setParameter("p_id",cageLoad.getId())
                .executeUpdate();
    }
}
