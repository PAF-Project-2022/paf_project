package model;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Inquiry {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/pafelectricity?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertInquiry(String PersonName, String Area, String Date, String Reason) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into inquiry1(`inqID`,`PersonName`,`Area`,`Date`,`Reason`)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, PersonName);
			preparedStmt.setString(3, Area);
			preparedStmt.setString(4, Date);
			preparedStmt.setString(5, Reason);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the inquiry.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readInquiry() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>ID</th><th>Person Name</th><th>Area</th><th>Inquiry Date</th><th>Inquiry Reason</th></tr>";
			String query = "select * from inquiry1 ";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String inqID = Integer.toString(rs.getInt("inqID"));
				String PersonName = rs.getString("PersonName");
				String Area = rs.getString("Area");
				String Date = rs.getString("Date");
				String Reason = rs.getString("Reason");

				// Add into the html table
				output += "<tr><td>" + inqID + "</td>";
				output += "<td>" + PersonName + "</td>";
				output += "<td>" + Area + "</td>";
				output += "<td>" + Date + "</td>";
				output += "<td>" + Reason + "</td>";
				
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the inquiry.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateInquiry(String inqID, String PersonName, String Area, String Date, String Reason) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE inquiry1 SET PersonName=?,Area=?,Date=?,Reason=?" + "WHERE inqID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, PersonName);
			preparedStmt.setString(2, Area);
			preparedStmt.setString(3, Date);
			preparedStmt.setString(4, Reason);
			preparedStmt.setInt(5, Integer.parseInt(inqID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the inquiry.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String deleteInquiry(String inqID) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from inquiry1 where inqID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(inqID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the inquiry.";
			System.err.println(e.getMessage());
		}

		return output;
	}

}
