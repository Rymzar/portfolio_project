package BusinessLogic;


import DataModel.User;

import java.util.ArrayList;
import java.util.Objects;

public class UserService implements Service<User> {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void update(User user){
        repository.update(user);
    }

    @Override
    public void add(User user) {
        repository.save(user);
    }

    @Override
    public ArrayList<User> getAll() {
        return repository.getAll();
    }

    public User getById(String name, String password) {
        ArrayList<User> userList = getAll();
        return userList.stream().filter(user -> Objects.equals(user.getName(), name) && Objects.equals(user.getPassword(), password)).findFirst().orElse(null);
    }

    public User getByName(String name) {
        ArrayList<User> userList = getAll();
        return userList.stream().filter(user -> Objects.equals(user.getName(), name)).findFirst().orElse(null);
    }
}
