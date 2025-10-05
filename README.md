# MedicApp - Sistema de Telemedicina

(MVP) Web app de telemedicina que permite la gestión de citas médicas, consultas virtuales y seguimiento de pacientes. Sistema completo con backend en Spring Boot, frontend en React y despliegue en Docker.

## 📋 Tabla de Contenidos

- [Características](#características)
- [Arquitectura](#arquitectura)
- [Requisitos](#requisitos)
- [Instalación](#instalación)
- [Ejecución](#ejecución)
- [Desarrollo](#desarrollo)
- [Testing](#testing)
- [Documentación API](#documentación-api)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Tecnologías](#tecnologías)

## ✨ Características

- **Gestión de Usuarios**: Registro y autenticación con JWT (Roles: Paciente, Médico, Administrador)
- **Sistema de Citas**: Agendamiento de citas con médicos por especialidad
- **Videollamadas**: Consultas médicas virtuales en tiempo real
- **Historial Médico**: Registro y consulta de historial clínico
- **Gestión de Horarios**: Médicos pueden configurar su disponibilidad
- **Panel Administrativo**: Gestión completa de usuarios, médicos y especialidades
- **Documentación API**: Swagger UI integrado

## 🏗️ Arquitectura

```
MedicApp/
├── MedicAppBack/          # Backend - Spring Boot API
│   ├── src/main/java/
│   │   ├── config/        # Configuración (CORS, Security, Swagger)
│   │   ├── controllers/   # Controladores REST
│   │   ├── models/        # Entidades y DTOs
│   │   ├── repositories/  # Repositorios JPA
│   │   ├── services/      # Lógica de negocio
│   │   └── security/      # JWT y autenticación
│   └── src/main/resources/
│       └── db/migration/  # Migraciones Flyway
│
├── MedicAppFront/         # Frontend - React + Vite
│   └── src/
│       ├── features/      # Módulos por funcionalidad
│       ├── core/          # Componentes y contextos globales
│       └── components/    # Componentes compartidos
│
└── docker-compose.yml     # Orquestación de servicios
```

## 📦 Requisitos

**Backend:**
- Java 17+
- Maven 3.8+
- MySQL 8.0+

**Frontend:**
- Node.js 18+
- npm 9+

**Docker (Opcional):**
- Docker 20.10+
- Docker Compose 2.0+

## 🚀 Instalación

### Opción 1: Con Docker (Recomendado)

1. **Clonar el repositorio:**
```bash
git clone <repository-url>
cd medicApp
```

2. **Configurar variables de entorno:**
Crear archivo `.env` en la raíz:
```env
DB_HOST="YOUR_HOST"
DB_NAME="YOUR_DB_NAME"
DB_USER="YOUR_USER"
DB_PASSWORD_USER="YOUR_PASSWORD"
```

3. **Levantar servicios:**
```bash
docker-compose up -d
```

El backend estará disponible en `http://localhost:8080`  
La base de datos en `localhost:3307`

### Opción 2: Instalación Manual

**Backend:**
```bash
cd MedicAppBack
./mvnw clean install
```

**Frontend:**
```bash
cd MedicAppFront
npm install
```

**Base de Datos:**
```sql
CREATE DATABASE database;
```

Configurar `application.properties`:
```properties
spring.datasource.url="YOUR_DB"
spring.datasource.username="YOUR_USER"
spring.datasource.password="YOUR_PASSWORD"
```

## ▶️ Ejecución

### Con Docker

```bash
# Iniciar servicios
docker-compose up -d

# Ver logs
docker-compose logs -f backend

# Detener servicios
docker-compose down
```

### Sin Docker

**Backend:**
```bash
cd MedicAppBack
./mvnw spring-boot:run
```

**Frontend:**
```bash
cd MedicAppFront
npm run dev
```

Accesos:
- **API**: http://localhost:8080
- **Frontend**: http://localhost:5173
- **Swagger UI**: http://localhost:8080/swagger-ui/index.html

## 🛠️ Desarrollo

### Comandos del Backend

```bash
# Ejecutar en desarrollo
./mvnw spring-boot:run

# Compilar (sin tests)
./mvnw clean package -DskipTests

# Ejecutar todos los tests
./mvnw test

# Ejecutar un test específico
./mvnw test -Dtest=UserControllerTest

# Limpiar y compilar
./mvnw clean package
```

### Comandos del Frontend

```bash
# Servidor de desarrollo (con hot-reload)
npm run dev

# Build de producción
npm run build

# Lint
npm run lint

# Preview del build
npm run preview
```

## 🧪 Testing

### Backend (JUnit 5 + Mockito)

Los tests siguen el patrón AAA (Arrange-Act-Assert):

```bash
# Ejecutar todos los tests
cd MedicAppBack && ./mvnw test

# Test específico
./mvnw test -Dtest=DoctorControllerTest

# Test con cobertura
./mvnw test jacoco:report
```

**Estructura de tests:**
```
src/test/java/
├── controllers/          # Tests de controladores (@WebMvcTest)
├── models/usuarios/      # Tests de repositorios (@DataJpaTest)
└── TeleMedicinaApplicationTests.java
```

**Convenciones:**
- `@DisplayName` en español con patrón "Deberia..."
- Use Mockito para mocks (`@Mock`, `@InjectMocks`)
- AssertJ para assertions fluidas
- Tests de controladores con `MockMvc`

### Frontend

Actualmente sin framework de testing configurado.

## 📖 Documentación API

### Swagger UI

Acceder a: http://localhost:8080/swagger-ui/index.html

### Endpoints Principales

**Autenticación:**
```http
POST /auth/registro        # Registrar usuario
POST /auth/login           # Iniciar sesión (obtener JWT)
```

**Usuarios:**
```http
GET    /usuarios           # Listar usuarios
GET    /usuarios/{id}      # Obtener usuario
PUT    /usuarios/{id}      # Actualizar usuario
DELETE /usuarios/{id}      # Eliminar usuario
```

**Médicos:**
```http
GET    /doctores           # Listar médicos
GET    /doctores/{id}      # Obtener médico
POST   /doctores           # Registrar médico
PUT    /doctores/{id}      # Actualizar médico
DELETE /doctores/{id}      # Eliminar médico
```

### Autenticación

Todos los endpoints (excepto `/auth/*`) requieren JWT:

```http
Authorization: Bearer <token>
```

**Ejemplo:**
```bash
# 1. Registrarse
curl -X POST http://localhost:8080/auth/registro \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Juan",
    "apellido": "Pérez",
    "username": "juan.perez",
    "correoElectronico": "juan@example.com",
    "contrasena": "password123"
  }'

# 2. Login
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "juan.perez",
    "contrasena": "password123"
  }'

# 3. Usar el token
curl -X GET http://localhost:8080/usuarios \
  -H "Authorization: Bearer <token-recibido>"
```

## 📁 Estructura del Proyecto

### Backend

```
MedicAppBack/
├── src/main/java/com/teleMedicina/teleMedicina/
│   ├── config/
│   │   ├── CorsConfiguration.java           # Configuración CORS
│   │   └── SpringDocConfiguration.java      # Configuración Swagger
│   ├── controllers/
│   │   ├── AutenticacionController.java     # Login/Registro
│   │   ├── UserController.java              # CRUD Usuarios
│   │   └── DoctorController.java            # CRUD Médicos
│   ├── models/
│   │   ├── usuarios/                        # Usuario, DTOs, Roles
│   │   ├── medicos/                         # Doctor, DTOs
│   │   ├── citas/                           # Cita, Consulta, Estados
│   │   ├── especialidades/                  # Especialidad
│   │   ├── historialMedico/                 # HistorialMedico
│   │   └── horarioDoctor/                   # HorarioDoctor
│   ├── repositories/
│   │   ├── IUserRepository.java
│   │   └── DoctorRepository.java
│   ├── services/
│   │   ├── UserService.java                 # Lógica de usuarios
│   │   ├── DoctorService.java               # Lógica de médicos
│   │   └── CustomUserDetailsService.java    # Spring Security
│   ├── security/
│   │   ├── SecurityConfigurations.java      # Configuración Security
│   │   ├── SecurityFilter.java              # Filtro JWT
│   │   ├── TokenService.java                # Generación/validación JWT
│   │   └── AutenticacionService.java
│   └── errores/
│       ├── ErrorResponse.java               # DTO de error
│       └── TratadorDeErrores.java           # @ControllerAdvice
│
├── src/main/resources/
│   ├── application.properties               # Configuración principal
│   ├── application-test.properties          # Configuración tests
│   └── db/migration/
│       └── V1__create-tables-telemedicina.sql
│
└── src/test/java/
    ├── controllers/                         # Tests de controladores
    └── models/usuarios/                     # Tests de repositorios
```

### Frontend

```
MedicAppFront/
├── src/
│   ├── features/                            # Módulos por funcionalidad
│   │   ├── home/view/HomeView.jsx
│   │   ├── login/view/LoginView.jsx
│   │   ├── registrarse/view/Registrarse.jsx
│   │   ├── especialidades/view/Especialidades.jsx
│   │   ├── agendarCita/view/AgendarCita.jsx
│   │   ├── paciente/view/PacienteView.jsx
│   │   ├── medico/view/MedicoView.jsx
│   │   ├── administrador/view/Admin.jsx
│   │   ├── videollamada/view/VideollamadaView.jsx
│   │   ├── servicios/view/Servicios.jsx
│   │   ├── precios/view/Precios.jsx
│   │   └── ayuda/view/Ayuda.jsx
│   ├── core/
│   │   ├── auth/
│   │   │   ├── context/AuthContext.jsx      # Contexto de autenticación
│   │   │   ├── hook/useAuth.js              # Hook personalizado
│   │   │   ├── providers/AuthProvider.jsx
│   │   │   └── components/
│   │   │       ├── PrivateRoute.jsx         # Rutas protegidas
│   │   │       └── PublicRoute.jsx
│   │   ├── layouts/Layout.jsx               # Layout principal
│   │   ├── routes/AppRouter.jsx             # Configuración de rutas
│   │   ├── providers/RootProviders.jsx
│   │   └── components/
│   │       ├── Menu.jsx                     # Barra de navegación
│   │       ├── Footer.jsx
│   │       ├── Banner.jsx
│   │       ├── Body.jsx
│   │       ├── Card1.jsx
│   │       └── Card2.jsx
│   ├── components/
│   │   ├── Inicio.jsx
│   │   └── ImagenTelemedicina.jsx
│   ├── App.jsx                              # Componente raíz
│   ├── main.jsx                             # Entry point
│   ├── App.css
│   └── index.css
│
├── public/                                  # Assets estáticos
├── index.html
├── vite.config.js                           # Configuración Vite
├── eslint.config.js                         # Configuración ESLint
├── package.json
└── nginx.conf                               # Configuración Nginx (producción)
```

## 🔧 Tecnologías

### Backend

| Tecnología | Versión | Propósito |
|------------|---------|-----------|
| **Spring Boot** | 3.3.5 | Framework principal |
| **Spring Security** | 6.x | Autenticación y autorización |
| **Spring Data JPA** | 3.x | Persistencia de datos |
| **MySQL** | 8.0 | Base de datos |
| **Flyway** | 10.x | Migraciones de BD |
| **JWT (Auth0)** | 4.3.0 | Tokens de autenticación |
| **Lombok** | Latest | Reducir boilerplate |
| **SpringDoc OpenAPI** | 2.6.0 | Documentación Swagger |
| **Jakarta Validation** | 3.x | Validación de datos |
| **JUnit 5** | 5.x | Testing |
| **Mockito** | 5.x | Mocking para tests |

### Frontend

| Tecnología | Versión | Propósito |
|------------|---------|-----------|
| **React** | 18.3.1 | Biblioteca UI |
| **Vite** | 5.4.8 | Build tool y dev server |
| **React Router DOM** | 6.27.0 | Enrutamiento |
| **Bootstrap** | 5.3.3 | Framework CSS |
| **React Bootstrap** | 2.10.5 | Componentes Bootstrap |
| **FullCalendar** | 6.1.15 | Calendario para citas |
| **Bootstrap Icons** | 1.11.3 | Iconografía |
| **ESLint** | 9.11.1 | Linter de código |

### DevOps

- **Docker** & **Docker Compose** - Contenedorización
- **Maven Wrapper** - Build automation
- **Git** - Control de versiones

## 📝 Convenciones de Código

### Backend (Java)

**Nomenclatura:**
- Clases: `PascalCase`
- Métodos/variables: `camelCase`
- Constantes: `UPPER_SNAKE_CASE`
- Packages: `lowercase`

**Anotaciones:**
```java
// Entidades
@Entity @Table @Getter @Setter @NoArgsConstructor @AllArgsConstructor

// Validación
@NotEmpty @NotNull @Valid @Email

// Controladores
@RestController @RequestMapping @GetMapping @PostMapping 
@SecurityRequirement(name="bearer-key") @Tag

// Servicios
@Service @Transactional
```

**DTOs:**
- Input: `DatosRegistroX`, `DatosActualizacionX`
- Output: `DatosRespuestaX`, `DatosDetalleX`

**Tests:**
```java
@DisplayName("Deberia registrar un nuevo usuario correctamente")
@Test
void testRegistroUsuario() {
    // Arrange (Given)
    // Act (When)
    // Assert (Then)
}
```

### Frontend (React)

**Nomenclatura:**
- Componentes: `PascalCase` (.jsx)
- Funciones/variables: `camelCase`
- Constantes: `UPPER_CASE`

**Estructura de componentes:**
```jsx
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

function MiComponente() {
  const [state, setState] = useState(null);
  const navigate = useNavigate();
  
  const handleSubmit = async (e) => {
    e.preventDefault();
    // lógica
  };
  
  return (
    <div>
      {/* JSX */}
    </div>
  );
}

export default MiComponente;
```

**Estilos:**
- Bootstrap 5 para layout y componentes
- Estilos inline para personalizaciones
- CSS modules evitados (se usa Bootstrap)

**Gestión de estado:**
- `useState` para estado local
- Context API para estado global (AuthContext)
- `sessionStorage` para JWT

**Llamadas API:**
```javascript
const response = await fetch('http://localhost:8080/api/endpoint', {
  method: 'POST',
  headers: {
    'Content-Type': 'application/json',
    'Authorization': `Bearer ${sessionStorage.getItem('token')}`
  },
  body: JSON.stringify(data)
});
```

## 🔐 Seguridad

- **JWT**: Tokens con expiración de 2 horas
- **Bcrypt**: Hash de contraseñas
- **CORS**: Configurado para frontend en desarrollo
- **Spring Security**: Autenticación stateless
- **Roles**: PACIENTE, MEDICO, ADMINISTRADOR
- **Validación**: Jakarta Validation en DTOs

**Configuración de seguridad:**
```java
// Endpoints públicos: /auth/**
// Endpoints protegidos: Todo lo demás
// Método: JWT en header Authorization
```

## 🚀 Despliegue

### Producción con Docker

```bash
# Build de imágenes optimizadas
docker-compose -f docker-compose.prod.yml build

# Deploy
docker-compose -f docker-compose.prod.yml up -d
```

### Variables de entorno requeridas

```env
# Base de datos
DB_HOST="YOUR_HOST"
DB_NAME="YOUR_DB_NAME"
DB_USER="YOUR_USER"
DB_PASSWORD_USER="YOUR_PASSWORD"

# JWT
JWT_SECRET="YOUR_SECRET"

# Spring
SPRING_PROFILES_ACTIVE=prod
```

<!-- ## 🤝 Contribución

1. Fork del proyecto
2. Crear rama feature (`git checkout -b feature/nueva-funcionalidad`)
3. Commit cambios (`git commit -m 'feat: agregar nueva funcionalidad'`)
4. Push a la rama (`git push origin feature/nueva-funcionalidad`)
5. Abrir Pull Request

**Convenciones de commits:**
- `feat`: Nueva funcionalidad
- `fix`: Corrección de bug
- `docs`: Documentación
- `test`: Tests
- `refactor`: Refactorización
- `style`: Formato de código -->

<!-- ## 📄 Licencia

Este proyecto es de uso educativo.

## 👥 Autores

Proyecto desarrollado como parte del MVP de sistema de telemedicina.

## 📞 Soporte

Para reportar bugs o solicitar features, crear un issue en el repositorio. -->

---

**Versión:** 0.0.1-SNAPSHOT  
**Última actualización:** Octubre 2025
