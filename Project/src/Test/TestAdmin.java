package Test;

import business.Admin;
import business.AdminDAO;
import java.util.UUID;
public class TestAdmin {
    public static void main(String[] args) {
//        Admin admin = new Admin("1", "admin1");
//        admin.createAdmin();
//        System.out.println(admin);
//        admin.setName("admin1a");
//        admin.updateAdmin();
//        System.out.println(admin);
        String uuid = UUID.randomUUID().toString();

        AdminDAO dao = new AdminDAO();
        Admin admin2 = dao.getAdminById("1");
        System.out.println(admin2);
        Admin admin3 = dao.getAdminById("2");
        System.out.println(admin3);

    }
}
