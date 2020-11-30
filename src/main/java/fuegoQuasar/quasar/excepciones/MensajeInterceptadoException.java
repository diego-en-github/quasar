package fuegoQuasar.quasar.excepciones;

public class MensajeInterceptadoException extends Exception{


	private static final long serialVersionUID = 1L;

	public MensajeInterceptadoException(String errorMessage){
        super(errorMessage);
    }
}
