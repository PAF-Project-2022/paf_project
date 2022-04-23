package model;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Billing {

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

	
	public String insertBilling(String AccNo, String Date, String Unit, String UnitPrice, String Total) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into billing1(`biil_ID`,`bill_AccNo`,`bill_Date`,`bill_UnitA`,`bill_Unitprice`,`bill_Total`)"
					+ " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, AccNo);
			preparedStmt.setString(3, Date);
			preparedStmt.setString(4, Unit);
			preparedStmt.setString(5, UnitPrice);
			preparedStmt.setString(6, Total);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the billing.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readBilling() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Bill ID</th><th>Eletricity Account No</th><th>Date</th><th>Unit Amount</th><th>PreUnit Price</th><th>Total Price</th></tr>";
			String query = "select * from billing1";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String ID = Integer.toString(rs.getInt("biil_ID"));
				String AccNo = rs.getString("bill_AccNo");
				String Date = rs.getString("bill_Date");
				String Unit = rs.getString("bill_UnitA");
				String UnitPrice = rs.getString("bill_Unitprice");
				String Total = rs.getString("bill_Total");

				// Add into the html table
				output += "<tr><td>" + ID + "</td>";
				output += "<td>" + AccNo + "</td>";
				output += "<td>" + Date + "</td>";
				output += "<td>" + Unit + "</td>";
				output += "<td>" + UnitPrice + "</td>";
				output += "<td>" + Total + "</td>";
				
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the billing.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateBilling(String ID, String AccNo, String Date, String Unit, String UnitPrice, String Total) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE billing1 SET bill_AccNo=?,bill_Date=?,bill_UnitA=?,bill_Unitprice=?,bill_Total=?" + "WHERE biil_ID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, AccNo);
			preparedStmt.setString(2, Date);
			preparedStmt.setString(3, Unit);
			preparedStmt.setString(4, UnitPrice);
			preparedStmt.setString(5, Total);
			preparedStmt.setInt(6, Integer.parseInt(ID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the billing.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String deleteBilling(String ID) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from billing1 where biil_ID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(ID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the billing.";
			System.err.println(e.getMessage());
		}

		return output;
	}

}
