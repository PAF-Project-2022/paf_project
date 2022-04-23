package com;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import model.Billing;

@Path("/Billing")
public class BillingAPI {
	Billing BillObj = new Billing();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readBilling() {
		return BillObj.readBilling();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertBilling(
	 @FormParam("bill_AccNo") String bill_AccNo,		
	 @FormParam("bill_Date") String bill_Date,
	 @FormParam("bill_UnitA") String bill_UnitA,
	 @FormParam("bill_Unitprice") String bill_Unitprice,
	 @FormParam("bill_Total") String bill_Total)
	{
	 String output = BillObj.insertBilling(bill_AccNo, bill_Date, bill_UnitA, bill_Unitprice, bill_Total);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateBilling(String billData)
	{
	//Convert the input string to a JSON object
	 JsonObject bill_Object = new JsonParser().parse(billData).getAsJsonObject();
	//Read the values from the JSON object
	 String biil_ID = bill_Object.get("biil_ID").getAsString();
	 String bill_AccNo = bill_Object.get("bill_AccNo").getAsString();
	 String bill_Date = bill_Object.get("bill_Date").getAsString();
	 String bill_UnitA = bill_Object.get("bill_UnitA").getAsString();
	 String bill_Unitprice = bill_Object.get("bill_Unitprice").getAsString();
	 String bill_Total = bill_Object.get("bill_Total").getAsString();
	 String output = BillObj.updateBilling(biil_ID, bill_AccNo, bill_Date, bill_UnitA, bill_Unitprice, bill_Total);
	return output;
	} 
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteBilling(String billData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(billData, "", Parser.xmlParser());

	//Read the value from the element <ID>
	 String ID = doc.select("biil_ID").text();
	 String output = BillObj.deleteBilling(ID);
	return output;
	}
}
