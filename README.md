# NotasBackend API

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.5.4-green?style=flat-square)
![H2 Database](https://img.shields.io/badge/Database-H2-blue?style=flat-square)

Una API RESTful desarrollada con Spring Boot para gestionar roles y usuarios, con funcionalidades de autenticación.

---

## Estructura de Controladores

### 1. RolController
**Base URL:** `/api/roles`

- `GET /api/roles` - Obtiene todos los roles registrados.
- `POST /api/roles` - Crea un nuevo rol.

---

### 2. UsuarioController
**Base URL:** `/api/usuarios`

- `GET /api/usuarios` - Obtiene una lista paginada de usuarios.
  - Parámetros opcionales:
    - `page` (por defecto: 0)
    - `size` (por defecto: 5)
- `GET /api/usuarios/{id}` - Obtiene los detalles de un usuario por su ID.
- `PUT /api/usuarios/{id}/rol` - Actualiza el rol de un usuario existente por su ID. 
- `DELETE /api/usuarios/{id}` - Elimina un usuario por su ID.

---

### 3. AuthController
**Base URL:** `/api/auth`

- `POST /api/auth/login` - Autentica un usuario mediante sus credenciales.
  - **Body:** `{ "usuario": "string", "password": "string" }`
- `POST /api/auth/register` - Registra un nuevo usuario.
- `POST /api/auth/logout` - Cierra la sesión del usuario autenticado.

---

### 4. NotaController
**Base URL:** `/api/notas`

- `GET /api/notas` - Obtiene una lista paginada de notas.
  - Parámetros opcionales:
    - `page` (por defecto: 0)
    - `size` (por defecto: 5)
  - Filtra el rol del usuario y muestra solo las notas propias o, si se es administrador, las de todos los usuarios.
- `POST /api/notas/{usuarioId}` - Crea una nueva nota asociada a un usuario.
- `GET /api/notas/{id}` - Obtiene los detalles de una nota por su ID.
- `PUT /api/notas/{id}` - Actualiza una nota existente por su ID.
- `PUT /api/notas/{id}/estado` - Actualiza el estado de una nota existente por su ID. 
- `DELETE /api/notas/{id}` - Elimina una nota por su ID.

---

## Tecnologías Usadas

- ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.5.4-green?style=flat-square) - Framework principal para el desarrollo de la aplicación.
- ![H2 Database](https://img.shields.io/badge/Database-H2-blue?style=flat-square) - Base de datos en memoria para propósitos de desarrollo y pruebas.
- **Jakarta Validation** - Validación de datos de entrada.

---

## Instalación y Ejecución

1. Clona este repositorio:
   ```bash
   git clone <url_del_repositorio>
   ```

2. Ve al directorio del proyecto:
   ```bash
   cd notasBackend
   ```

3. Configura las propiedades de la base de datos y el token de autenticación en `application.properties` si es necesario.

4. Ejecuta la aplicación:
   ```bash
   ./mvnw spring-boot:run
   ```

5. Accede a la API en [http://localhost:8080](http://localhost:8080).

---

## Próximos Pasos

- Implementar seguridad con Spring Security.
- Añadir más endpoints para la gestión de notas.
- Mejorar el manejo de errores y validaciones adicionales.

---

## Contribuciones

¡Contribuciones son bienvenidas! Por favor, abre un issue o envía un pull request.
