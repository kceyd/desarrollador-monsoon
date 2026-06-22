# 🧑‍💻 Microservicio Desarrolladores

Microservicio que gestiona los desarrolladores de videojuegos de la plataforma **Monsoon**. Expone una API REST con soporte de **HATEOAS** y documentación interactiva con **Swagger / OpenAPI**.

## 🛠 Tecnologías

- Java 17 (build con Java 21 vía Docker)
- Spring Boot 3.4.1
- Spring Data JPA
- Spring HATEOAS
- Spring WebFlux (WebClient)
- Springdoc OpenAPI (Swagger UI)
- MySQL
- Lombok
- Maven

## 📂 Arquitectura

```
controller   → recibe las peticiones HTTP
service      → lógica de negocio
repository   → acceso a datos (Spring Data JPA)
model        → entidad DesarrolladorGG
DTO          → objetos de transferencia expuestos por la API
assembler    → construye los EntityModel y agrega enlaces HATEOAS
```

## 🧾 Modelo

**Entidad `DesarrolladorGG`:**

```java
private Long id;
private String nombre;
```

**DTO de salida `DTOdesarrollador`:**

```java
private Long id;
private String nombre;
```

> El servicio también define `DTOjuego` (id, título, género, descripción, precio, desarrollador), usado para consumir datos del microservicio de juegos a través de `WebClient`.

## ⚙️ Configuración

### Opción A — Ejecución local

Crea la base de datos antes de ejecutar:

```sql
CREATE DATABASE db_desarrolladores;
```

`application.properties`:

```properties
spring.application.name=desarollador
server.port=8081

spring.datasource.url=jdbc:mysql://localhost:3306/db_desarrolladores?allowPublicKeyRetrieval=true&useSSL=false
spring.datasource.username=root
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

> Si usas Laragon, cambia `password=` por `password=root`.

### Opción B — Docker Compose

El proyecto incluye `Dockerfile` y `docker-compose.yml`. El contenedor se conecta a una instancia de MySQL corriendo en el host (`host.docker.internal`), por lo que MySQL debe estar disponible localmente antes de levantar el contenedor.

```bash
docker compose up --build
```

Esto expone:

| Servicio | Puerto host | Puerto interno |
|---|---|---|
| App Spring Boot | `8081` | `8081` |

Variables de entorno usadas por el contenedor (definidas en `docker-compose.yml`):

```yaml
SPRING_DATASOURCE_URL=jdbc:mysql://host.docker.internal:3306/db_desarrolladores?allowPublicKeyRetrieval=true&useSSL=false
SPRING_DATASOURCE_USERNAME=root
SPRING_DATASOURCE_PASSWORD=
SPRING_JPA_HIBERNATE_DDL_AUTO=update
```

## ▶️ Cómo ejecutar

**Local con Maven:**

```bash
mvn spring-boot:run
```

**Con Docker:**

```bash
docker compose up --build
```

La API quedará disponible en `http://localhost:8081`.

## 📌 Endpoints

Todos bajo el prefijo `/api/v0/desarrolladores`.

| Método | URL | Descripción |
|---|---|---|
| GET | `/api/v0/desarrolladores` | Obtener todos los desarrolladores |
| GET | `/api/v0/desarrolladores/{id}` | Obtener un desarrollador por ID |
| POST | `/api/v0/desarrolladores` | Crear un desarrollador |
| PUT | `/api/v0/desarrolladores/{id}` | Actualizar un desarrollador |
| DELETE | `/api/v0/desarrolladores/{id}` | Eliminar un desarrollador |

### Ejemplo POST `/api/v0/desarrolladores`

```
POST http://localhost:8081/api/v0/desarrolladores
```

```json
{
  "nombre": "Santa Monica Studio"
}
```

### Ejemplo GET por ID

```
GET http://localhost:8081/api/v0/desarrolladores/1
```

### Ejemplo de respuesta (con HATEOAS)

```json
{
  "id": 1,
  "nombre": "Santa Monica Studio",
  "_links": {
    "self": { "href": "http://localhost:8081/api/v0/desarrolladores/1" },
    "desarrolladores": { "href": "http://localhost:8081/api/v0/desarrolladores" }
  }
}
```

## 📖 Documentación interactiva (Swagger)

Una vez levantado el proyecto:

```
http://localhost:8081/swagger-ui/index.html
```

## 🧪 Tests

El proyecto incluye una prueba con `MockMvc` sobre la capa de controlador (`PruebasUnitarias`), que valida:

- Que el listado de desarrolladores responda `200 OK`.

```bash
mvn test
```

## 📁 Estructura del proyecto

```
src/main/java/com/example/desarollador/
├── assembler/      # DesarrolladorAssembler (HATEOAS)
├── controller/     # ControlDesarrollador
├── DTO/            # DTOdesarrollador, DTOjuego
├── model/          # DesarrolladorGG
├── repository/     # repoDesarrollador
└── service/        # servicioDesarrollador
```

## 🚧 Notas / mejoras pendientes

- `obtenerDesarrollador(id)` devuelve `null` (sin cuerpo) cuando no existe el recurso, en lugar de un `404 Not Found` explícito.
- Los endpoints `POST`, `PUT` y `DELETE` no devuelven el recurso creado/actualizado ni código de estado explícito (usan `void`); podrían enriquecerse para devolver el DTO con enlaces HATEOAS y el status correcto (`201 Created`, `200 OK`, `204 No Content`).
- El servicio no tiene seguridad/autenticación configurada en el código mostrado.
- El `pom.xml` define `java.version=17`, pero el `Dockerfile` compila y ejecuta con imágenes de Java 21; conviene unificar la versión.
