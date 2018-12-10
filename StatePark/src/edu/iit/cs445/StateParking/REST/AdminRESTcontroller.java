package edu.iit.cs445.StateParking.REST;
import edu.iit.cs445.StateParking.Actors.*;

import edu.iit.cs445.StateParking.Objects.*;
import edu.iit.cs445.StateParking.ObjectsEnum.*;
import edu.iit.cs445.StateParking.REST.*;

import java.net.URI;
import java.util.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.google.gson.GsonBuilder;
import org.glassfish.jersey.internal.sonar.*;
@Path("/parkpay")
public class AdminRESTcontroller {
	private MockDatabase DB = MockDatabase.getInstance();
	private AdditionalFunction f = AdditionalFunction.getInstance();
	
	@Path("/parks")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response ViewAllParks(@QueryParam("key") String key) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String s ;
		ArrayList<ParkBoundaryInterface> result = new ArrayList<ParkBoundaryInterface>();
		if(key == null) {
			result = DB.ViewAllParks().ViewAll();
		}
		else {
			result = DB.SearchPark(key);
		}
		s = gson.toJson(createParkPresenter(result));
		return Response.status(Response.Status.OK).entity(s).build();
		}
	
		private ArrayList<SimpleParkPresenter> createParkPresenter(
				ArrayList<ParkBoundaryInterface> list){
			ArrayList<SimpleParkPresenter> result = new ArrayList<SimpleParkPresenter> ();
			Iterator<ParkBoundaryInterface> it = list.iterator();
			while(it.hasNext()) {
				ParkBoundaryInterface park = it.next();
				result.add(new SimpleParkPresenter(park));
			}
			return result;
		}
	
	@Path("/parks")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response CreatePark( @Context UriInfo uriInfo, String json) {
		Gson gson = new Gson();
		JsonObject Error = new JsonObject();
		JsonObject Result = new JsonObject();
		Error.addProperty("type", 
				"http://cs.iit.edu/~virgil/cs445/project/api/problems/data-validation");
		Error.addProperty("title", "Your request data didn't pass validation");
		Error.addProperty("instance", "/parks");
		Error.addProperty("status", 400);
		
		JsonParser parser = new JsonParser();
		JsonElement jelement = parser.parse(json);
		JsonObject jsonObj = jelement.getAsJsonObject();
		JsonObject loc = f.validGetJson(jsonObj,"location_info");
		String name = f.validGetField(loc, "name");
		String region = f.validGetField(loc, "region");
		String phone = f.validGetField(loc, "phone");
		String web = f.validGetField(loc, "web");
		String address = f.validGetField(loc, "address");	
		if(name.isEmpty() || address.isEmpty() || web.isEmpty()){
			Error.addProperty("detail", "missing required information");
			String s = gson.toJson(Error);
			return Response.status(400).entity(s).build();
		}
		
		ArrayList<String> a = f.OneLineParsing(address);
		if (a.size()!=4) {
			Error.addProperty("detail","Addrees format should be:'Street','City','State' 'zip'");
			String s = gson.toJson(Error);
			return Response.status(400).entity(s).build();
		}
		Address ad = new Address(a.get(0),a.get(1),State.valueOf(a.get(2)),a.get(3));
		
		JsonObject geo = f.validGetJson(loc,"geo");
		if(geo == null){
			Error.addProperty("detail","Geo information is required but missing in your request");
			String s = gson.toJson(Error);
			return Response.status(400).entity(s).build();
		}
		double lat = f.validGetDoubleField(geo,"lat"); 
		double lng = f.validGetDoubleField(geo,"lng");
		try {
			geo G1 = new geo(lat, lng);
		}catch(RuntimeException e) {
			Error.addProperty("detail","Invalid geo coordinate");
			String s = gson.toJson(Error);
			return Response.status(400).entity(s).build();
		}
		geo G = new geo(lat, lng);
		
		JsonObject fee = f.validGetJson(jsonObj,"payment_info");
		java.lang.reflect.Type typeMap = new TypeToken<Map<String, ArrayList<Double>>>(){}.getType();
		Map<String, ArrayList<Double>> myMap2 = gson.fromJson(fee,typeMap);
		if(!myMap2.containsKey("motorcycle") || !myMap2.containsKey("car") 
				|| !myMap2.containsKey("rv")) {
			Error.addProperty("detail","must have 3 vehicle types: motocycle, car and rv");
			String s = gson.toJson(Error);
			return Response.status(400).entity(s).build();
		}

		if(!f.PositivePrice(myMap2)) {
			Error.addProperty("detail", "All payment data must be a number greater than or equal to zero");
			String s = gson.toJson(Error);
			return Response.status(400).entity(s).build();
		}
		
		Iterator<String> it = myMap2.keySet().iterator();
		ParkBoundaryInterface park = new Park(name, ad, web, G, region, phone); 
		while(it.hasNext()) {
			String type = it.next();
			double instate = myMap2.get(type).get(0);
			double outstate = myMap2.get(type).get(1);
			park.updateExistingFee(VehicleType.valueOf(type),
					instate, outstate);
		}
		int pid = DB.CreatePark2(park);
		String PID = Integer.toString(pid);
		Result.addProperty("pid", PID);
		String s = gson.toJson(Result);
		//return s;
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(Integer.toString(pid));
		return Response.created(builder.build()).entity(s).build();
	}
	
	@Path("/parks/{pid}")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response UpdatePark( @PathParam("pid") int pid, String json) {
		Gson gson = new Gson();
		JsonObject Error = new JsonObject();
		Error.addProperty("type", 
				"http://cs.iit.edu/~virgil/cs445/project/api/problems/data-validation");
		Error.addProperty("title", "Your request data didn't pass validation");
		Error.addProperty("instance", "/parks");
		Error.addProperty("status", "400");
		
		JsonParser parser = new JsonParser();
		JsonElement jelement = parser.parse(json);
		JsonObject jsonObj = jelement.getAsJsonObject();
		JsonObject loc = f.validGetJson(jsonObj,"location_info");
		String name = f.validGetField(loc, "name");
		String region = f.validGetField(loc, "region");
		String phone = f.validGetField(loc, "phone");
		String web = f.validGetField(loc, "web");
		String address = f.validGetField(loc, "address");	
		if(name.isEmpty() || address.isEmpty() || web.isEmpty()){
			Error.addProperty("detail", "missing required information");
			String s = gson.toJson(Error);
			return Response.status(400).entity(s).build();
		}
		
		ArrayList<String> a = f.OneLineParsing(address);
		if (a.size()!=4) {
			Error.addProperty("detail","Addrees format should be:'Street','City','State' 'zip'");
			String s = gson.toJson(Error);
			return Response.status(400).entity(s).build();
		}
		Address ad = new Address(a.get(0),a.get(1),State.valueOf(a.get(2)),a.get(3));
		
		JsonObject geo = f.validGetJson(loc,"geo");
		if(geo == null){
			Error.addProperty("detail","Geo information is required but missing in your request");
			String s = gson.toJson(Error);
			return Response.status(400).entity(s).build();
		}
		double lat = f.validGetDoubleField(geo,"lat"); 
		double lng = f.validGetDoubleField(geo,"lng");
		try {
			geo G1 = new geo(lat, lng);
		}catch(RuntimeException e) {
			Error.addProperty("detail","Invalid geo coordinate");
			String s = gson.toJson(Error);
			return Response.status(400).entity(s).build();
		}
		geo G = new geo(lat, lng);
		
		JsonObject fee = f.validGetJson(jsonObj,"payment_info");
		java.lang.reflect.Type typeMap = new TypeToken<Map<String, ArrayList<Double>>>(){}.getType();
		Map<String, ArrayList<Double>> myMap2 = gson.fromJson(fee,typeMap);
		if(!myMap2.containsKey("motorcycle") || !myMap2.containsKey("car") 
				|| !myMap2.containsKey("rv")) {
			Error.addProperty("detail","must have 3 vehicle types: motocycle, car and rv");
			String s = gson.toJson(Error);
			return Response.status(400).entity(s).build();
		}

		if(!f.PositivePrice(myMap2)) {
			Error.addProperty("detail", "All payment data must be a number greater than or equal to zero");
			String s = gson.toJson(Error);
			return Response.status(400).entity(s).build();
		}
		
		Iterator<String> it = myMap2.keySet().iterator();
		DB.UpdateParkLoc(pid, name, ad, web, lat, lng, region, phone);
		while(it.hasNext()) {
			String type = it.next();
			double instate = myMap2.get(type).get(0);
			double outstate = myMap2.get(type).get(1);
			DB.UpdatePark(pid, VehicleType.valueOf(type),
					instate, outstate);
		}
		
		return Response.status(204).build();
	}
	
	@Path("/parks/{pid}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response DeletePark(@PathParam("pid") int pid) {
		Gson gson = new Gson();
		try {
			DB.DeletePark(pid);
		}catch(RuntimeException e) {
			return Response.status(404).build();
		}
		return Response.status(204).build();
		
	}
	
	@Path("/parks/{pid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response ViewSpecifiedPark(@PathParam("pid") int pid) {
		Gson gson = new Gson();
		ParkBoundaryInterface park = DB.ViewParkDetail(pid);
		if (park.isNull()) {
			return Response.status(404).build();
		}

		ParkPresenterWithGeo p = new ParkPresenterWithGeo(park);
		String s = gson.toJson(p);
		return Response.ok(s).build();
		
	}
	
	@Path("/orders")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response CreateOrder( @Context UriInfo uriInfo, String json) {
		Gson gson = new Gson();
		JsonObject Error = new JsonObject();
		JsonObject Result = new JsonObject();
		Error.addProperty("type", 
				"http://cs.iit.edu/~virgil/cs445/project/api/problems/processing-errors");
		Error.addProperty("title", "There has been an error processing your request");
		Error.addProperty("instance", "/orders");
		Error.addProperty("status", "400");
		
		JsonParser parser = new JsonParser();
		JsonElement jelement = parser.parse(json);
		JsonObject element5 = jelement.getAsJsonObject();
		
		int pid;
		try {
			pid= f.validGetIntField(element5,"pid");  
		} catch(RuntimeException e) {
			Error.addProperty("detail","A park id must be specified for the order");
			String s = gson.toJson(Error);
			return Response.status(400).entity(s).build();
		}
		
		JsonObject Vehicle = new JsonObject();
		Vehicle = f.validGetJson(element5,"vehicle");
		if (Vehicle == null) {
			Error.addProperty("detail","This is a parking payment system and you need a car, kiddo");
			String s = gson.toJson(Error);
			return Response.status(400).entity(s).build();
		}
		String state = f.validGetField(Vehicle,"state");
		String plate = f.validGetField(Vehicle,"plate");
		String type = f.validGetField(Vehicle,"type");
		Vehicle Somecar = new Vehicle(State.valueOf(state),VehicleType.valueOf(type),plate);
		
		JsonObject Visitor = f.validGetJson(element5,"visitor");
		if (Visitor == null) {
			Error.addProperty("detail","Visitor information is required for placing order");
			String s = gson.toJson(Error);
			return Response.status(400).entity(s).build();
		}
		String name = Visitor.get("name").getAsString();
		String email = Visitor.get("email").getAsString();
		if (f.EmptyInput("email")) {
			Error.addProperty("detail","Email is required to receive order receipt");
			String s = gson.toJson(Error);
			return Response.status(400).entity(s).build();
		}

		JsonObject card = f.validGetJson(Visitor, "payment_info");
		if (card == null) {
			Error.addProperty("detail","You need to provide credit card info to place order.");
			String s = gson.toJson(Error);
			return Response.status(400).entity(s).build();
		}
		String number = f.validGetField(card,"card");
		String name_on_card = f.validGetField(card,"name_on_card");
		String expiration_date = f.validGetField(card,"expiration_date");
		String zip = f.validGetField(card,"zip");
		CreditCard creditcard = new CreditCard(number,name_on_card,expiration_date,zip);

		MockCCprocessor CC = MockCCprocessor.getInstance();
		if(!CC.validate(creditcard)) {
			Error.addProperty("detail","Credit card transaction declined.");
			String s = gson.toJson(Error);
			return Response.status(400).entity(s).build();
		}
		
		int oid ;
		int vid = DB.NewVisitor(name, email);
		try {
			oid = DB.CreateOrder(pid, vid, Somecar, creditcard);
		}catch(RuntimeException e) {
			Error.addProperty("detail","The park id you provide is not valid.");
			String s = gson.toJson(Error);
			return Response.status(400).entity(s).build();
		}
		String OID = Integer.toString(oid);
		Result.addProperty("oid", OID);
		String s = gson.toJson(Result);
		//return s;
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(Integer.toString(oid));
		return Response.created(builder.build()).entity(s).build();
	}
	
	@Path("/orders")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response ViewAllOrder(@QueryParam("key") String key) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String s ;
		ArrayList<Order> order = new ArrayList<Order>();
		ArrayList<SimpleOrderPresenter> result = new ArrayList<SimpleOrderPresenter>();
		if(key == null) {
			order = DB.ViewAllOrder();
			result = createOrderPresenter(order);
		}
		else {
			order = DB.SearchOrder(key);
			result = createOrderPresenter(order);
		}
		s = gson.toJson(result);//(createParkPresenter(result));
		return Response.status(Response.Status.OK).entity(s).build();
	}
		private ArrayList<SimpleOrderPresenter> createOrderPresenter(
			ArrayList<Order> list){
			ArrayList<SimpleOrderPresenter> result = new ArrayList<SimpleOrderPresenter> ();
			Iterator<Order> it = list.iterator();
			int pid;
			while(it.hasNext()) {
				Order order = it.next();
				pid = DB.GetKeyfromValue(DB.OrderOfPark, order.getId());
				SimpleOrderPresenter presenter = new SimpleOrderPresenter(order);
				presenter.setPid(pid);
				result.add(presenter);
			}
			return result;
		}
		@Path("/orders/{oid}")
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public Response ViewSpecifiedOrder(@PathParam("oid") int oid) {
			Gson gson = new Gson();
			Order order = DB.ViewOrder(oid);
			if (order.isNull()) {
				return Response.status(404).build();
			}
			OrderWithDetailPresenter O = f.getOrderDetail(order);
			String s = gson.toJson(O);
			return Response.ok(s).build();
			
		}	
	
	
	@Path("/visitors")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response ViewAllVisitors(@QueryParam("key") String key) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String s;
		ArrayList <Visitor> result = new ArrayList<Visitor> ();
		if(key == null) {
			result = DB.ViewAllVisitor();
		}
		else {
			result = DB.SearchVisitor(key);
		}
		s = gson.toJson(result);
		return Response.status(Response.Status.OK).entity(s).build();
		}
	
	@Path("/visitors/{vid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response ViewSpecifiedVisitor(@PathParam("vid") int vid) {
		Gson gson = new Gson();
		Visitor visitor = DB.ViewVisitor(vid);
		if (visitor.isNull()) {
			return Response.status(404).build();
		}
		VisitorWithDetailsPresenter result = f.getVisitorDetail(visitor);
		String s = gson.toJson(result);
		return Response.ok(s).build();
	}

	@Path("/parks/{pid}/notes")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response CreateNote( @PathParam("pid") int pid, @Context UriInfo uriInfo, String json) {
		Gson gson = new Gson();
		JsonObject Error = new JsonObject();
		JsonObject Result = new JsonObject();
		Error.addProperty("type", 
				"http://cs.iit.edu/~virgil/cs445/project/api/problems/data-validation");
		Error.addProperty("title", "Your request data didn't pass validation");
		Error.addProperty("instance", "/parks/{pid}");
		Error.addProperty("status", "400");
		
		JsonParser parser = new JsonParser();
		JsonElement jelement = parser.parse(json);
		JsonObject jobject = jelement.getAsJsonObject();
		int vid = (jobject.get("vid").getAsInt());
		String title = (jobject.get("title").getAsString());
		String comment =(jobject.get("text").getAsString());
		int nid;
		try {
			nid = DB.CreateNote(pid, vid, title, comment);
		} catch(RuntimeException e) {
			Error.addProperty("detail","You may not post a note to a park unless you paid for admission at that park");
			String s = gson.toJson(Error);
			return Response.status(400).entity(s).build();
		}
		Result.addProperty("nid", nid);
		String s = gson.toJson(Result);
		return Response.status(Response.Status.OK).entity(s).build();
	}
	
	@Path("/parks/{pid}/notes")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response ViewAllNoteOfGivenPark( @PathParam("pid") int pid) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		ArrayList<Note> note = DB.ViewAllNotesOfThePark(pid);
		ArrayList<ParksNotePresenter> result = f.createNotePresenter(note);
		String s = gson.toJson(result);
		return Response.status(Response.Status.OK).entity(s).build();
	}
	
	@Path("/notes")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response ViewAllNotes(@QueryParam("key") String key) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String s ;
		ArrayList<Note> note = new ArrayList<Note>();
		ArrayList<ParksNotePresenter> result = 
				new ArrayList<ParksNotePresenter>();
		if(key == null) {
			note = DB.ViewAllNote();
			result = f.createNotePresenter(note);
		}
		else {
			note = DB.SearchNote(key);
			result = f.createNotePresenter(note);
		}
		s = gson.toJson(result);
		return Response.status(Response.Status.OK).entity(s).build();
	}

	@Path("/notes/{nid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response ViewSpecifiedNote(@PathParam("nid") int nid) {
		Gson gson = new Gson();
		Note note = DB.ViewNote(nid);
		if (note.isNull()) {
			return Response.status(404).build();
		}
		NotePresenterWithDetails result = new NotePresenterWithDetails(note);
		int pid = DB.GetKeyfromValue(DB.NoteOfPark, note.getNid());
		result.setPid(pid);
		int vid = DB.GetKeyfromValue(DB.NoteOfVisitor, note.getNid());
		result.setVid(vid);
		String s = gson.toJson(result);
		return Response.ok(s).build();
	}
	@Path("/notes/{nid}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response DeleteSpecifiedNote(@PathParam("nid") int nid) {
		if (DB.DeleteNote(nid)==false) {
			return Response.status(404).build();
		}
		return Response.status(204).build();
	}
	
	@Path("/parks/{pid}/notes/{nid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response ViewSpecifiedNoteOfCertainPark(@PathParam("nid") int nid
			,@PathParam("pid") int pid) {
		Gson gson = new Gson();
		Note note = DB.ViewSpecifiedNoteOfGivenPark(pid, nid);
		if (note.isNull()) {
			return Response.status(404).build();
		}
		NotePresenterWithDetails result = new NotePresenterWithDetails(note);
		result.setPid(pid);
		int vid = DB.GetKeyfromValue(DB.NoteOfVisitor, note.getNid());
		result.setVid(vid);
		String s = gson.toJson(result);
		return Response.ok(s).build();
	}
	
	@Path("/reports")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response ViewAvailableReport() {
		Gson gson = new Gson();
		ReportCode[] result = DB.getReportCode();
		String s = gson.toJson(result);
		return Response.ok(s).build();
	}
	
	@Path("/reports/{rid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response ViewSpecifiedTypeOfReport(@PathParam("rid") int rid
			,@QueryParam("start_date") String start_date, 
			@QueryParam("end_date") String end_date) {
		Gson gson = new Gson();
		JsonObject Error = new JsonObject();
		Error.addProperty("type", 
				"http://cs.iit.edu/~virgil/cs445/project/api/problems/data-validation");
		Error.addProperty("title", "Your request data didn't pass validation");
		Error.addProperty("instance", "/reports/{rid}");
		Error.addProperty("status", "400");
		String s;
		if (rid == 907) {
			try {
				  s = gson.toJson(DB.getAdmissionReport(start_date, end_date));
				} catch(RuntimeException e) {
					Error.addProperty("detail","Wrong date format");
					s = gson.toJson(Error);
					return Response.status(400).entity(s).build();
				}
			s = gson.toJson(DB.getRevenueReport(start_date, end_date));
			return Response.status(200).entity(s).build();
		}
		else if (rid == 911) {
			try {
			  s = gson.toJson(DB.getRevenueReport(start_date, end_date));
			} catch(RuntimeException e) {
				Error.addProperty("detail","Wrong date format");
				s = gson.toJson(Error);
				return Response.status(400).entity(s).build();
			}
			s = gson.toJson(DB.getRevenueReport(start_date, end_date));
			return Response.status(200).entity(s).build();
		}
		return Response.status(404).build();
	}
}
