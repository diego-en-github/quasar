package fuegoQuasar.quasar.services;

import java.util.List;

import fuegoQuasar.quasar.excepciones.UbicacionException;
import fuegoQuasar.quasar.model.entities.MensajeInterceptado;

/*
 * Service para obtener la posicion
 */
public interface UbicacionService {

	public double[] determinarUbicacion(List<MensajeInterceptado> mensajes) throws UbicacionException;	

	
}
