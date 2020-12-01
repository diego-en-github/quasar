package fuegoQuasar.quasar.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fuegoQuasar.quasar.model.entities.MensajeInterceptado;

/*
 * ABM y busqueda de MensajeInterceptado
 */
public interface MensajeInterceptadoRepository extends JpaRepository<MensajeInterceptado, Long>{

}
