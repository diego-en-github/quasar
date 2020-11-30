package fuegoQuasar.quasar.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fuegoQuasar.quasar.excepciones.BadRequestException;
import fuegoQuasar.quasar.excepciones.MensajeException;
import fuegoQuasar.quasar.excepciones.MensajeInterceptadoException;
import fuegoQuasar.quasar.excepciones.UbicacionException;
import fuegoQuasar.quasar.model.entities.MensajeInterceptado;
import fuegoQuasar.quasar.model.entities.SateliteRebelde;
import fuegoQuasar.quasar.model.repository.MensajeInterceptadoRepository;
import fuegoQuasar.quasar.model.repository.SateliteRebeldeRepository;
import fuegoQuasar.quasar.model.response.MensajeResponse;
import fuegoQuasar.quasar.model.response.TopSecretResponse;
import fuegoQuasar.quasar.services.ComunicacionService;
import fuegoQuasar.quasar.services.MensajeService;
import fuegoQuasar.quasar.services.UbicacionService;

@Service
public class ComunicacionServiceImpl implements ComunicacionService {

	
	@Autowired
	private MensajeService mensajeService;
	
	@Autowired
	private UbicacionService ubicacionService;
	
	@Autowired
	private MensajeInterceptadoRepository mensajeInterceptadoRepository;
	
	@Autowired
	private SateliteRebeldeRepository sateliteRebeldeRepository;
	

	@Override
	public TopSecretResponse getTopSecretResponse(List<MensajeInterceptado> mensajes) throws MensajeException, UbicacionException, BadRequestException{
		
		try {
			if(mensajesInvalidos(mensajes)){
				throw new BadRequestException("El request es invalido");
			}
			
			double [] distancia = ubicacionService.determinarUbicacion(mensajes);
			String mensaje = mensajeService.capturarMensaje(mensajes);
			TopSecretResponse response = new TopSecretResponse(distancia, mensaje);
			return response;
			
		} catch (MensajeException e) {
			throw new MensajeException(e.getMessage());
		}
		
		catch (UbicacionException e) {
			throw new UbicacionException(e.getMessage());
		}
	}
	
	
	private boolean mensajesInvalidos(List<MensajeInterceptado> mensajes){
		for(MensajeInterceptado m: mensajes){
			if(m.getDistance()== null || m.getName() == null || m.getMessage() == null){
				return true;
			}
		}
		return false;
	}


	@Override
	public TopSecretResponse getTopSecretSplitResponse() throws MensajeException, UbicacionException, MensajeInterceptadoException, BadRequestException {
		
		List<MensajeInterceptado> mensajes = mensajeInterceptadoRepository.findAll();
				  
		if(mensajes.size() != 3){
			throw new MensajeInterceptadoException("No hay suficiente informacion.");
		}
		return getTopSecretResponse(mensajes);
	}


	@Override
	public MensajeResponse insertTopSecretRequest(String nombreSatelite, MensajeInterceptado mensaje)
			throws BadRequestException {
		
		mensaje.setName(nombreSatelite);
		
		if(mensajeInvalido(mensaje)){
			throw new BadRequestException("El request es invalido");
		}

		List<SateliteRebelde> satelites = sateliteRebeldeRepository.findAll();
		List<String> nombresSatelites = satelites.stream().map(SateliteRebelde::getNombre).collect(Collectors.toList());
		
		if(!nombresSatelites.contains(nombreSatelite)){
			throw new BadRequestException("Es un satelite invalido");
		}
		
		mensajeInterceptadoRepository.save(mensaje);
		
		
		return new MensajeResponse("Mesaje recibido.");
	}
	
	private boolean mensajeInvalido(MensajeInterceptado mensaje){
		if(mensaje.getDistance()== null || mensaje.getName() == null || mensaje.getMessage() == null){
				return true;
		}
		return false;
	}
	
}
