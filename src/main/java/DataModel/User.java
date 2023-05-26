package DataModel;

public class User {
    private final String name;
    private final long id;
    private final String password;
    private String document;
    private boolean isAdmin;

    public User(String name, long id, String password, String document, boolean isAdmin) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.document = document;
        this.isAdmin = isAdmin;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {return password; }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return this.getName();
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
