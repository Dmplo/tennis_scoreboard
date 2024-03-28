package controllers;


import Infrastructure.entityService.ServiceFactory;
import Infrastructure.entityService.iMatchService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Match;

import java.io.IOException;

@WebServlet(name = "IndexController", value = "")

public class IndexController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            iMatchService matchService = ServiceFactory.getService(iMatchService.class);
            Match lastMatch;
            assert matchService != null;
            lastMatch = matchService.getLastMatch();
            if (lastMatch != null) {
                request.setAttribute("lastMatch", lastMatch);
            } else {
                throw new Exception("Matches is not found");
            }
        } catch (Exception e) {
            request.setAttribute("exception", "Sorry, no data available. " + e.getMessage());
        }
        request.getRequestDispatcher("/views/scoreboard.jsp").forward(request, response);
    }
}
