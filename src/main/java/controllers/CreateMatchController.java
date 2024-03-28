package controllers;

import Infrastructure.validarors.StringValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Match;
import services.NewMatchCreatorService;
import services.OngoingMatchesService;
import services.ValidationService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CreateMatchController", urlPatterns = "/new-match")

public class CreateMatchController extends HttpServlet {

    private final NewMatchCreatorService newMatchCreatorService = new NewMatchCreatorService();
    private final ValidationService validationService = new ValidationService(new StringValidator());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstPlayer = request.getParameter("firstplayer");
        String secondPlayer = request.getParameter("secondplayer");

        List<String> violations = this.validationService.validateStr(firstPlayer, secondPlayer);

        if (!violations.isEmpty()) {
            request.setAttribute("violations", violations);
            request.setAttribute("firstPlayer", firstPlayer);
            request.setAttribute("secondPlayer", secondPlayer);

        } else {
            try {
                String uuid = this.newMatchAndGenerateUUID(firstPlayer, secondPlayer);
                response.sendRedirect("/scoreboard/match-score?uuid=" + uuid);
                return;
            } catch (Exception e) {
                request.setAttribute("exception", "Input data is not correct");
            }
        }
        request.getRequestDispatcher("/views/prepare-match.jsp").forward(request, response);
    }

    public String newMatchAndGenerateUUID(String firstPlayerName, String secondPlayerName) {
        Match match = newMatchCreatorService.createMatch(firstPlayerName, secondPlayerName);
        return OngoingMatchesService.createMatchAndUUID(match);
    }
}