package model;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Customer {

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

	public String insertCustomer(String Customer_name, String Customer_address, String Customer_nic, String Customer_email, String Customer_mobileNo) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into customer1(`Customer_ID`,`Customer_name`,`Customer_address`,`Customer_nic`,`Customer_email`,`Customer_mobileNo`)"
					+ " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, Customer_name);
			preparedStmt.setString(3, Customer_address);
			preparedStmt.setString(4, Customer_nic);
			preparedStmt.setString(5, Customer_email);
			preparedStmt.setString(6, Customer_mobileNo);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the customer.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readCustomer() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Customer ID</th><th>Customer Name</th><th>Customer Address</th><th>Customer NIC</th><th>Customer Email</th><th>Mobile No</th></tr>";
			String query = "select * from customer1";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String Customer_ID = Integer.toString(rs.getInt("Customer_ID"));
				String Customer_name = rs.getString("Customer_name");
				String Customer_address = rs.getString("Customer_address");
				String Customer_nic = rs.getString("Customer_nic");
				String Customer_email = rs.getString("Customer_email");
				String Customer_mobileNo = rs.getString("Customer_mobileNo");

				// Add into the html table
				output += "<tr><td>" + Customer_ID + "</td>";
				output += "<td>" + Customer_name + "</td>";
				output += "<td>" + Customer_address + "</td>";
				output += "<td>" + Customer_nic + "</td>";
				output += "<td>" + Customer_email + "</td>";
				output += "<td>" + Customer_mobileNo + "</td>";
				
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the customer.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateCustomer(String Customer_ID, String Customer_name, String Customer_address, String Customer_nic, String Customer_email, String Customer_mobileNo) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE customer1 SET Customer_name=?,Customer_address=?,Customer_nic=?,Customer_email=?,Customer_mobileNo=?" + "WHERE Customer_ID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, Customer_name);
			preparedStmt.setString(2, Customer_address);
			preparedStmt.setString(3, Customer_nic);
			preparedStmt.setString(4, Customer_email);
			preparedStmt.setString(5, Customer_mobileNo);
			preparedStmt.setInt(6, Integer.parseInt(Customer_ID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the customer.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String deleteCustomer(String Customer_ID) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from customer1 where Customer_ID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(Customer_ID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the customer.";
			System.err.println(e.getMessage());
		}

		return output;
	}

}
