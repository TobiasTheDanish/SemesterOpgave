package dat.backend.model.persistence;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionPool
{
    // TODO: Change access credentials for MySql server as needed below:

    private HikariDataSource ds;
    private static String USER = "dev";
    private static String PASSWORD = "";
    private static String URL = "jdbc:mysql://206.81.31.86:3306/semesteropgave";

    public ConnectionPool()
    {
        this(USER, PASSWORD, URL, false);
    }

    public ConnectionPool(String USER, String PASSWORD, String URL, boolean test)
    {
        String deployed = System.getenv("DEPLOYED");
        if (deployed != null && !test)
        {
            // Prod: hent variabler fra setenv.sh i Tomcats bin folder
            USER = System.getenv("JDBC_USER");
            PASSWORD = System.getenv("JDBC_PASSWORD");
            URL = System.getenv("JDBC_CONNECTION_STRING");
        }

        Logger.getLogger("web").log(Level.INFO,
                String.format("Connection Pool created for: (%s, %s, %s)", USER, PASSWORD, URL));
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl(URL);
        config.setUsername(USER);
        config.setPassword(PASSWORD);
        config.setMaximumPoolSize(5);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        this.ds = new HikariDataSource(config);
    }

    public Connection getConnection() throws SQLException
    {
        Logger.getLogger("web").log(Level.INFO, ": get data connection");
        return ds.getConnection();
    }

    public void close()
    {
        Logger.getLogger("web").log(Level.INFO, "Shutting down connection pool");
        ds.close();
    }

}
