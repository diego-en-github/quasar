package fuegoQuasar.quasar.excepciones;

/*
 * Excepción para la ubicación
 */
public class UbicacionException extends Exception{

	private static final long serialVersionUID = 1L;

	public UbicacionException(String errorMessage){
        super(errorMessage);
    }
}
