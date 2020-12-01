package fuegoQuasar.quasar.services;

import java.util.List;

import fuegoQuasar.quasar.excepciones.BadRequestException;
import fuegoQuasar.quasar.excepciones.MensajeException;
import fuegoQuasar.quasar.excepciones.MensajeInterceptadoException;
import fuegoQuasar.quasar.excepciones.UbicacionException;
import fuegoQuasar.quasar.model.entities.MensajeInterceptado;
import fuegoQuasar.quasar.model.response.MensajeResponse;
import fuegoQuasar.quasar.model.response.TopSecretResponse;

/*
 * Service del controller
 */
public interface ComunicacionService {
	

	public TopSecretResponse getTopSecretResponse(List<MensajeInterceptado> mensajes) throws MensajeException, UbicacionException, BadRequestException;
	public TopSecretResponse getTopSecretSplitResponse()throws MensajeException, UbicacionException, MensajeInterceptadoException, BadRequestException;
	public MensajeResponse insertTopSecretRequest(String satelite, MensajeInterceptado mensaje) throws BadRequestException;

}
