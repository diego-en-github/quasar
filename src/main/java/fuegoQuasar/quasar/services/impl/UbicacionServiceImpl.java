package fuegoQuasar.quasar.services.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;

import fuegoQuasar.quasar.excepciones.UbicacionException;
import fuegoQuasar.quasar.model.entities.MensajeInterceptado;
import fuegoQuasar.quasar.model.entities.Position;
import fuegoQuasar.quasar.model.entities.SateliteRebelde;
import fuegoQuasar.quasar.model.repository.SateliteRebeldeRepository;
import fuegoQuasar.quasar.services.UbicacionService;

@Service
public class UbicacionServiceImpl implements UbicacionService {

    @Autowired
    @Qualifier("messageResourceSB")
    MessageSource messageSource;
	
	@Autowired
	private SateliteRebeldeRepository repository;
	
	
	public Position determinarUbicacion(List<MensajeInterceptado> mensajes) throws UbicacionException{
		
		List <SateliteRebelde> satelites = repository.findAll();
		satelites.sort(Comparator.comparing(SateliteRebelde::getNombre));
		mensajes.sort(Comparator.comparing(MensajeInterceptado::getName));
		
		if(!mismosSatelites(satelites, mensajes)){
			throw new UbicacionException(messageSource.getMessage("message.exception.nocoinciden",null, Locale.getDefault()));
		}
		
		double[][] coordenadas = getCoordenadasSatelites(satelites);
		double distancias[] = getDistancias(mensajes);
		
		double[] posicion = getLocation(coordenadas, distancias);
		
		Position p = new Position(posicion[0], posicion[1]);
		
		return p;
		
	}
	
	
	private double[][] getCoordenadasSatelites(List <SateliteRebelde> satelites) {
		
		double lista [][] = new double[satelites.size()][];
		for (int i = 0; i < satelites.size(); i++) {
			double coord [] = new double[2];
			coord[0] = satelites.get(i).getCoordenadaX();
			coord[1] = satelites.get(i).getCoordenadaY();
			lista [i] = coord;
		}
		return lista;
	}

	
	private double[] getDistancias(List<MensajeInterceptado> mensajes) {
		double lista [] = new double[mensajes.size()];
		for (int i = 0; i < mensajes.size(); i++) {
			lista [i] = mensajes.get(i).getDistance();
		}
		return lista;
	}
	
	
    public double[] getLocation(double[][] posiciones, double [] distancias) {

        TrilaterationFunction trilaterationFunction = new TrilaterationFunction(posiciones, distancias);
        NonLinearLeastSquaresSolver nSolver = new NonLinearLeastSquaresSolver(trilaterationFunction, new LevenbergMarquardtOptimizer());

        return  nSolver.solve().getPoint().toArray();
    }
	
    
    private boolean mismosSatelites(List <SateliteRebelde> satelites, List<MensajeInterceptado> mensajes) {
    	//List<String> nombreSatelite = satelites.stream().map(SateliteRebelde::getNombre).collect(Collectors.toList());
    	//List <String> nombreMensaje	= mensajes.stream().map(MensajeInterceptado::getName).collect(Collectors.toList());	
    	return satelites.stream().map(SateliteRebelde::getNombre).collect(Collectors.toList()).equals(
    			mensajes.stream().map(MensajeInterceptado::getName).collect(Collectors.toList()));

    }
	
}
