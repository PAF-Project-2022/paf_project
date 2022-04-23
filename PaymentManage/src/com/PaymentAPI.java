package com;

import model.Payment;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Payment")
public class PaymentAPI {
	Payment PaymentObj = new Payment();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPayment() {
		return PaymentObj.readPayment();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPayment(@FormParam("Payment_AccountNO") String Payment_AccountNO, 
			@FormParam("Payment_CName") String Payment_CName,
			@FormParam("Payment_Date") String Payment_Date,
			@FormParam("Payment_TotalAmount") String Payment_TotalAmount) {
		String output = PaymentObj.insertPayment(Payment_AccountNO,Payment_CName, Payment_Date, Payment_TotalAmount);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)

	public String updatePayment(String paymentData) {
		// Convert the input string to a JSON object
		JsonObject PaymentObject = new JsonParser().parse(paymentData).getAsJsonObject();

		// Read the values from the JSON object
		String payment_ID = PaymentObject.get("payment_ID").getAsString();
		String Payment_AccountNO = PaymentObject.get("Payment_AccountNO").getAsString();
		String Payment_CName = PaymentObject.get("Payment_CName").getAsString();
		String Payment_Date = PaymentObject.get("Payment_Date").getAsString();
		String Payment_TotalAmount = PaymentObject.get("Payment_TotalAmount").getAsString();
		
		String output = PaymentObj.updatePayment(payment_ID, Payment_AccountNO, Payment_CName, Payment_Date, Payment_TotalAmount);
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePayment(String paymentData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());

		// Read the value from the element
		String payment_ID = doc.select("payment_ID").text();
		String output = PaymentObj.deletePayment(payment_ID);
		return output;
	}
}
