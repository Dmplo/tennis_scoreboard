package Infrastructure.dao;

import models.Player;

public interface iPlayerDAO extends iDao {

    Player getByName(String login);
}
