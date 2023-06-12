package BusinessLogic;

import DataModel.User;
import DataModel.UserProfile;

import java.util.List;
import java.util.Objects;

public class UserService {

    private UserDao usersDao = new UserDao();

    public UserService() {
    }

    public User findUser(int id) {
        return usersDao.findById(id);
    }

    public void saveUser(User user) {
        usersDao.save(user);
    }

    public void deleteUser(User user) {
        usersDao.delete(user);
    }

    public void updateUser(User user) {
        usersDao.update(user);
    }

    public List<User> findAllUsers() {
        return usersDao.findAll();
    }

    public UserProfile findProfileById(Long id) {
        return usersDao.findProfileById(id);
    }

    public User getById(String name, String password) {
        List<User> userList = findAllUsers();
        return userList.stream().filter(user -> Objects.equals(user.getName(), name) && Objects.equals(user.getPassword(), password)).findFirst().orElse(null);
    }

    public User getByName(String name) {
        List<User> userList = findAllUsers();
        return userList.stream().filter(user -> Objects.equals(user.getName(), name)).findFirst().orElse(null);
    }
}
