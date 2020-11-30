package fuegoQuasar.quasar.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import fuegoQuasar.quasar.excepciones.MensajeException;
import fuegoQuasar.quasar.model.entities.MensajeInterceptado;
import fuegoQuasar.quasar.services.MensajeService;

@Service
public class MensajeServiceImpl implements MensajeService {

	
	public String capturarMensaje(List<MensajeInterceptado> mensajes) throws MensajeException{
		
		List<List<String>> listaMensajes = getMensajes(mensajes);
		List<Integer> longitudesMensajes = getLongitudesMensaje(mensajes);
		int minimoMensaje = getLongitudMinimoMensaje(longitudesMensajes);
		removerDelay(minimoMensaje, listaMensajes);

		return getMensajeDecodificado(minimoMensaje, listaMensajes);
		
	}

	
	private int getLongitudMinimoMensaje(List<Integer> longitudesMensajes){
		return longitudesMensajes.stream().mapToInt(i -> i).min().getAsInt();
	}
	
	private String getMensajeDecodificado(int minimoMensaje, List<List<String>> listaMensajes) throws MensajeException{
		String mensajeDecodificado = "";
		for (int i = 0; i< minimoMensaje; i++){
			
			if(!listaMensajes.get(0).get(i).equals("")){
				mensajeDecodificado+= listaMensajes.get(0).get(i) + " ";
			} else if (!listaMensajes.get(1).get(i).equals("")) {
				mensajeDecodificado+= listaMensajes.get(1).get(i) + " ";
			} else if (!listaMensajes.get(2).get(i).equals("")){
				mensajeDecodificado+= listaMensajes.get(2).get(i) + " ";
			} else {
				throw new MensajeException("No se pudo determinar el mensaje.");
			}
		}	
		return mensajeDecodificado;
	}
	
	
	private List<List<String>> getMensajes (List<MensajeInterceptado> mensajes){
		
		List<List<String>> lm = new ArrayList<>();
		for (MensajeInterceptado m : mensajes) {
			List<String> l = new ArrayList<String>();
			Collections.addAll(l, m.getMessage());
			lm.add(l);
		}
		return lm;
	}
	
	private List<Integer> getLongitudesMensaje(List<MensajeInterceptado> mensajes){
		List<Integer> longitudesMensajes = new ArrayList<Integer>();
		for (MensajeInterceptado m : mensajes) {
			List<String> l = new ArrayList<String>();
			Collections.addAll(l, m.getMessage());
			longitudesMensajes.add(m.getMessage().length);
		}
		return longitudesMensajes;
		
	}
	
	private void removerDelay(int minimoMensaje, List<List<String>> listaMensajes) throws MensajeException{
		for (List<String>  lm : listaMensajes) {
			while (minimoMensaje < lm.size()){
				if(!lm.get(0).equals("")){
					throw new MensajeException("No se pudo determinar el mensaje.");
				}
				lm.remove(0);
			}
		}	
		
	}
	
}
