# Reto 1 · Corrección del código base del cine

**Unidad 3 · Semántica** — Arquitectura Orientada a Servicios
**Equipo:** Elias Martinez Garcia y Jose de Jesus Junior Barba Cano

API REST de un cine (películas, salas, funciones, clientes y boletos) entregada por el profesor con errores. El reto consiste en **encontrar y corregir los errores sin agregar funciones nuevas**; los `TODO` del código original se conservan porque son funcionalidad pendiente, no errores. Los mensajes se intercambian sólo en JSON.

El primer commit del repositorio es el código tal como lo entregó el profesor; el segundo contiene todas las correcciones, así que el diff entre ambos muestra cada cambio.

## Errores corregidos

| Capa | Errores | Ejemplos |
|---|---|---|
| Arranque y compilación | 13 | `WebApplicationType.NONE` apagaba el servidor web; `double` asignado a `Integer` en `BoletoService`; 11 métodos de repositorio con nombres que Spring Data no puede interpretar |
| Modelos (semántica de los datos) | 16 | `getTitulo()` regresaba `null`, `getDuracion()` multiplicaba por 60, `getPrecio()` por 100, `getEmail()` regresaba el nombre, el constructor de `Sala` invertía número y capacidad, `Clasificacion` con valores fuera del caso y guardada por posición |
| Servicios | 12 | `listar()` regresaba listas vacías, `obtener()` regresaba `null`, el precio de la función se sobrescribía con 0, los puntos del cliente nunca se guardaban |
| Controladores (REST) | 12 | Comprar boleto con `GET`, crear con `PUT`, `/{id}` leído con `@RequestParam`, película creada sin `@RequestBody`, filtro de clasificación fijo en `A` |

El detalle de cada error, con archivo, línea y corrección, está en [`CORRECCIONES.md`](CORRECCIONES.md).

## Ejecutar

1. Copiar `.env.example` como `.env` y poner la contraseña de MySQL (la base `cine` se crea sola).
2. `./mvnw spring-boot:run` (Windows: `mvnw.cmd spring-boot:run`). Arranca en `http://localhost:8088`.
3. Importar `postman/Reto1_Cine.postman_collection.json` (22 solicitudes) y ejecutar en orden sobre una base vacía.

## Endpoints

| Método | Ruta | Cuerpo / parámetros |
|---|---|---|
| GET · POST | `/salas` | `{"numero":3,"capacidad":120}` |
| GET · POST | `/peliculas` · `?clasificacion=B15` | `{"titulo":"Coco","clasificacion":"A","duracion":105,"genero":"Animación"}` |
| GET | `/peliculas/{id}` | — |
| GET · POST | `/clientes` | `{"nombre":"Ana García","email":"ana.garcia@correo.com"}` |
| GET | `/funciones` · `/funciones/{id}` | — |
| POST | `/funciones?peliculaId=1&salaId=1` | `{"fechaHora":"2026-11-07T18:30:00","precio":85.0}` |
| POST | `/boletos?funcionId=1&clienteId=1&fila=F&numero=7` | `clienteId` es opcional |
| GET | `/boletos/funcion/{funcionId}` | — |

Un id inexistente responde `404` con el mensaje del recurso que falta.

Tecnologías: Java 25, Spring Boot 4.1.1, Spring Data JPA, MySQL.
