### Descripción

Para resolver el desafio implemente una aplicación Java Spring Boot con Maven.

Para persistir los satelites rebeldes y los post request del topsecret_split  use Spring JPA con la base de datos in-memory  H2.

### Uso 

- topsecret : Tipo Post

http://localhost:8080/topsecret

Request:
{"satellites": [ 
{ “name”: "kenobi", distance”: 100.0, "message”: ["este", "", "", "mensaje", ""]  }, 
{ “name”: "skywalker", "distance”: 115.5“message”: ["", "es", "", "", "secreto"] }, 
{ “name”: "sato", “distance”: 142.7 "message”: ["este", "", "un", "", ""]}
]} 

Response:
{ 
"position": {"x": -100.0, "y": 75.5 }, 
"message": "este es un mensaje secreto" 
} 

- topsecret_split/{name}: Tipo Post

http://localhost:8080/topsecret_split/name

Request:
El name debe ser uno entre {Sato, Skywalker, Kenobi}
{ 
"distance": 100.0, 
"message": ["este", "", "", "mensaje", ""] 
} 

 - topsecret_split/: Tipo Get

http://localhost:8080/topsecret_split/

Response:
{ 
"position": {"x": -100.0, "y": 75.5 }, 
"message": "este es un mensaje secreto" 
} 

### Errores

En caso de que se produzca en error se espera devolver un mensaje descriptivo.

### Consideraciones

- Para resolver la función getLocation() utilizé el algoritmo provisto por la biblioteca com.lemmingapex.trilateration

- En el mensaje recibido se admite una ilimitada cantidad de caracteres de desfazaje representado por ""
