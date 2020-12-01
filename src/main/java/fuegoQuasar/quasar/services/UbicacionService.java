package fuegoQuasar.quasar.services;

import java.util.List;

import fuegoQuasar.quasar.excepciones.UbicacionException;
import fuegoQuasar.quasar.model.entities.MensajeInterceptado;
import fuegoQuasar.quasar.model.entities.Position;

/*
 * Service para obtener la posicion
 */
public interface UbicacionService {

	public Position determinarUbicacion(List<MensajeInterceptado> mensajes) throws UbicacionException;	

	
}
