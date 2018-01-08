package fabian.smarthome.server.packages;

import fabian.smarthome.server.clients.Client;

import java.util.List;

public class RequestPackage {
	private Client self;
	private Action action;
	private List<Object> args;
	private Client target;

	public RequestPackage() {
	}

	public RequestPackage(Client self, Action action, List<Object> args, Client target) {
		this.self = self;
		this.action = action;
		this.args = args;
		this.target = target;
	}

	public Client getSelf() {
		return self;
	}

	public RequestPackage setSelf(Client self) {
		this.self = self;
		return this;
	}

	public Action getAction() {
		return action;
	}

	public RequestPackage setAction(Action action) {
		this.action = action;
		return this;
	}

	public List<Object> getArgs() {
		return args;
	}

	public RequestPackage setArgs(List<Object> args) {
		this.args = args;
		return this;
	}

	public Client getTarget() {
		return target;
	}

	public RequestPackage setTarget(Client target) {
		this.target = target;
		return this;
	}
}
