package fuegoQuasar.quasar.services;

import java.util.List;

import fuegoQuasar.quasar.excepciones.MensajeException;
import fuegoQuasar.quasar.model.entities.MensajeInterceptado;
/*
 * Service para obtener el mensaje
 */
public interface MensajeService {
	
	
	public String capturarMensaje(List<MensajeInterceptado> mensajes) throws MensajeException;

}
