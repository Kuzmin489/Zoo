package lv.autentica.DAO.staff;

import lv.autentica.DAO.BaseDAO;
import lv.autentica.domain.staff.Staff;
import org.hibernate.CacheMode;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class StaffDAO extends BaseDAO {

    public List<Staff> getAll() {
        return super.getAll(Staff.class);
    }

    public void create(Staff worker) {
        sessionFactory.getCurrentSession()
                .createSQLQuery("CALL ZOO_ADM_UTILITY.ADD_STAFF(:p_type_id, :p_first_name, :p_last_name," +
                        ":p_soc_sec_num,:p_address,:p_phone,:p_salary, :p_user_id )")
                .addEntity(Staff.class)
                .setParameter("p_type_id", worker.getPosition().getId())
                .setParameter("p_first_name", worker.getFirstName())
                .setParameter("p_last_name", worker.getLastName())
                .setParameter("p_soc_sec_num",worker.getSsnNumber())
                .setParameter("p_address",worker.getAddress())
                .setParameter("p_phone",worker.getPhone())
                .setParameter("p_salary",worker.getSalary())
                .setParameter("p_user_id",22L)
                .executeUpdate();
    }

    public void update(Staff worker) {
        sessionFactory.getCurrentSession()
                .createSQLQuery("CALL ZOO_ADM_UTILITY.UPD_STAFF(:p_id,:p_type_id, :p_first_name, :p_last_name," +
                        ":p_soc_sec_num,:p_address,:p_phone,:p_salary,:p_user_id  )")
                .addEntity(Staff.class)
                .setParameter("p_id",worker.getId())
                .setParameter("p_type_id", worker.getPosition().getId())
                .setParameter("p_first_name", worker.getFirstName())
                .setParameter("p_last_name", worker.getLastName())
                .setParameter("p_soc_sec_num",worker.getSsnNumber())
                .setParameter("p_address",worker.getAddress())
                .setParameter("p_phone",worker.getPhone())
                .setParameter("p_salary",worker.getSalary())
                .setParameter("p_user_id",22L)
                .executeUpdate();
    }

    public void delete(Staff worker) {
        sessionFactory.getCurrentSession()
                .createSQLQuery("CALL ZOO_ADM_UTILITY.DEL_STAFF(:p_id)")
                .addEntity(Staff.class)
                .setParameter("p_id",worker.getId())
                .executeUpdate();
    }
}
