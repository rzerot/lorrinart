package clientvideo.util;

import java.util.List;

import clientvideo.POJO.Client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {

	public static Client fromJson(String json) {
		Gson gson = new Gson();
		Client client = gson.fromJson(json, Client.class);
		return client;
	}

	public static String toJson(Client client) {
		Gson gson = new Gson();
		String json = gson.toJson(client);
		return json;
	}

	public static String toJson(List<Client> clients) {
		Gson gson = new GsonBuilder()
				.create();

		String json = gson.toJson(clients);
		return json;
	}
}
