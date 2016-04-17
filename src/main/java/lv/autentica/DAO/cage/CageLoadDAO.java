package lv.autentica.DAO.cage;

import lv.autentica.DAO.BaseDAO;
import lv.autentica.domain.cages.CageLoad;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CageLoadDAO extends BaseDAO {
    public List<CageLoad> getAll() {
        return super.getAll(CageLoad.class);
    }
}
