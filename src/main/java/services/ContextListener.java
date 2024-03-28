package services;

import Infrastructure.config.DBService;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        if (DBService.isTesting()) {
            CreateMatchesService create = new CreateMatchesService();
            create.addMatches();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        DBService.close();
    }
}