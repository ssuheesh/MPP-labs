<<<<<<< HEAD
package dataaccess;

import java.sql.SQLException;

public interface DataAccess {
	void read(Dao dao) throws SQLException;
	void write(Dao dao) throws SQLException;
	
}
=======
package dataaccess;

import java.sql.SQLException;

public interface DataAccess {
	void read(Dao dao) throws SQLException;
	void write(Dao dao) throws SQLException;
	void createTables(Dao dao) throws SQLException;
	
}
>>>>>>> e8068d26905497c461075f51157d8e57e452821b
