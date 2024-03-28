package Infrastructure.entityService;

import models.Player;

public interface iPlayerService extends iService {

    Player getPlayerByName(String name);

}
