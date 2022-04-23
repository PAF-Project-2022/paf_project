package com;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import model.Inquiry;

@Path("/Inquiry")
public class InquiryAPI {
	Inquiry InquiryObj = new Inquiry();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readInquiry() {
		return InquiryObj.readInquiry();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertInquiry(
	 @FormParam("PersonName") String PersonName,			
	 @FormParam("Area") String Area,
	 @FormParam("Date") String Date,
	 @FormParam("Reason") String Reason)
	{
	 String output = InquiryObj.insertInquiry(PersonName, Area, Date, Reason);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateInquiry(String inquiryData)
	{
	//Convert the input string to a JSON object
	 JsonObject inqObject = new JsonParser().parse(inquiryData).getAsJsonObject();
	//Read the values from the JSON object
	 String inqID = inqObject.get("inqID").getAsString();
	 String PersonName = inqObject.get("PersonName").getAsString();
	 String Area = inqObject.get("Area").getAsString();
	 String Date = inqObject.get("Date").getAsString();
	 String Reason = inqObject.get("Reason").getAsString();
	 String output = InquiryObj.updateInquiry(inqID, PersonName, Area, Date, Reason);
	return output;
	} 
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteInquiry(String inquiryData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(inquiryData, "", Parser.xmlParser());

	//Read the value from the element <ID>
	 String inqID = doc.select("inqID").text();
	 String output = InquiryObj.deleteInquiry(inqID);
	return output;
	}
}
