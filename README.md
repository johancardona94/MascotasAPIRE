# MascotasAPI

API REST para registrar y administrar mascotas. El proyecto está desarrollado con Java 21, Spring Boot, Spring Web, Spring Data JPA, Hibernate y H2.

## Contexto

La API permite gestionar la información básica de mascotas: nombre, especie, edad y propietario.

## Tecnologías

- Java 21
- Spring Boot 3.5.6
- Spring Web
- Spring Data JPA e Hibernate
- Base de datos H2
- Maven

## Requisitos previos

- JDK 21 instalado.
- Maven instalado, o usar el Maven Wrapper si se agrega posteriormente.
- Conexión a internet la primera vez para descargar dependencias.

## Ejecutar el proyecto

1. Descomprime el proyecto.
2. Abre una terminal en la carpeta `MascotasAPI`, donde está `pom.xml`.
3. Ejecuta:

   ```bash
   mvn spring-boot:run
   ```

4. La API estará disponible en `http://localhost:8080`.

También puedes abrir la carpeta en IntelliJ IDEA o Visual Studio Code y ejecutar la clase `MascotasApiApplication`.

## Endpoints principales

| Método | Ruta | Descripción | Respuesta esperada |
|---|---|---|---|
| POST | `/mascotas` | Crear mascota | `201 Created` |
| GET | `/mascotas` | Listar todas | `200 OK` |
| GET | `/mascotas/{id}` | Consultar por ID | `200 OK` o `404 Not Found` |
| PUT | `/mascotas/{id}` | Actualizar por ID | `200 OK` o `404 Not Found` |
| DELETE | `/mascotas/{id}` | Eliminar por ID | `204 No Content` o `404 Not Found` |
| GET | `/mascotas/buscar/especie?especie=perro` | Buscar por especie | `200 OK` |

## Ejemplo para crear una mascota

Solicitud `POST http://localhost:8080/mascotas`, con encabezado `Content-Type: application/json` y este cuerpo:

```json
{
  "nombre": "Luna",
  "especie": "perro",
  "edad": 3,
  "propietario": "Camila"
}
```

Respuesta aproximada (`201 Created`):

```json
{
  "id": 1,
  "nombre": "Luna",
  "especie": "perro",
  "edad": 3,
  "propietario": "Camila"
}
```

El identificador lo genera automáticamente la base de datos; puede ser distinto en cada instalación.

## Pruebas rápidas

1. Ejecuta la solicitud `POST` anterior para guardar una mascota.
2. Ejecuta `GET http://localhost:8080/mascotas` para verla en el listado.
3. Ejecuta `GET http://localhost:8080/mascotas/1` (usa el ID real que devolvió el POST).
4. Ejecuta `GET http://localhost:8080/mascotas/buscar/especie?especie=perro`.
5. Prueba `PUT` y `DELETE` con un ID existente.

Puedes usar Postman, Thunder Client o `curl`.

## Persistencia y consola H2

La configuración utiliza una base de datos H2 en archivos dentro de la carpeta `data`, para que los datos se conserven entre ejecuciones. La consola H2 está habilitada en `http://localhost:8080/h2-console` mientras la aplicación está encendida.

Datos para la consola:
- JDBC URL: `jdbc:h2:file:./data/mascotasdb`
- User Name: `sa`
- Password: dejar vacío

Si la consola no conecta usando la URL indicada, revisa la URL exacta que aparece en `application.properties`; no cambies la URL de la aplicación mientras esté ejecutándose.

## Estructura

- `model/Mascota.java`: entidad JPA.
- `repository/MascotaRepository.java`: repositorio basado en `JpaRepository` y consulta derivada por especie.
- `controller/MascotaController.java`: endpoints CRUD y consulta personalizada.
- `application.properties`: configuración de H2 y Hibernate.

## Publicación en GitHub

1. Crea un repositorio nuevo llamado `MascotasAPI` en GitHub.
2. Descomprime el ZIP y abre una terminal en la carpeta del proyecto.
3. Ejecuta:

   ```bash
   git init
   git add .
   git commit -m "Actividad colaborativa 2: API REST con JPA y CRUD"
   git branch -M main
   git remote add origin https://github.com/TU-USUARIO/MascotasAPI.git
   git push -u origin main
   ```

4. Reemplaza `TU-USUARIO` por tu usuario real de GitHub. Verifica que el repositorio quede visible con el código y el README.

## Uso de inteligencia artificial

Este proyecto fue preparado con apoyo de ChatGPT para organizar la estructura inicial, explicar JPA/Hibernate y proponer operaciones CRUD. Antes de entregar, el estudiante debe ejecutar el proyecto, probar los endpoints, revisar el código y adaptar lo necesario. En el video debe explicar honestamente qué herramientas utilizó y cómo verificó el funcionamiento.

## Integrantes

- Nombre del estudiante: [escribe tu nombre]
- Integrantes adicionales (si aplica): [escribe sus nombres]
