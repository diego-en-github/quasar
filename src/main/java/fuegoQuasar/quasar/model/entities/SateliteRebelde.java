package fuegoQuasar.quasar.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/*
 * Entidad de satelite rebelde
 */
@Entity
public class SateliteRebelde {

	@Id
	@GeneratedValue
	private Long id;
	private String nombre;
	private Double coordenadaX;
	private Double coordenadaY;

	
	public SateliteRebelde() {
	}

	
	public SateliteRebelde(String nombre, Double coordenadaX, Double coordenadaY) {
		super();
		this.nombre = nombre;
		this.coordenadaX = coordenadaX;
		this.coordenadaY = coordenadaY;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Double getCoordenadaX() {
		return coordenadaX;
	}
	
	public void setCoordenadaX(Double coordenadaX) {
		this.coordenadaX = coordenadaX;
	}
	
	public Double getCoordenadaY() {
		return coordenadaY;
	}
	
	public void setCoordenadaY(Double coordenadaY) {
		this.coordenadaY = coordenadaY;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coordenadaX == null) ? 0 : coordenadaX.hashCode());
		result = prime * result + ((coordenadaY == null) ? 0 : coordenadaY.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		SateliteRebelde other = (SateliteRebelde) obj;
		if (coordenadaX == null) {
			if (other.coordenadaX != null)
				return false;
		} else if (!coordenadaX.equals(other.coordenadaX))
			return false;
		if (coordenadaY == null) {
			if (other.coordenadaY != null)
				return false;
		} else if (!coordenadaY.equals(other.coordenadaY))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SateliteRebelde [id=" + id + ", nombre=" + nombre + ", coordenadaX=" + coordenadaX + ", coordenadaY="
				+ coordenadaY + "]";
	}
	
	
}
