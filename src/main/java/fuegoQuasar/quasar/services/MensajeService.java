package fuegoQuasar.quasar.services;

import java.util.List;

import fuegoQuasar.quasar.excepciones.MensajeException;
import fuegoQuasar.quasar.model.entities.MensajeInterceptado;

public interface MensajeService {
	
	
	public String capturarMensaje(List<MensajeInterceptado> mensajes) throws MensajeException;

}
