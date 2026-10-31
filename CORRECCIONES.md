# Errores corregidos en el código base del cine

**Equipo:** Elias Martinez Garcia y Jose de Jesus Junior Barba Cano

El código base del profesor no compilaba y, aunque compilara, no levantaba el servidor web. Encontramos y corregimos **53 errores** sin agregar funciones nuevas: los `TODO` del código original se conservan porque son funcionalidad pendiente. Las líneas son las del código original (primer commit del repositorio); el segundo commit contiene las correcciones.

## 1. Arranque y compilación (13)

| # | Archivo y línea | Error | Corrección |
|---|---|---|---|
| 1 | `CineApplication` 10–12 | `setWebApplicationType(WebApplicationType.NONE)` arranca Spring sin servidor web, y `run(new String[0])` ignora los argumentos | `SpringApplication.run(CineApplication.class, args)` |
| 2 | `BoletoService` 43 | `cliente.getPuntos() + funcion.getPrecio() / 10` da un `double` y se pasa a `setPuntos(Integer)`: no compila | `(int) (funcion.getPrecio() / 10)` |
| 3 | `BoletoRepository` 12 | `findByFuncionIdentifier`: `Funcion` no tiene atributo `identifier`; duplica a `findByFuncionId` | Eliminado |
| 4 | `BoletoRepository` 14 | `findByClienteMail`: el atributo se llama `email` | `findByClienteEmail` |
| 5 | `ClienteRepository` 12 | `findByEmaill`: duplica a `findByEmail` | Eliminado |
| 6 | `ClienteRepository` 14 | `findByPoints`: el atributo es `puntos`; con `Optional` fallaría si dos clientes tienen los mismos puntos | `List<Cliente> findByPuntos(Integer puntos)` |
| 7 | `FuncionRepository` 11 | `findByFechaHoraBetwen`: la palabra clave es `Between` | `findByFechaHoraBetween` |
| 8 | `FuncionRepository` 15 | `findBySalaNumer`: el atributo es `numero` | `findBySalaNumero` |
| 9 | `FuncionRepository` 17 | `findByFechaHorra`: el atributo es `fechaHora` | `findByFechaHora` |
| 10 | `PeliculaRepository` 15 | `findByClasificacionNombre`: `Clasificacion` es un enum sin atributo `nombre` | Eliminado; ya existe `findByClasificacion` |
| 11 | `PeliculaRepository` 17 | `findByGeneroo`: duplica a `findByGenero` | Eliminado |
| 12 | `SalaRepository` 10 | `findByNumeroo`: duplica a `findByNumero` | Eliminado |
| 13 | `SalaRepository` 12 | `findByCapacidadDisponible`: no existe el atributo `capacidadDisponible` | `List<Sala> findByCapacidadGreaterThanEqual(Integer capacidad)` |

## 2. Semántica de los modelos (16)

| # | Archivo y línea | Error | Corrección |
|---|---|---|---|
| 14 | `Pelicula` 27 | `getTitulo()` regresa `null` | Regresa `titulo` |
| 15 | `Pelicula` 31 | `getDuracion()` multiplica por 60: los minutos salen como segundos | Regresa `duracion` en minutos |
| 16 | `Pelicula` 15 | `EnumType.ORDINAL` guarda la posición: si se reordena el enum cambian las películas guardadas | `EnumType.STRING` |
| 17 | `Clasificacion` 4 | Valores `A, B15, B, C, ATP`: `ATP` no existe en el caso y el orden no sigue la escala | `A, B, B15, C`, como indica el caso |
| 18 | `Funcion` 40 | `getFechaHora()` regresa `LocalDateTime.now()` | Regresa `fechaHora` |
| 19 | `Funcion` 42 | `getPrecio()` multiplica por 100 | Regresa `precio` |
| 20 | `Funcion` 16 y 20 | `pelicula` y `sala` con `FetchType.LAZY`: al convertir a JSON se intenta serializar el proxy de Hibernate | `@ManyToOne` sin `LAZY` |
| 21 | `Funcion` 28 | `boletos` dentro de la función y cada boleto con su función: recursión infinita al convertir a JSON | `@JsonIgnore` en `boletos` |
| 22 | `Cliente` 27 | `getEmail()` regresa el `nombre` | Regresa `email` |
| 23 | `Cliente` 31 | `getPuntos()` suma 1 cada vez que se lee | Regresa `puntos` |
| 24 | `Cliente` 18 | `puntos` empieza en `null`: la primera compra lanzaba `NullPointerException` | Inicia en `0` |
| 25 | `Cliente` 9–10 | `@Id` sin `@GeneratedValue`: no se puede registrar sin id | `@GeneratedValue(strategy = IDENTITY)` |
| 26 | `Boleto` 32 | `getFolio()` regresa el `id` | Regresa `folio` |
| 27 | `Boleto` 36 | `getNumero()` suma 1 | Regresa `numero` |
| 28 | `Sala` 21–22 | El constructor invierte número y capacidad | Asignación correcta |
| 29 | `Sala` 29 | `getCapacidad()` regresa el `numero` | Regresa `capacidad` |

