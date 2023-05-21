package com.sehal.util.parser;

import java.util.List;

import com.sehal.model.Kontragent;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;

public class ParsJSON {

	public static JsonObject createKontragentJSON(String kontragentName,
			int kontragentId) {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add("K_NAME", kontragentName).add("K_ID", kontragentId)
				.build();
		return jsonObject;
	}

	public static JsonArray createKontragentsListJSON(
			List<Kontragent> kontragents) {
		JsonArrayBuilder jsonArraysBuilder = Json.createArrayBuilder();
		for (Kontragent kontragent : kontragents) {
			JsonObject jObject = ParsJSON.createKontragentJSON(
					kontragent.getK_NAME(), kontragent.getK_ID());
			jsonArraysBuilder.add(jObject);
		}
		return jsonArraysBuilder.build();
	}

}
