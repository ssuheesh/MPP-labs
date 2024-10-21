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
<<<<<<< HEAD
=======

    @Override
    public String toString() {
        return this.name + " " + this.id;
    }
>>>>>>> e8068d26905497c461075f51157d8e57e452821b
}
