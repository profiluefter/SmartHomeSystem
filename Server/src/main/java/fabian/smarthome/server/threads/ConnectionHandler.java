package fabian.smarthome.server.threads;

import com.google.gson.Gson;

import fabian.smarthome.server.packages.RequestPackage;
import fabian.smarthome.server.util.ClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Objects;

import static fabian.smarthome.server.util.SocketUtil.readLine;
import static fabian.smarthome.server.util.SocketUtil.sendLine;

public class ConnectionHandler implements Runnable {

	private static Gson gson = new Gson();

	private Logger logger = LoggerFactory.getLogger(ConnectionHandler.class);
	private Socket client;
	private InputStream inputStream;
	private OutputStream outputStream;

	public ConnectionHandler(Socket client) throws IOException {
		this.client = client;
		inputStream = client.getInputStream();
		outputStream = client.getOutputStream();
	}

	public OutputStream getOutputStream() {
		return outputStream;
	}

	@Override
	public void run() {
		try {
			if (setupConnection())
				handle();
			client.close();
			logger.trace("Finished handling connection from {}:{}!", client.getInetAddress().getHostAddress(), client.getPort());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean setupConnection() throws IOException {
		logger.trace("Start handling connection from {}:{}!", client.getInetAddress().getHostAddress(), client.getPort());
		sendLine(outputStream, "OK");
		String readLine = readLine(inputStream);
		if (Objects.equals(readLine, "OK")) {
			logger.trace("Connection established!");
			return true;
		} else {
			logger.warn("Connection failed! Client sent \"{}\"!", readLine);
			return false;
		}
	}

	private void handle() {
		logger.trace("Start handling connection.");

		String request;
		while((request = readLine(inputStream)) != null) {
			RequestPackage requestPackage = gson.fromJson(request, RequestPackage.class);
			switch(requestPackage.getAction()) {
				case REGISTER:
					ClientUtil.register(outputStream,requestPackage);
					break;
			}
		}
	}
}
