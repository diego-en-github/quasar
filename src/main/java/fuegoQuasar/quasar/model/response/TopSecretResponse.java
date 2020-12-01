package fuegoQuasar.quasar.model.response;

/*
 * Mensaje de response para devolder la posicion y mensaje decodificado
 */
public class TopSecretResponse {
	
	private double[] location;
	private String message;
	
	
	public TopSecretResponse() {
		
	}
	
	public TopSecretResponse(double[] location, String message) {
		super();
		this.location = location;
		this.message = message;
	}

	public double[] getLocation() {
		return location;
	}
	
	public void setLocation(double[] location) {
		this.location = location;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	

}
