package fabian.smarthome.server.packages;

import fabian.smarthome.server.clients.Client;

public class IllegalRequestErrorPackage {
	private Client target;
	private Action error;
	private String illegalField;

	public IllegalRequestErrorPackage(Client target, Action error, String illegalField) {
		this.target = target;
		this.error = error;
		this.illegalField = illegalField;
	}

	public Client getTarget() {
		return target;
	}

	public Action getError() {
		return error;
	}

	public String getIllegalField() {
		return illegalField;
	}
}
