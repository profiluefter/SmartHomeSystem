package fabian.smarthome.server.packages;

public enum Action {
	//General
	REGISTER,
	RENAME,
	ON,
	OFF,

	//Info
	VERSION,
	ID,

	//Receiver
	STATUS,

	//Sender
	GET_NAME,
	CHANGE_STATUS
}
