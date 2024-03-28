package controllers;

import Infrastructure.validarors.StringValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Match;
import models.Pagination;
import services.CompletedMatchesPersistenceService;
import services.ValidationService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CompletedMatchesController", urlPatterns = "/matches")

public class CompletedMatchesController extends HttpServlet {

    int pageSize = 2;
    CompletedMatchesPersistenceService completedMatches = new CompletedMatchesPersistenceService(this.pageSize);
    ValidationService validationService = new ValidationService(new StringValidator());

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int currentPageNumber = request.getParameterMap().containsKey("page") ? Integer.parseInt(request.getParameter("page")) : 1;
        List<Match> getMatches = null;
        try {
            if (request.getParameterMap().containsKey("filter_by_player_name")) {

                String playerName = request.getParameter("filter_by_player_name");
                List<String> violations = this.validationService.validateStr(playerName);
                if (!violations.isEmpty()) {
                    request.setAttribute("violations", violations);
                } else {
                    request.setAttribute("playerName", playerName);
                    getMatches = this.completedMatches.getMatchesByParams(currentPageNumber, playerName);
                }
            } else {
                getMatches = this.completedMatches.getMatchesByPageNumber(currentPageNumber);
            }
        } catch (Exception e) {
            request.setAttribute("exception", "Sorry, no data available. " + e.getMessage());
        }
        Pagination<Match> pagination = this.completedMatches.getPaginateMatches();
        request.setAttribute("matches", getMatches);
        request.setAttribute("pagination", pagination);
        request.getRequestDispatcher("/views/completed-matches.jsp").forward(request, response);
    }
}
