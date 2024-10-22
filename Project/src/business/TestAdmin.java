package business;

public class TestAdmin {
    public static void main(String[] args) {
        Admin admin = new Admin("1", "admin1");
        admin.createAdmin();
        System.out.println(admin);
        admin.setName("admin1a");
        admin.updateAdmin();
        System.out.println(admin);

        AdminDAO dao = new AdminDAO();
        Admin admin2 = dao.getAdminById("1");
        System.out.println(admin2);

    }
}
