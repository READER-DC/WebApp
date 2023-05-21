package com.sehal.resources;

import com.sehal.model.Kontragent;
import com.sehal.model.services.KontragentService;
import com.sehal.util.parser.ParsJSON;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.json.JsonArray;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("javaee10")
public class TestWebService {
	@Inject
	private KontragentService kontragentService;

//	@PostConstruct
//	private void init() {
////		Kontragent kontragent = new Kontragent();
//		if (Kontragent.kontragents.isEmpty())
//			kontragentService.getAll();
////		ParsJSON parsJSON = new ParsJSON();
//	}

	@GET
	public Response ping() {
		System.out.println("TestWebService is called!");
		return Response.ok("ping").build();
	}

	@POST
	public Response getKontragents() {
		JsonArray kontragentString = ParsJSON
				.createKontragentsListJSON(Kontragent.kontragents);
		kontragentString.toString();
		return Response.ok(kontragentString).build();
	}

}
