package business;

import dataaccess.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class AdminDAO implements Dao {

    private Admin admin;
    private List<Admin> allAdmins;
    private String queryString;
    private String insertUpdateQueryString;

    public List<Admin> getAllAdmins() {
        return allAdmins;
    }

    public AdminDAO() {
    }

    public void setAdmin(Admin p) {
        admin = p;
    }

    @Override
    public String getSql() {
        return "SELECT * from ADMIN";
    }

    public void setSql(String queryString) {
        this.queryString = queryString;
    }

    @Override
    public void unpackResultSet(ResultSet rs) throws SQLException {
        allAdmins = new ArrayList<>();
        while (rs.next()) {
            allAdmins.add(new Admin(rs.getString("id"), rs.getString("name")));
        }
    }

    @Override
    public List<?> getResults() {
        return allAdmins;
    }

    @Override
    public String getInsertSql() {
        return insertUpdateQueryString;
    }

    public void setInsertUpdateQueryStringQueryString(String queryString) {
        this.insertUpdateQueryString = queryString;
    }


    @Override
    public void setParameters(PreparedStatement pstmt) throws SQLException {
        if (pstmt.toString().toUpperCase().startsWith("INSERT")) {
            pstmt.setString(1, admin.getId() != null ? admin.getId() : null);
            pstmt.setString(2, admin.getName() != null ? admin.getName() : null);
        }
        if (pstmt.toString().toUpperCase().startsWith("UPDATE")) {
            pstmt.setString(1, admin.getName() != null ? admin.getName() : null);
            pstmt.setString(2, admin.getId() != null ? admin.getId() : null);
        }
    }


    public boolean updateAdmin(Admin admin) {
        boolean flag = false;
        DataAccess dataAccess = DataAccessFactory.getDataAccess();
        try {
            this.admin = admin;
            this.setInsertUpdateQueryStringQueryString("UPDATE ADMIN SET name = ? " +
                    " WHERE id = ? ");
            dataAccess.write(this);
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
        }
        return flag;
    }

    public boolean createAdmin(Admin admin) {
        boolean flag = false;
        DataAccess dataAccess = DataAccessFactory.getDataAccess();

        try {
            this.admin = admin;
            this.setInsertUpdateQueryStringQueryString("INSERT INTO ADMIN" +
                    "(id,name)" +
                    " VALUES " +
                    "(?,?)");
            dataAccess.write(this);
            flag = true;

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
        }
        return flag;
    }

    public Admin getAdminById(String id) {

        try {
            DataAccess dataAccess = DataAccessFactory.getDataAccess();
            this.setSql("SELECT * from ADMIN");
            dataAccess.read(this);
            admin = allAdmins.stream().filter(admin -> admin.getId().equals(id)).findFirst().orElse(null);
            return admin;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}