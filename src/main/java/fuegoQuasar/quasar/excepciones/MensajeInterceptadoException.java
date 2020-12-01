package fuegoQuasar.quasar.excepciones;

/*
 * Execpción de decodificación de mensaje
 */
public class MensajeInterceptadoException extends Exception{


	private static final long serialVersionUID = 1L;

	public MensajeInterceptadoException(String errorMessage){
        super(errorMessage);
    }
}
