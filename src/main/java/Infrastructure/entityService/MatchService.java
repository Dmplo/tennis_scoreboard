package Infrastructure.entityService;


import Infrastructure.config.DBService;
import Infrastructure.dao.DaoFactory;
import Infrastructure.dao.iMatchDAO;
import jakarta.persistence.NoResultException;
import models.Match;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import java.util.List;

public class MatchService implements iMatchService {

    MatchService() {
    }

    @Override
    public long getTotalRecords(String name) {
        Transaction transaction = DBService.getTransaction();
        long totalRecords = -1L;
        try {
            iMatchDAO dao = DaoFactory.getDao(iMatchDAO.class);
            if (dao != null) {
                if (name.isEmpty()) {
                    totalRecords = dao.getTotalRecords();
                } else {
                    totalRecords = dao.getTotalRecordsByName(name);
                }
                transaction.commit();
            }
        } catch (HibernateException | NoResultException | NullPointerException e) {
            DBService.transactionRollback(transaction);
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return totalRecords;
    }

    @Override
    public void create(Match match) {
        Transaction transaction = DBService.getTransaction();
        try {
            iMatchDAO dao = DaoFactory.getDao(iMatchDAO.class);
            if (dao != null) {
                dao.create(match);
                transaction.commit();
            }
        } catch (HibernateException | NoResultException | NullPointerException e) {
            DBService.transactionRollback(transaction);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Match getLastMatch() {
        Transaction transaction = DBService.getTransaction();
        Match match = null;
        try {
            iMatchDAO dao = DaoFactory.getDao(iMatchDAO.class);
            if (dao != null) {
                match = dao.getLastMatch();
                transaction.commit();
            }
        } catch (HibernateException | NoResultException | NullPointerException e) {
            DBService.transactionRollback(transaction);
            throw new RuntimeException(e);
        }
        return match;
    }

    @Override
    public List<Match> getPaginateCondition(int pageNumber, int pageSize) {
        Transaction transaction = DBService.getTransaction();
        List<Match> matches = null;
        try {
            iMatchDAO dao = DaoFactory.getDao(iMatchDAO.class);
            if (dao != null) {
                matches = dao.getPaginateCondition(pageNumber, pageSize);
                transaction.commit();
            }
        } catch (HibernateException | NoResultException | NullPointerException e) {
            DBService.transactionRollback(transaction);
            throw new RuntimeException(e);
        }
        return matches;
    }

    @Override
    public List<Match> getPaginateConditionByName(int pageNumber, int pageSize, String name) {
        Transaction transaction = DBService.getTransaction();
        List<Match> matches = null;
        try {
            iMatchDAO dao = DaoFactory.getDao(iMatchDAO.class);
            if (dao != null) {
                matches = dao.getPaginateConditionByName(pageNumber, pageSize, name);
                transaction.commit();
            }
        } catch (HibernateException | NoResultException | NullPointerException e) {
            DBService.transactionRollback(transaction);
            throw new RuntimeException(e);
        }
        return matches;
    }

}