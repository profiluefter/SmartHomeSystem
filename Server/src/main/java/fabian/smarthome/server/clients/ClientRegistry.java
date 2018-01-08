package fabian.smarthome.server.clients;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ClientRegistry {
	private HashMap<Integer, Client> clients = new HashMap<>();
	private File clientSaveFile;

	private Gson gson = new Gson();

	private Logger logger = LoggerFactory.getLogger(ClientRegistry.class);

	public void save() {
		logger.info("Saving client registry...");
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(clientSaveFile)));
			writer.write(gson.toJson(clients, TypeToken.getParameterized(HashMap.class,Integer.class,Client.class).getType()));
			writer.flush();
			writer.close();
			logger.info("Successfully saved client registry!");
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Error while saving client registry!", e);
		}
	}

	public void load() {
		logger.info("Loading client registry...");
		BufferedReader reader;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(clientSaveFile)));
			List<String> collect = reader.lines().collect(Collectors.toList());
			if(collect.size() != 1)
				return;
			Object fromJson = gson.fromJson(collect.get(0), TypeToken.getParameterized(HashMap.class, Integer.class, Client.class).getType());

			//noinspection unchecked
			clients = (HashMap<Integer, Client>) fromJson;
			logger.info("Successfully loaded client registry!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			logger.error("Error while loading client registry!", e);
		}
	}

	public void addClient(Client client) {
		clients.put(client.getId(),client);
		save();
	}

	public ClientRegistry(File clientSaveFile) {
		if (clientSaveFile.isDirectory())
			throw new IllegalArgumentException("Clients Save File is a directory!");
		else {
			if (!clientSaveFile.exists())
				try {
					//noinspection ResultOfMethodCallIgnored
					clientSaveFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			this.clientSaveFile = clientSaveFile;
		}
		load();
		save();
	}
}