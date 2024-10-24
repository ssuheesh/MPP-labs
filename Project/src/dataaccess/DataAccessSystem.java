package dataaccess;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;


public class DataAccessSystem implements DataAccess {
	//package level access
	DataAccessSystem() {}

	public Connection getConnection() throws SQLException {
		return new ConnectManager().getConnection();
	}
	public void read(Dao dao) throws SQLException {
		String query = dao.getSql();
		Connection con = null;
		try {
			con = (new ConnectManager()).getConnection();

			Statement stmt = con.createStatement();

			//System.out.println("the query: "+ query);
			ResultSet rs = stmt.executeQuery(query);
			dao.unpackResultSet(rs);
		} finally {
//			if(con != null) {
//				try {
//				 con.close();
//				} catch(Exception e) {
//					//do nothing
//				}
//			}
		}
	}


	public void write(Dao dao) throws SQLException {
		String insertSql = dao.getInsertSql();
		try (Connection con = getConnection();
			 PreparedStatement pstmt = con.prepareStatement(insertSql)) {

			dao.setParameters(pstmt);
			pstmt.executeUpdate();
		}
	}

	public void createTables(Dao dao) throws SQLException {

		var createAdminSql = "CREATE TABLE IF NOT EXISTS ADMIN ("
				+ "	id text PRIMARY KEY,"
				+ "	name text NOT NULL"
				+ ");";

		var createDoctorScheduleSql = "CREATE TABLE IF NOT EXISTS DOCTORSCHEDULE ("
				+ "	id text PRIMARY KEY,"
				+ "	availableDay text NOT NULL,"
				+ "	slotNumber int NOT NULL,"
				+ "	doctorId int NOT NULL,"
				+ "	appointmentId int"
				+ ");";

		var createPatientSql = "CREATE TABLE IF NOT EXISTS PATIENT ("
				+ "	patientId text PRIMARY KEY,"
				+ "	firstName text,"
				+ "	lastName text,"
				+ "	contactNumber text,"
				+ "	address text,"
				+ "	birthDate text,"
				+ "	gender text"
				+ ");";

		var createPatientHistorySql = "CREATE TABLE IF NOT EXISTS PATIENT_HISTORY ("
				+ "	historyId text PRIMARY KEY,"
				+ "	patientId text,"
				+ "	visitDate text,"
				+ "	syndrome text,"
				+ "	prescription text"
				+ ");";

		var createStaffSql  = "create table if not exists STAFF\n" +
				"(\n" +
				"    staffId       INTEGER not null\n" +
				"        primary key autoincrement\n" +
				"        unique,\n" +
				"    name          TEXT,\n" +
				"    role          TEXT,\n" +
				"    joinDate      REAL,\n" +
				"    contactNumber TEXT,\n" +
				"    specialist    text\n" +
				");\n";
		var createAppointmentSql = "create table if not exists APPOINTMENT\n" +
				"(\n" +
				"    appointmentid  INTEGER\n" +
				"        primary key autoincrement,\n" +
				"    date           REAL,\n" +
				"    slotOfTheDay   INTEGER,\n" +
				"    visitReason    TEXT,\n" +
				"    status         TEXT,\n" +
				"    patient        TEXT,\n" +
				"    doctorschedule text\n" +
				");";
		Connection con = null;
		try {
			con = (new ConnectManager()).getConnection();

			Statement stmt = con.createStatement();

			System.out.println("the query: "+ createAdminSql);
			Boolean isSuccess = stmt.execute(createAdminSql);
			System.out.println(isSuccess);

			System.out.println("the query: "+ createDoctorScheduleSql);
			stmt.execute(createDoctorScheduleSql);

			System.out.println("The query: " + createPatientSql);
			Boolean isPatientSuccess = stmt.execute(createPatientSql);
			System.out.println("Patient table created: " + isPatientSuccess);

			System.out.println("The query: " + createPatientHistorySql);
			Boolean isPatientHistorySuccess = stmt.execute(createPatientHistorySql);
			System.out.println("Patient table created: " + isPatientHistorySuccess);


			System.out.println("The query: " + createStaffSql);
			Boolean isStaffSuccess = stmt.execute(createStaffSql);
			System.out.println("Staff table created: " + isStaffSuccess);


			System.out.println("The query: " + createAppointmentSql);
			Boolean isAppointmentSuccess = stmt.execute(createAppointmentSql);
			System.out.println("Appointment table created: " + isAppointmentSuccess);
		} finally {
		}
	}


	public static class ConnectManager {

		private static Connection conn = null;

		public Connection getConnection() throws SQLException {
			if (conn == null) {
				// Load properties file
				Properties properties = new Properties();
				try (InputStream input = ConnectManager.class.getClassLoader().getResourceAsStream("config.properties")) {
					if (input == null) {
						System.out.println("Sorry, unable to find config.properties");
						return null;
					}
					properties.load(input);
				} catch (IOException e) {
					e.printStackTrace();
					return null;
				}

				String dburl = properties.getProperty("db.url");
//				String dburl = "jdbc:sqlite:hospital.sqlite";
	            if (dburl != null) {
	                // Create a connection to the database
//	                conn = DriverManager.getConnection(dburl);
					Connection conn = DriverManager.getConnection(dburl);
					//System.out.println("Connection to SQLite has been established.");
					return conn;

	            } else {
	                System.out.println("Database URL not found in config.properties");
					return null;
	            }
	        }
	        return conn;

		}
	}
}