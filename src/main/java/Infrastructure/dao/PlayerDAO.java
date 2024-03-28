package Infrastructure.dao;

import Infrastructure.config.DBService;
import models.Player;
import org.hibernate.Session;

public class PlayerDAO implements iPlayerDAO {

    PlayerDAO() {
    }

    @Override
    public Player getByName(String name) {
        Session session = DBService.getSessionFactory().getCurrentSession();
        return session.createQuery("from Player where name = :name", Player.class)
                .setParameter("name", name).getSingleResult();
    }

}
