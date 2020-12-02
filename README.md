### Descripción

Para resolver el desafio implemente una aplicación Java Spring Boot con Maven.

Para persistir los satelites rebeldes y los post request del topsecret_split  use Spring JPA con la base de datos in-memory H2.

La aplicación esta deployada en Heroku con la url https://opfuegodequasar.herokuapp.com/

### Inicialización

Al levantar la aplicación se persisten en la base de datos en memoria los satelites rebeldes con los datos del enunciado.

### Uso 

- topsecret : Tipo Post


https://opfuegodequasar.herokuapp.com/topsecret

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

https://opfuegodequasar.herokuapp.com/topsecret_split/name

http://localhost:8080/topsecret_split/name


Request:
El name debe ser uno entre {Sato, Skywalker, Kenobi}
{ 
"distance": 100.0, 
"message": ["este", "", "", "mensaje", ""] 
} 

 - topsecret_split/: Tipo Get

https://opfuegodequasar.herokuapp.com/topsecret_split

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

- Los satelites enviados en el servicio topsecret_split se almacenan en la base de datos en memoria. Invocando el servicio con parámetro de un satelite ya dado de alta produce que se actualicen sus valores de distance y message con los pasados en el POST.

- Para dar de alta un nuevo satelite con el servicio topsecret_split se valida que el nombre se corresponda con uno de los dados de alta al inicializar la aplicación

