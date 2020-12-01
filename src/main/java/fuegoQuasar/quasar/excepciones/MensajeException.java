package fuegoQuasar.quasar.excepciones;

/*
 * Mensaje de Excepción genérico
 */
public class MensajeException extends Exception{

	private static final long serialVersionUID = 1L;

 	
	public MensajeException(String errorMessage){
        super(errorMessage);
    }
	
}
