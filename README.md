# Tech Test para Coopeuch (Backend)

## Backend del Proyecto

Este proyecto contiene el backend desarrollado con Java 17, Maven y Spring Boot 3.2.5. Utiliza una base de datos H2 para el almacenamiento de datos.

## Requisitos previos
Antes de ejecutar este proyecto, asegúrate de tener instalado lo siguiente en tu sistema:

- [Java Development Kit (JDK) 17](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html "Java Development Kit (JDK) 17")
- [Apache Maven](https://maven.apache.org/download.cgi "Apache Maven")

## Instalación y configuración
Clona este repositorio en tu máquina local.
Abre una terminal y navega hasta la carpeta del proyecto backend.
Ejecuta el comando mvn install para instalar todas las dependencias del proyecto.
Modifica la configuración de la base de datos en el archivo application.properties según sea necesario.
Ejecuta el comando mvn `spring-boot:run` para iniciar el servidor de desarrollo.

## Uso
Una vez que el servidor esté en funcionamiento, puedes acceder a los endpoints API proporcionados por el backend para interactuar con la base de datos H2, el CORS permite acceder solamente desde la máquina local o desde del puerto 3000 de la maquina local (Frontend)

## Información adicional
Para obtener más información sobre el uso de Spring Boot y Maven, consulta la documentación oficial:

- [Spring Boot Documentation](https://spring.io/projects/spring-boot "Spring Boot Documentation")
- [Maven Documentation](https://maven.apache.org/guides/index.html "Maven Documentation")

## Documentacion de APIs implementada con Swagger
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html "http://localhost:8080/swagger-ui/index.html")
