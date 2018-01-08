package fabian.smarthome.server.util;

import com.google.gson.Gson;

import java.io.*;
import java.util.Scanner;

public class SocketUtil {
	private static Gson gson = new Gson();

	public static String readLine(InputStream inputStream) {
		Scanner scanner = new Scanner(inputStream);
		for (int i = 0; i < 10; i++) {
			if (scanner.hasNextLine())
				return scanner.nextLine();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static void sendLine(OutputStream outputStream, String line) throws IOException {
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
		writer.write(line + "\n");
		writer.flush();
	}

	public static void sendPackage(OutputStream outputStream, Object packageToSend) throws IOException {
		sendLine(outputStream,gson.toJson(packageToSend));
	}
}
