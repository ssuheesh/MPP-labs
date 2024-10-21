<<<<<<< HEAD
package dataaccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface Dao {
	public String getSql();
	public void unpackResultSet(ResultSet rs) throws SQLException;
	public List<?> getResults();
}
=======
package dataaccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface Dao {
	public String getSql();
	public void unpackResultSet(ResultSet rs) throws SQLException;
	public List<?> getResults();
}
>>>>>>> e8068d26905497c461075f51157d8e57e452821b
