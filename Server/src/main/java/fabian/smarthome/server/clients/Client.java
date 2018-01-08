package fabian.smarthome.server.clients;

import java.util.Objects;

public class Client {
	private int id;
	private IPAddress address;

	private String name;
	private Type type;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Client client = (Client) o;
		return id == client.id &&
				Objects.equals(address, client.address) &&
				Objects.equals(name, client.name) &&
				type == client.type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, address, name, type);
	}

	public Client() {
	}

	public Client(int id, IPAddress address, String name, Type type) {
		this.id = id;
		this.address = address;
		this.name = name;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public Client setId(int id) {
		this.id = id;
		return this;
	}

	public IPAddress getAddress() {
		return address;
	}

	public Client setAddress(IPAddress address) {
		this.address = address;
		return this;
	}

	public String getName() {
		return name;
	}

	public Client setName(String name) {
		this.name = name;
		return this;
	}

	public Type getType() {
		return type;
	}

	public Client setType(Type type) {
		this.type = type;
		return this;
	}

	public enum Type {
		sender,
		reciever
	}

	public static class IPAddress {
		private String address;
		private int port;

		public IPAddress() {
		}

		public IPAddress(String address, int port) {
			setAddress(address);
			setPort(port);
		}

		public String getAddress() {
			return address;
		}

		public IPAddress setAddress(String address) {
			this.address = address;
			return this;
		}

		public int getPort() {
			return port;
		}

		public IPAddress setPort(int port) {
			this.port = port;
			return this;
		}
	}
}
