package fuegoQuasar.quasar.model.entities;

import java.util.List;
/*
 * Wrapper para la entidad de mensajes interceptados
 */
public class MensajeWrapper {

	
	private List<MensajeInterceptado> satelites;

	
	public List<MensajeInterceptado> getSatelites() {
		return satelites;
	}

	public void setSatelites(List<MensajeInterceptado> satelites) {
		this.satelites = satelites;
	}
	
	
}
