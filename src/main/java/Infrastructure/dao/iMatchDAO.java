package Infrastructure.dao;


import models.Match;

import java.util.List;

public interface iMatchDAO extends iDao {

    Match getLastMatch();

    long getTotalRecords();
    long getTotalRecordsByName(String name);

    void create(Match purchase);

    List<Match> getPaginateCondition(int pageNumber, int pageSize);

    List<Match> getPaginateConditionByName(int pageNumber, int pageSize, String name);
}
