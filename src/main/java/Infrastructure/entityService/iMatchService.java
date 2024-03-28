package Infrastructure.entityService;

import models.Match;

import java.util.List;

public interface iMatchService extends iService {

    long getTotalRecords(String name);

    void create(Match match);

    Match getLastMatch();

    List<Match> getPaginateCondition(int pageNumber, int pageSize);

    List<Match> getPaginateConditionByName(int pageNumber, int pageSize, String name);
}
