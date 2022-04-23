package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Payment {

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
	public String insertPayment(String Payment_AccountNO, String Payment_CName, String Payment_Date, String Payment_TotalAmount) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into payment1(`payment_ID`,`Payment_AccountNO`,`Payment_CName`,`Payment_Date`,`Payment_TotalAmount`)" + " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, Payment_AccountNO);
			preparedStmt.setString(3, Payment_CName);
			preparedStmt.setString(4, Payment_Date);
			preparedStmt.setString(5, Payment_TotalAmount);

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the Payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readPayment() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Payment ID</th><th>Account No</th><th>Customer Name</th><th>Pay Date</th><th>Total Amount</th></tr>";
			String query = "select * from payment1";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String payment_ID = Integer.toString(rs.getInt("payment_ID"));
				String Payment_AccountNO = rs.getString("Payment_AccountNO");
				String Payment_CName = rs.getString("Payment_CName");
				String Payment_Date = rs.getString("Payment_Date");
				String Payment_TotalAmount = Float.toString(rs.getFloat("Payment_TotalAmount"));
				
				output += "<tr><td>" + payment_ID + "</td>";
				output += "<td>" + Payment_AccountNO + "</td>";
				output += "<td>" + Payment_CName + "</td>";
				output += "<td>" + Payment_Date + "</td>";
				output += "<td>" + Payment_TotalAmount + "</td>";
				
			}
			con.close();
			
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updatePayment(String payment_ID, String Payment_AccountNO,String Payment_CName, String Payment_Date, String Payment_TotalAmount) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE payment1 SET Payment_AccountNO=?,Payment_CName=?,Payment_Date=?,Payment_TotalAmount=? WHERE payment_ID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values

			preparedStmt.setString(1, Payment_AccountNO);
			preparedStmt.setString(2, Payment_CName);
			preparedStmt.setString(3, Payment_Date);
			preparedStmt.setString(4, Payment_TotalAmount);
			preparedStmt.setInt(5, Integer.parseInt(payment_ID));
			

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the Payment.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String deletePayment(String payment_ID) {

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from payment1 where payment_ID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(payment_ID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the Payment.";
			System.err.println(e.getMessage());
		}

		return output;
	}

}
