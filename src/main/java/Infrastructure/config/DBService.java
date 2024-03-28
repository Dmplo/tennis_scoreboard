package Infrastructure.config;

import models.Match;
import models.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.hibernate.service.ServiceRegistry;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class DBService {

    private static String hibernate_hbm2ddl_auto = null;

    private static final String path = "/database.properties";

    private static SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = getConfiguration();
            sessionFactory = createSessionFactory(configuration);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Transaction getTransaction() {
        Session session = DBService.getSessionFactory().getCurrentSession();
        Transaction transaction = DBService.getSessionFactory().getCurrentSession().getTransaction();
        if (!transaction.isActive()) {
            transaction = session.beginTransaction();
        }
        return transaction;
    }

    public static void transactionRollback(Transaction transaction) {
        if (transaction.getStatus() == TransactionStatus.ACTIVE
                || transaction.getStatus() == TransactionStatus.MARKED_ROLLBACK) {
            transaction.rollback();
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private static Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        addAnnotatedClassToConfiguration(configuration);

        try (InputStream is = DBService.class.getResourceAsStream(path)) {
            Properties props = new Properties();
            props.load(is);

            configuration.setProperty("hibernate.dialect", props.getProperty("hibernate.dialect"));
            configuration.setProperty("hibernate.connection.driver_class", props.getProperty("hibernate.connection.driver_class"));
            configuration.setProperty("hibernate.connection.url", props.getProperty("hibernate.connection.url"));
            configuration.setProperty("hibernate.connection.username", props.getProperty("hibernate.connection.username"));
            configuration.setProperty("hibernate.connection.password", props.getProperty("hibernate.connection.password"));
            configuration.setProperty("hibernate.show_sql", props.getProperty("hibernate.show_sql"));
            configuration.setProperty("hibernate.hbm2ddl.auto", props.getProperty("hibernate.hbm2ddl.auto"));
            configuration.setProperty("hibernate.connection.pool_size", props.getProperty("hibernate.connection.pool_size"));
            configuration.setProperty("hibernate.current_session_context_class", "thread");

            hibernate_hbm2ddl_auto = configuration.getProperty("hibernate.hbm2ddl.auto");

        } catch (IOException e) {
            throw new RuntimeException("Invalid config file " + path);
        }
        return configuration;
    }

    private static void addAnnotatedClassToConfiguration(Configuration configuration) {
        configuration.addAnnotatedClass(Player.class)
                .addAnnotatedClass(Match.class);

    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    public static void close(){
        sessionFactory.close();
    }
    public static boolean isTesting(){
        return hibernate_hbm2ddl_auto.equalsIgnoreCase("create")
                || hibernate_hbm2ddl_auto.equalsIgnoreCase("create-drop");
    }
}