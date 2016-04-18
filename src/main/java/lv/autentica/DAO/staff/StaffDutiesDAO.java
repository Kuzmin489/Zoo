package lv.autentica.DAO.staff;

import lv.autentica.DAO.BaseDAO;
import lv.autentica.domain.staff.StaffDuties;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class StaffDutiesDAO  extends BaseDAO{
    public List<StaffDuties> getAll() {
        return super.getAll(StaffDuties.class);
    }

    public void create(StaffDuties staffDuty) {
        sessionFactory.getCurrentSession()
                .createSQLQuery("CALL ZOO_ADM_UTILITY.ADD_DUTY(:p_stp_id ,:p_description,:p_user_id)")
                .addEntity(StaffDuties.class)
                .setParameter("p_stp_id",staffDuty.getPosition().getId())
                .setParameter("p_description",staffDuty.getDescription())
                .setParameter("p_user_id",22L)
                .executeUpdate();
    }
    public void update(StaffDuties staffDuty) {
        sessionFactory.getCurrentSession()
                .createSQLQuery("CALL ZOO_ADM_UTILITY.UPD_DUTY(:p_id,:p_stp_id ,:p_description,:p_user_id)")
                .addEntity(StaffDuties.class)
                .setParameter("p_id",staffDuty.getId())
                .setParameter("p_stp_id",staffDuty.getPosition().getId())
                .setParameter("p_description",staffDuty.getDescription())
                .setParameter("p_user_id",22L)
                .executeUpdate();
    }

    public void delete(StaffDuties staffDuty) {
        sessionFactory.getCurrentSession()
                .createSQLQuery("CALL ZOO_ADM_UTILITY.DEL_DUTY(:p_id)")
                .addEntity(StaffDuties.class)
                .setParameter("p_id",staffDuty.getId())
                .executeUpdate();
    }
}
