package business;

public class Admin {
    static AdminDAO dao = new AdminDAO();
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

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public boolean createAdmin() {
        return dao.createAdmin(this);
    }

    public boolean updateAdmin() {
        return dao.updateAdmin(this);
    }
}
