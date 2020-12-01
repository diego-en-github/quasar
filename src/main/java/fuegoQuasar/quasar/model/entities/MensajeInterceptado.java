package fuegoQuasar.quasar.model.entities;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/*
 * Entidad para el mensaje interceptado por los satelites rebeldes
 */
@Entity
public class MensajeInterceptado {
	
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private Double distance;
	private String[] message;
	

	public MensajeInterceptado() {
	}
	
	
	public MensajeInterceptado(String name, Double distance, String[] message) {
		super();
		this.name = name;
		this.distance = distance;
		this.message = message;
	}


	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Double getDistance() {
		return distance;
	}
	
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	
	public String[] getMessage() {
		return message;
	}
	
	public void setMessage(String[] message) {
		this.message = message;
	}
	

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((distance == null) ? 0 : distance.hashCode());
		result = prime * result + Arrays.hashCode(message);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MensajeInterceptado other = (MensajeInterceptado) obj;
		if (distance == null) {
			if (other.distance != null)
				return false;
		} else if (!distance.equals(other.distance))
			return false;
		if (!Arrays.equals(message, other.message))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	@Override
	public String toString() {
		return "MensajeInterceptado [name=" + name + ", distance=" + distance + ", message="
				+ Arrays.toString(message) + "]";
	}
	
	

}
