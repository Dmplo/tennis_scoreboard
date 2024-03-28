package services;

import Infrastructure.entityService.ServiceFactory;
import Infrastructure.entityService.iMatchService;
import jakarta.servlet.http.HttpServlet;
import models.Match;
import models.Pagination;

import java.util.List;

public class CompletedMatchesPersistenceService extends HttpServlet {
    private final iMatchService matchService = ServiceFactory.getService(iMatchService.class);
    private Pagination<Match> paginateMatches;
    int pageSize;

    public CompletedMatchesPersistenceService(int pageSize) {
        this.pageSize = pageSize;
        this.paginateMatches = new Pagination<>();
    }

    public CompletedMatchesPersistenceService() {
    }

    public void init(String playerName, int pageNumber) {
        int lastPageNumber;
        long totalRecords;

        assert this.matchService != null;
        totalRecords = this.matchService.getTotalRecords(playerName);

        if (totalRecords == 0) {
            throw new RuntimeException(String.format("Player name \"%s\" is not found", playerName));
        }
        if (totalRecords + 1 >= (long) pageNumber * pageSize) {
            if (totalRecords % this.pageSize == 0) {
                lastPageNumber = (int) (totalRecords / this.pageSize);
            } else {
                lastPageNumber = (int) (totalRecords / this.pageSize) + 1;
            }
            this.paginateMatches.setCurrentPageNumber(pageNumber);
            this.paginateMatches.setPageSize(this.pageSize);
            this.paginateMatches.setLastPageNumber(lastPageNumber);
            this.paginateMatches.setTotalRecords(totalRecords);
        } else {
            throw new RuntimeException("Not valid page number");
        }
    }

    public void updateMatchesByCondition(int pageNumber) {
        this.init("", pageNumber);
        assert this.matchService != null;
        List<Match> matches = this.matchService.getPaginateCondition(this.paginateMatches.getCurrentPageNumber(), this.paginateMatches.getPageSize());
        this.paginateMatches.setRecords(matches);
    }

    public void updateMatchesByCondition(String name, int pageNumber) {
        this.init(name, pageNumber);
        assert this.matchService != null;
        List<Match> matches = this.matchService.getPaginateConditionByName(this.paginateMatches.getCurrentPageNumber(), this.paginateMatches.getPageSize(), name);
        this.paginateMatches.setRecords(matches);
    }

    public List<Match> getMatchesByPageNumber(int pageNumber) {
        this.updateMatchesByCondition(pageNumber);
        return this.paginateMatches.getRecords();
    }

    public List<Match> getMatchesByParams(int pageNumber, String playerName) {
        this.updateMatchesByCondition(playerName, pageNumber);
        return this.paginateMatches.getRecords();
    }

    public void writeCompletedMatch(Match match) {
        assert this.matchService != null;
        this.matchService.create(match);
    }

    public Pagination<Match> getPaginateMatches() {
        return paginateMatches;
    }
}
