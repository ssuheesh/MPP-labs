package dataaccess;

import business.AppointmentDao;

import java.sql.Connection;
import java.sql.SQLException;

public interface DataAccess {
	void read(Dao dao) throws SQLException;
	void write(Dao dao) throws SQLException;
	void createTables(Dao dao) throws SQLException;
	Connection getConnection() throws SQLException;
}
