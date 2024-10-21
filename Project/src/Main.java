import business.*;
import dataaccess.*;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Dao dao = new AdminDAO();
        DataAccess da = DataAccessFactory.getDataAccess();
        try {
            da.createTables(dao);
            da.read(dao);
            List<Admin> admins = (List<Admin>) dao.getResults();
            //display addresses
            System.out.println(admins);
        } catch (SQLException e) {
            //handle
        }
    }
}