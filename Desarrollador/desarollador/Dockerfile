# Paso 1: Usar una imagen oficial de Java 21 para compilar
FROM eclipse-temurin:21-jdk-jammy AS build
WORKDIR /app

# Copiar los archivos de Maven del proyecto
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Descargar las dependencias (incluyendo HATEOAS) sin compilar el código aún
RUN ./mvnw dependency:go-offline

# Copiar el código fuente y compilar el archivo .jar
COPY src ./src
RUN ./mvnw clean package -DskipTests

# Paso 2: Crear la imagen final más ligera para correr la app
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Copiar el archivo .jar generado en el paso anterior
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto en el que corre tu app (8081)
EXPOSE 8081

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]