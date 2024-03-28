package Infrastructure.entityService;

import Infrastructure.config.DBService;
import Infrastructure.dao.DaoFactory;
import Infrastructure.dao.iPlayerDAO;
import jakarta.persistence.NoResultException;
import models.Player;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

public class PlayerService implements iPlayerService {
    PlayerService() {
    }

    @Override
    public Player getPlayerByName(String name) {
        Transaction transaction = DBService.getTransaction();
        Player player = null;
        try {
            iPlayerDAO dao = DaoFactory.getDao(iPlayerDAO.class);
            if (dao != null) {
                player = dao.getByName(name);
                transaction.commit();
            }
        } catch (HibernateException | NoResultException | NullPointerException e) {
            DBService.transactionRollback(transaction);
            throw new RuntimeException(e);
        }
        return player;
    }

}