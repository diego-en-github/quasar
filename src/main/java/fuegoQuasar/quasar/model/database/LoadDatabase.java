package fuegoQuasar.quasar.model.database;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fuegoQuasar.quasar.model.entities.SateliteRebelde;
import fuegoQuasar.quasar.model.repository.SateliteRebeldeRepository;


/*
 * Carga inicial de los satelites rebeldes
 */
@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(SateliteRebeldeRepository repository) {

    return args -> {
      log.info("Cargando " + repository.save(new SateliteRebelde("skywalker", 100.00, -100.00)));	
      log.info("Cargando " + repository.save(new SateliteRebelde("kenobi", -500.00, -200.00)));
      log.info("Cargando " + repository.save(new SateliteRebelde("sato", 500.00, 100.00)));
    };
  }
}