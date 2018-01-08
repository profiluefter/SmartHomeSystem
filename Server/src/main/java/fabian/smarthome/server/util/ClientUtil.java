package fabian.smarthome.server.util;

import fabian.smarthome.server.ServerMain;
import fabian.smarthome.server.packages.Action;
import fabian.smarthome.server.packages.IllegalRequestErrorPackage;
import fabian.smarthome.server.packages.RequestPackage;

import java.io.IOException;
import java.io.OutputStream;

import static fabian.smarthome.server.util.SocketUtil.sendPackage;

public class ClientUtil {
	public static void register(OutputStream outputStream, RequestPackage requestPackage) {
		if (requestPackage.getSelf().equals(requestPackage.getTarget()) || requestPackage.getTarget() == null) {
			ServerMain.getClientRegistry().addClient(requestPackage.getSelf());
		} else {
			try {
				sendPackage(outputStream,new IllegalRequestErrorPackage(requestPackage.getSelf(), Action.REGISTER,"self"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}