package fabian.smarthome.server;

import fabian.smarthome.server.clients.ClientRegistry;
import fabian.smarthome.server.threads.ConnectionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerMain {
	private static ExecutorService threadPool;
	private static ServerSocket serverSocket;
	private static ClientRegistry clientRegistry;

	private static Logger logger;

	public static void main(String[] args) throws IOException {
		if (Arrays.asList(args).contains("-D"))
			System.setProperty(org.slf4j.simple.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "TRACE");
		logger = LoggerFactory.getLogger(ServerMain.class);

		logger.info("Smart Home System - Server by Fabian");

		logger.trace("Starting creating ThreadPool...");
		threadPool = Executors.newCachedThreadPool(Executors.defaultThreadFactory());
		logger.info("Successfully created ThreadPool!");

		logger.trace("Starting creating ClientRegistry...");
		clientRegistry = new ClientRegistry(new File("clients.json"));
		logger.info("Successfully created ClientRegistry!");

		logger.trace("Starting creating ServerSocket...");
		serverSocket = new ServerSocket(4200);
		logger.info("Successfully created ServerSocket!");

		logger.trace("Starting accepting connections...");
		new Thread(() -> {
			try {
				while (!Thread.interrupted()) {
					Socket client = serverSocket.accept();
					threadPool.execute(new ConnectionHandler(client));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}).start();
		logger.info("Started accepting connections!");
	}

	public static ClientRegistry getClientRegistry() {
		return clientRegistry;
	}
}
