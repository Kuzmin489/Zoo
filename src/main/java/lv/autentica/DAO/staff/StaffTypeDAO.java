package lv.autentica.DAO.staff;

import lv.autentica.DAO.BaseDAO;
import lv.autentica.domain.staff.StaffType;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class StaffTypeDAO extends BaseDAO {

    public List<StaffType> getAll() {
        return super.getAll(StaffType.class);
    }

    public void create(StaffType staffType) {
        sessionFactory.getCurrentSession()
                .createSQLQuery("CALL ZOO_ADM_UTILITY.ADD_STAFF_TYPE(:p_parent_id , :p_type_name, :p_user_id)")
                .addEntity(StaffType.class)
                .setParameter("p_parent_id", staffType.getManager().getId())
                .setParameter("p_type_name", staffType.getTypeName())
                .setParameter("p_user_id",22L)
                .executeUpdate();
    }

    public void update(StaffType staffType) {
        sessionFactory.getCurrentSession()
                .createSQLQuery("CALL ZOO_ADM_UTILITY.UPD_STAFF_TYPE(:p_id,:p_parent_id , :p_type_name, :p_user_id)")
                .addEntity(StaffType.class)
                .setParameter("p_id",staffType.getId())
                .setParameter("p_parent_id", staffType.getManager().getId())
                .setParameter("p_type_name", staffType.getTypeName())
                .setParameter("p_user_id",22L)
                .executeUpdate();
    }

    public void delete(StaffType staffType) {
        sessionFactory.getCurrentSession()
                .createSQLQuery("CALL ZOO_ADM_UTILITY.DEL_STAFF_TYPE(:p_id)")
                .addEntity(StaffType.class)
                .setParameter("p_id",staffType.getId())
                .executeUpdate();
    }
}
