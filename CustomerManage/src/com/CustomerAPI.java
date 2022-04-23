package com;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import model.Customer;

@Path("/Customer")
public class CustomerAPI {
	Customer CustomerObj = new Customer();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readCustomer() {
		return CustomerObj.readCustomer();
	}
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertCustomer(@FormParam("Customer_name") String Customer_name,			
	 @FormParam("Customer_address") String Customer_address,
	 @FormParam("Customer_nic") String Customer_nic,
	 @FormParam("Customer_email") String Customer_email,
	 @FormParam("Customer_mobileNo") String Customer_mobileNo)
	{
	 String output = CustomerObj.insertCustomer(Customer_name, Customer_address, Customer_nic, Customer_email, Customer_mobileNo);
	return output;
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateCustomer(String CustomerData)
	{
	//Convert the input string to a JSON Object
	 JsonObject Customer_Object = new JsonParser().parse(CustomerData).getAsJsonObject();
	//Read the values from the JSON object
	 String Customer_ID = Customer_Object.get("Customer_ID").getAsString();
	 String Customer_name = Customer_Object.get("Customer_name").getAsString();
	 String Customer_address = Customer_Object.get("Customer_address").getAsString();
	 String Customer_nic = Customer_Object.get("Customer_nic").getAsString();
	 String Customer_email = Customer_Object.get("Customer_email").getAsString();
	 String Customer_mobileNo = Customer_Object.get("Customer_mobileNo").getAsString();
	 String output = CustomerObj.updateCustomer(Customer_ID, Customer_name, Customer_address, Customer_nic, Customer_email, Customer_mobileNo);
	return output;
	} 
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteCustomer(String CustomerData)
	{
	//Convert the input string to an XML Document
	 Document doc = Jsoup.parse(CustomerData, "", Parser.xmlParser());

	//Read the value from the element <ID>
	 String Customer_ID = doc.select("Customer_ID").text();
	 String output = CustomerObj.deleteCustomer(Customer_ID);
	return output;
	}
	
}
