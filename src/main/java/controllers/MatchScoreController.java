package controllers;

import Infrastructure.validarors.StringValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Match;
import services.CompletedMatchesPersistenceService;
import services.MatchScoreCalculationService;
import services.OngoingMatchesService;
import services.ValidationService;

import java.io.IOException;

@WebServlet(name = "MatchScoreController", urlPatterns = "/match-score")
@MultipartConfig

public class MatchScoreController extends HttpServlet {
    ValidationService validationService = new ValidationService(new StringValidator());
    CompletedMatchesPersistenceService completedMatches = new CompletedMatchesPersistenceService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        final String playerName = request.getParameter("playerName");
        final String uuid = request.getParameter("uuid");
        String json;
        if (this.validationService.validateUUID(uuid)) {
            try {
                json = new ObjectMapper().writeValueAsString(this.getScoreCalc(playerName, uuid));
            } catch (Exception e) {
                json = "{\"error\": \"Sorry, match was stopped for unknown reasons!\"}";
            }
        } else {
            json = "{\"error\": \"Match not found!\"}";
        }
        response.getWriter().write(json);
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        final String uuid = request.getParameter("uuid");

        if (uuid == null || !this.validationService.validateUUID(uuid)) {
            request.setAttribute("exception", "Match not found!");
            request.getRequestDispatcher("/views/match-score.jsp").forward(request, response);
            return;
        }
        request.setAttribute("mapMatches", OngoingMatchesService.getMatches());
        request.getRequestDispatcher("/views/match-score.jsp").forward(request, response);
    }

    public Match getScoreCalc(String playerName, String key) {
        Match match = OngoingMatchesService.getMatchByKey(key);
        MatchScoreCalculationService scoreCalculation = new MatchScoreCalculationService(match, playerName);
        Match calcScoreMatch = scoreCalculation.getScoreCalc();
        if (match.getOver()) {
            this.completedMatches.writeCompletedMatch(match);
            OngoingMatchesService.delMatchByKey(key);
        }
        return calcScoreMatch;
    }
}
