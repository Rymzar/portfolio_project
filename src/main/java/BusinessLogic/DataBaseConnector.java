package BusinessLogic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import io.github.cdimascio.dotenv.Dotenv;

public class DataBaseConnector {

    private final Connection connection;

    public DataBaseConnector() {
        Dotenv dotenv;
        dotenv = Dotenv.configure().load();
        try {
            this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/diplom",
                    dotenv.get("USER"),
                    dotenv.get("PASSWORD"));
            System.out.println("DB: " + connection.getMetaData().getDatabaseProductName());
        } catch (SQLException e){
            throw new RuntimeException("Error while DB connecting...", e);
        }

    }

    public Connection getConnection() {
        return connection;
    }
}
