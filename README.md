#Microservicio Desarrolladores

Gestiona los desarrolladores independientes de la plataforma. Se conecta al microservicio de Juegos para ver los juegos publicados.

---

## Tecnologías

- Java 17
- Spring Boot 3.5.6
- Spring Data JPA
- Spring WebFlux (WebClient)
- MySQL
- Lombok
- Maven

---

## Configuración

**Base de datos:** `db_desarrolladores`  
**Puerto:** `8082`

Crea la base de datos antes de ejecutar:

```sql
CREATE DATABASE db_desarrolladores;
```

`application.properties`:

```properties
spring.application.name=desarrolladores
server.port=8082

spring.datasource.url=jdbc:mysql://localhost:3306/db_desarrolladores?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

> Si usas **Laragon** cambia `password=` por `password=root`

---

## Cómo ejecutar

```bash
mvn spring-boot:run
```

> El microservicio **Juegos** debe estar corriendo en el puerto `8085` para que los endpoints `/juegos` funcionen.

---

## Arquitectura

```
controller  → recibe peticiones HTTP
service     → lógica de negocio + WebClient
repository  → acceso a datos
model       → entidad DesarrolladorGG
dto         → DTOjuego (datos del microservicio Juegos)
```

---

## Modelo

```java
private Long id;
private String nombre;
private String email;
private String pais;
private String sitioWeb;
```

---

## Endpoints

| Método | URL | Descripción |
|---|---|---|
| GET | `/api/v0/desarrolladores` | Obtener todos los desarrolladores |
| GET | `/api/v0/desarrolladores/{id}` | Obtener desarrollador por ID |
| POST | `/api/v0/desarrolladores` | Crear desarrollador |
| PUT | `/api/v0/desarrolladores/{id}` | Actualizar desarrollador |
| DELETE | `/api/v0/desarrolladores/{id}` | Eliminar desarrollador |
| GET | `/api/v0/desarrolladores/juegos` | Obtener todos los juegos |
| GET | `/api/v0/desarrolladores/juegos/{id}` | Obtener juego por ID |

### Ejemplo POST

```json
{
  "nombre": "Rockstar Games",
  "email": "contacto@rockstar.com",
  "pais": "Estados Unidos",
  "sitioWeb": "https://rockstar.com"
}
```

---

## Conexión con microservicio Juegos

```java
private final WebClient webClient = WebClient.builder()
        .baseUrl("http://localhost:8085/api/v0/juegos")
        .build();
```
