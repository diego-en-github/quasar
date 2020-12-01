package fuegoQuasar.quasar.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import fuegoQuasar.quasar.excepciones.BadRequestException;
import fuegoQuasar.quasar.excepciones.MensajeException;
import fuegoQuasar.quasar.excepciones.MensajeInterceptadoException;
import fuegoQuasar.quasar.excepciones.UbicacionException;
import fuegoQuasar.quasar.model.entities.MensajeInterceptado;
import fuegoQuasar.quasar.model.entities.MensajeWrapper;
import fuegoQuasar.quasar.model.response.MensajeResponse;
import fuegoQuasar.quasar.services.ComunicacionService;

/*
 * Punto de entrada de los Webservices
 */

@RestController
public class ComunicacionController {


	
	@Autowired
	ComunicacionService comunicacionService;
	
    @Autowired
    @Qualifier("messageResourceSB")
    MessageSource messageSource;
	
	
	@PostMapping(path = "/topsecret", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity topSecret(RequestEntity<MensajeWrapper> requestEntity) {
		
		if( requestEntity ==  null || requestEntity.getBody() == null || requestEntity.getBody().getSatelites() == null 
				|| requestEntity.getBody().getSatelites().size() != 3 ) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensajeResponse(messageSource.getMessage("message.exception.request",null, Locale.getDefault())));
		}
		
		try {	
			return ResponseEntity.status(HttpStatus.OK).body(comunicacionService.getTopSecretResponse(requestEntity.getBody().getSatelites()));
		
		} catch (UbicacionException e) {	
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MensajeResponse(e.getMessage()));
			
		} catch (MensajeException e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MensajeResponse(e.getMessage()));
        
		} catch (BadRequestException e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensajeResponse(e.getMessage()));
        }

	}

	
	@GetMapping(path = "/topsecret_split", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity topSecretSplit() {
		
		try {	
			
			return ResponseEntity.status(HttpStatus.OK).body(comunicacionService.getTopSecretSplitResponse());
		
		} catch (UbicacionException e) {	
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MensajeResponse(e.getMessage()));
			
		} catch (MensajeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MensajeResponse(e.getMessage()));
        
		} catch (MensajeInterceptadoException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MensajeResponse(e.getMessage()));
            
        } catch (BadRequestException e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensajeResponse(e.getMessage()));
        }
	}
	
	
	@PostMapping(path = "/topsecret_split/{name}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity one(@PathVariable String name, RequestEntity<MensajeInterceptado> requestEntity) {
		try {	
			
			return ResponseEntity.status(HttpStatus.OK).body(comunicacionService.insertTopSecretRequest(name, requestEntity.getBody()));
		
		} catch (BadRequestException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensajeResponse(e.getMessage()));
        
		} 
		
	}
	
}
