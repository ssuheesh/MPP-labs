package business;

public class Admin {
    private String id;
    private String name;
    public Admin(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}