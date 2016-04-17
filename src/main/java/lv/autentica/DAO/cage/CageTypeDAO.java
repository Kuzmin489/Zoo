package lv.autentica.DAO.cage;

import lv.autentica.DAO.BaseDAO;
import lv.autentica.domain.cages.CageType;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class CageTypeDAO extends BaseDAO {
    public List<CageType> getALL() {
        return super.getAll(CageType.class);
    }

    public void create(CageType cageType) {
        sessionFactory.getCurrentSession()
                .createSQLQuery("CALL ZOO_ADM_UTILITY.ADD_CAGE_TYPE(:p_atp_id,:p_type_name, :p_user_id)")
                .addEntity(CageType.class)
                .setParameter("p_atp_id", cageType.getAnimalType().getId())
                .setParameter("p_type_name", cageType.getCageType())
                .setParameter("p_user_id",2L)
                .executeUpdate();
    }

    public void update(CageType cageType) {
        sessionFactory.getCurrentSession()
                .createSQLQuery("CALL ZOO_ADM_UTILITY.UPD_CAGE_TYPE(:p_id,:p_atp_id,:p_type_name, :p_user_id)")
                .addEntity(CageType.class)
                .setParameter("p_id", cageType.getId())
                .setParameter("p_atp_id", cageType.getAnimalType().getId())
                .setParameter("p_type_name", cageType.getCageType())
                .setParameter("p_user_id",2L)
                .executeUpdate();
    }

    public void delete(CageType cageType) {
        sessionFactory.getCurrentSession()
                .createSQLQuery("CALL ZOO_ADM_UTILITY.DEL_CAGE_TYPE(:p_id)")
                .addEntity(CageType.class)
                .setParameter("p_id", cageType.getId())
                .executeUpdate();
    }
}
