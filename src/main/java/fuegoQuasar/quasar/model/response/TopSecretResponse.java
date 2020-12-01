package fuegoQuasar.quasar.model.response;

import fuegoQuasar.quasar.model.entities.Position;

/*
 * Mensaje de response para devolder la posicion y mensaje decodificado
 */
public class TopSecretResponse {
	
	private String message;
	private Position position;
	
	
	public TopSecretResponse() {
		
	}
	
	public TopSecretResponse(Position position, String message) {
		super();
		this.message = message;
		this.position =  position;
	}

	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	

}
