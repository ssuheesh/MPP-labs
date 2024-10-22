package business;

import dataaccess.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class AdminDAO implements Dao {

    private Admin admin;
    private List<Admin> allAdmins;
    public List<Admin> getAllAdmins() {
        return allAdmins;
    }
    public AdminDAO(){}
    public void setAdmin(Admin p) {
        admin = p;
    }
    @Override
    public String getSql() {
        return "SELECT * from ADMIN";
    }
    @Override
    public void unpackResultSet(ResultSet rs) throws SQLException {
        allAdmins = new ArrayList<>();
        while(rs.next()) {
            allAdmins.add(new Admin(rs.getString("id"), rs.getString("name")));
        }
    }
    @Override
    public List<?> getResults() {
        return allAdmins;
    }

    @Override
    public String getInsertSql() {
        return "";
    }

    @Override
    public void setParameters(PreparedStatement pstmt) throws SQLException {

    }
}