package Infrastructure.dao;

import Infrastructure.config.DBService;
import models.Match;
import org.hibernate.Session;
import java.util.List;

public class MatchDAO implements iMatchDAO {

    MatchDAO() {
    }

    @Override
    public Match getLastMatch() {
        return DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from Match ORDER BY id DESC", Match.class).setMaxResults(1).uniqueResult();
    }

    @Override
    public long getTotalRecords() {
        return DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("SELECT COUNT (e.id) FROM Match e", Long.class).getSingleResult();
    }
    @Override
    public long getTotalRecordsByName(String name) {
        return DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("SELECT COUNT (e.id) FROM Match e WHERE lower(firstPlayer.name) LIKE :name OR lower(secondPlayer.name) LIKE :name", Long.class)
                .setParameter("name", "%" + name.toLowerCase() + "%").getSingleResult();
    }

    @Override
    public void create(Match match) {
        Session session = DBService.getSessionFactory().getCurrentSession();
        session.merge(match);
        session.flush();
    }

    public List<Match> getPaginateCondition(int pageNumber, int pageSize) {
        return DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("FROM Match e ORDER BY e.id", Match.class)
                .setFirstResult((pageNumber - 1) * pageSize).setMaxResults(pageSize).getResultList();
    }
    public List<Match> getPaginateConditionByName(int pageNumber, int pageSize, String name) {
        return DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("FROM Match e WHERE lower(firstPlayer.name) LIKE :name OR lower(secondPlayer.name) LIKE :name", Match.class)
                .setParameter("name", "%" + name.toLowerCase() + "%").setFirstResult((pageNumber - 1) * pageSize).setMaxResults(pageSize).getResultList();
    }

}
