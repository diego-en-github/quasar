package fuegoQuasar.quasar.services.impl;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import fuegoQuasar.quasar.excepciones.BadRequestException;
import fuegoQuasar.quasar.excepciones.MensajeException;
import fuegoQuasar.quasar.excepciones.MensajeInterceptadoException;
import fuegoQuasar.quasar.excepciones.UbicacionException;
import fuegoQuasar.quasar.model.entities.MensajeInterceptado;
import fuegoQuasar.quasar.model.entities.Position;
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
	
	
    @Autowired
    @Qualifier("messageResourceSB")
    MessageSource messageSource;
	

	@Override
	public TopSecretResponse getTopSecretResponse(List<MensajeInterceptado> mensajes) throws MensajeException, UbicacionException, BadRequestException{
		
		try {
			if(mensajesInvalidos(mensajes)){
				throw new BadRequestException(messageSource.getMessage("message.exception.request",null, Locale.getDefault()));
			}
			
			Position distancia = ubicacionService.determinarUbicacion(mensajes);
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
			if(m.getDistance()== null  || m.getName() == null || m.getName().equals("") || m.getMessage() == null){
				return true;
			}
		}
		return false;
	}


	@Override
	public TopSecretResponse getTopSecretSplitResponse() throws MensajeException, UbicacionException, MensajeInterceptadoException, BadRequestException {
		
		List<MensajeInterceptado> mensajes = mensajeInterceptadoRepository.findAll();
				  
		if(mensajes.size() != 3){
			throw new MensajeInterceptadoException(messageSource.getMessage("message.exception.faltainfo",null, Locale.getDefault()));
		}
		return getTopSecretResponse(mensajes);
	}


	@Override
	public MensajeResponse insertTopSecretRequest(String nombreSatelite, MensajeInterceptado mensaje)
			throws BadRequestException {
		
		mensaje.setName(nombreSatelite);
		
		if(mensajeInvalido(mensaje)){
			throw new BadRequestException(messageSource.getMessage("message.exception.request",null, Locale.getDefault()));
		}

		List<SateliteRebelde> satelites = sateliteRebeldeRepository.findAll();
		List<String> nombresSatelites = satelites.stream().map(SateliteRebelde::getNombre).collect(Collectors.toList());
				
		if(!nombresSatelites.contains(nombreSatelite)){
			throw new BadRequestException(messageSource.getMessage("message.exception.nombreinvalido",null, Locale.getDefault()));
		}
		
		List<MensajeInterceptado> mensajesSatelites = mensajeInterceptadoRepository.findAll();
		
		Optional<MensajeInterceptado> ms = mensajesSatelites.stream().filter(p -> p.getName().equals(nombreSatelite)).findFirst();
		if(ms.isPresent()){
			ms.get().setDistance(mensaje.getDistance());
			ms.get().setMessage(mensaje.getMessage());
			mensajeInterceptadoRepository.save(ms.get());
		}else{
			mensajeInterceptadoRepository.save(mensaje);
		}
		
		
		
		return new MensajeResponse(messageSource.getMessage("message.recepcion",null, Locale.getDefault()));
	}
	
	
	private boolean mensajeInvalido(MensajeInterceptado mensaje){
		return mensaje.getDistance()== null || mensaje.getName() == null || mensaje.getName().equals("")  || mensaje.getMessage() == null;
	}
	
}
