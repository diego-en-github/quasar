package fuegoQuasar.quasar.model.response;

/*
 * Mensaje de response
 */
public class MensajeResponse {

	private String respuesta;

	
	public MensajeResponse() {
		super();
	}	
	
	public MensajeResponse(String respuesta) {
		super();
		this.respuesta = respuesta;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	
}
