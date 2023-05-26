package BusinessLogic;

import DataModel.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserRepository implements DBRepository<User> {
    private final Connection connection;

    public UserRepository(DataBaseConnector connector){
        this.connection = connector.getConnection();
    }

    @Override
    public void save(User user) {
        String values = String.format("values ('%s', '%s', %b)", user.getName(), user.getPassword(), user.isAdmin());
        String query = "insert into users (name, password, is_admin)\n" + values;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException("Error while method list call: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<User> getAll() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users");
            ResultSet rs = preparedStatement.executeQuery();
            ArrayList<User> userList = new ArrayList<>();
            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                String password = rs.getString("password");
                String document = rs.getString("document");
                boolean isAdmin = rs.getBoolean("is_admin");
                User user = new User(name, id, password, document, isAdmin);
                userList.add(user);
            }
            return userList;
        } catch (SQLException e){
            throw new RuntimeException("Error while method list call: " + e.getMessage());
        }
    }

    @Override
    public void update(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update users set password = ?, name = ?, document = ? where id = ?;");
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getDocument());
            preparedStatement.setLong(4, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException("Error while method list call: " + e.getMessage());
        }
    }
}