## 3. Servicios (12)

| # | Archivo y línea | Error | Corrección |
|---|---|---|---|
| 30 | `BoletoService` 32 y 41 | `orElseThrow()` sin mensaje: función o cliente inexistente respondía `500` | `404` con el recurso que falta |
| 31 | `BoletoService` 35 | Folio fijo `"BOLETO"` en todos los boletos; `UUID` importado sin usar | `UUID.randomUUID()` |
| 32 | `BoletoService` 38 | `setNumero(numero + 1)` | `setNumero(numero)` |
| 33 | `BoletoService` 31 | Los puntos se sumaban pero nunca se guardaban: el método no era transaccional | `@Transactional` |
| 34 | `ClienteService` 17 | `listar()` regresa `List.of()` | `findAll()` |
| 35 | `ClienteService` 21 | `registrar()` marca al cliente como no registrado | `setRegistrado(true)` |
| 36 | `FuncionService` 31 | `obtener()` regresa `null` | Busca por id; `404` si no existe |
| 37 | `FuncionService` 35–36 | Película o sala inexistente respondía `500` | `404` |
| 38 | `FuncionService` 40 | `setPrecio(0)` borra el precio recibido | Se quita |
| 39 | `PeliculaService` 18 | `listar()` regresa `List.of()` | `findAll()` |
| 40 | `PeliculaService` 22 | `findById(id).get()`: `500` si no existe | `404` |
| 41 | `PeliculaService` 26 | `porClasificacion` siempre busca `Clasificacion.A` | Usa la clasificación recibida |

## 4. Controladores REST (12)

| # | Archivo y línea | Error | Corrección |
|---|---|---|---|
| 42 | `BoletoController` 22 | Comprar con `@GetMapping` | `@PostMapping` |
| 43 | `BoletoController` 24 | `clienteId` obligatorio aunque el servicio lo trata como opcional | `@RequestParam(required = false)` |
| 44 | `ClienteController` 19 | `listar()` regresa `List.of()` | `clienteService.listar()` |
| 45 | `ClienteController` 22 | Registrar con `@PutMapping` | `@PostMapping` |
| 46 | `FuncionController` 19 | `listar()` regresa `List.of()` | `funcionService.listar()` |
| 47 | `FuncionController` 23 | `/{id}` leído con `@RequestParam` | `@PathVariable` |
| 48 | `PeliculaController` 21 | El filtro siempre manda `Clasificacion.A` | Manda la recibida |
| 49 | `PeliculaController` 27 | `/{id}` leído con `@RequestParam` | `@PathVariable` |
| 50 | `PeliculaController` 32 | `crear` sin `@RequestBody`: se guardaba una película vacía | `@RequestBody` |
| 51 | `SalaController` 19 | `listar()` regresa `List.of()` | `findAll()` |
| 52 | `SalaController` 22 | Crear con `@PutMapping` | `@PostMapping` |
| 53 | Los 5 controladores | Las creaciones respondían `200 OK` | `201 Created` |

## Lo que no se agregó

Los `TODO` del código original quedan como venían: validar asiento ocupado y capacidad de la sala, puntos sólo para clientes registrados, cancelar boletos, consultar puntos, traslape de funciones, precio mayor a 0, título obligatorio y duración mayor a 0, y actualizar o eliminar películas.
