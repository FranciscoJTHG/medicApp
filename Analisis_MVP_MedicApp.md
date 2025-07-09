# Análisis y Plan de Acción para MVP de MedicApp

## Análisis General

El proyecto está bien estructurado, separando claramente el backend (Java/Spring Boot) del frontend (JavaScript/React). La base es sólida, pero la lógica de negocio principal definida en la base de datos aún no está implementada ni conectada.

## Backend (`MedicAppBack`) - Tareas Críticas Faltantes

El backend actual gestiona la autenticación, pero le falta la funcionalidad principal. Es necesario crear los `Controllers`, `Services` y `Repositories` para:

### 0. Modelos y Entidades Base
- **Crear entidades JPA faltantes**:
  - `Doctor`: Información del médico (especialidad, horarios, etc.).
  - `Cita`: Registro de citas agendadas.
  - `Consulta`: Detalles de consultas realizadas.
  - `Especialidad`: Catálogo de especialidades médicas.
- **Definir relaciones entre entidades**: Usuario-Doctor, Doctor-Especialidad, Paciente-Cita, Doctor-Cita, Cita-Consulta.
- **Crear DTOs**: Para transferencia de datos (DatosRegistroDoctor, DatosAgendarCita, DatosConsulta, etc.).

### 1. Gestión de Doctores
- **`DoctorController`**:
  - `GET /api/doctores`: Listar todos los doctores.
  - `GET /api/doctores/{id}`: Ver el perfil de un doctor.
  - `GET /api/doctores/especialidad/{especialidad}`: Filtrar doctores por especialidad.

### 2. Gestión de Citas
- **`CitaController`**:
  - `POST /api/citas`: Permitir a un paciente agendar una nueva cita.
  - `GET /api/citas/paciente/{id}`: Permitir a un paciente ver su historial de citas.
  - `GET /api/citas/doctor/{id}`: Permitir a un doctor ver su agenda.
  - `PUT /api/citas/{id}/cancelar`: Permitir cancelar una cita.

### 3. Gestión de Consultas
- **`ConsultaController`**:
  - `POST /api/consultas`: Permitir a un doctor crear un registro de la consulta (diagnóstico, etc.).
  - `GET /api/consultas/cita/{id}`: Permitir al paciente ver los detalles de una consulta pasada.

### 4. Seguridad y Autorización
- **Implementar autorización por roles**: Diferenciar permisos entre PACIENTE, MEDICO y ADMIN.
- **Proteger endpoints**: Asegurar que solo usuarios autorizados accedan a ciertos recursos.
- **Validación de datos**: Añadir validaciones en DTOs y controladores usando `@Valid` y anotaciones de validación.

### 5. Funcionalidades de Negocio Adicionales
- **Sistema de disponibilidad**: Permitir a los médicos configurar sus horarios disponibles.
- **Gestión de especialidades**: CRUD para administrar el catálogo de especialidades médicas.
- **Historial médico**: Endpoint para consultar el historial completo de un paciente.

## Frontend (`MedicAppFront`) - Tareas Críticas Faltantes

Las vistas del frontend existen, pero necesitan ser conectadas a la lógica del backend.

### 1. Capa de Servicio de API
- Crear un directorio (`src/api` o `src/services`) para centralizar todas las llamadas `fetch` o `axios` al backend. Esto es crucial para la mantenibilidad.

### 2. Flujo de Agendamiento de Cita
- **Listar Doctores**: La vista `Especialidades.jsx` debe consumir el endpoint `GET /api/doctores` para mostrar los doctores reales.
- **Agendar Cita**: La vista `AgendarCita.jsx` debe llamar al endpoint `POST /api/citas` para guardar la cita seleccionada.

### 3. Dashboards de Usuario
- **Dashboard del Paciente (`PacienteView.jsx`)**: Debe llamar a `GET /api/citas/paciente/{id}` para mostrar "Próximas Citas" y "Citas Pasadas".
- **Dashboard del Médico (`MedicoView.jsx`)**: Debe llamar a `GET /api/citas/doctor/{id}` para mostrar su agenda.

### 4. Autenticación y Autorización
- **Completar AuthProvider**: El sistema de autenticación actual está incompleto.
- **Manejo de tokens JWT**: Almacenar y enviar tokens en las peticiones HTTP.
- **Rutas protegidas**: Implementar protección de rutas según el rol del usuario.
- **Logout funcional**: Implementar cierre de sesión completo con limpieza de estado.

### 5. Navegación y UX
- **Navbar dinámico**: Mostrar opciones diferentes según el rol del usuario logueado.
- **Redirecciones post-login**: Dirigir a diferentes dashboards según el rol (paciente/médico/admin).
- **Validación de formularios**: Añadir validaciones en registro, login y agendamiento.
- **Manejo de errores**: Mostrar mensajes de error más específicos y user-friendly.

### 6. Feedback y Manejo de Estado
- Implementar indicadores de carga (`spinners`) durante las llamadas a la API.
- Mostrar notificaciones de éxito ("Cita agendada") o error.
- **Búsqueda y filtros**: Implementar búsqueda de doctores por nombre, especialidad, disponibilidad.

## Plan de Acción Sugerido

Abordar el desarrollo de manera incremental:

1.  **Exponer Datos de Doctores**: Implementar el `DoctorController` en el backend para listar doctores. Es el primer paso para que un paciente pueda iniciar el proceso de agendamiento.
2.  **Conectar Frontend**: Mostrar la lista de doctores en la vista correspondiente de React.
3.  **Implementar Agendamiento**: Crear la funcionalidad de agendamiento de citas (`CitaController`) y conectarla desde el frontend.
4.  **Desarrollar Dashboards**: Crear las vistas de paciente y médico para que puedan ver sus citas.
5.  **Añadir Detalles de Consulta**: Finalmente, implementar la lógica para que los médicos puedan añadir detalles post-consulta.

## Tareas Adicionales para Calidad y Robustez

### Testing y Validación
- **Tests de integración**: Para los nuevos controladores y servicios.
- **Validación de datos**: Asegurar integridad de datos en todos los formularios.
- **Manejo de excepciones**: Mejorar el manejo de errores en backend y frontend.

### Funcionalidades Complementarias
- **Sistema de notificaciones**: Implementar notificaciones básicas (email o en aplicación).
- **Gestión de horarios**: Permitir a los médicos configurar y modificar su disponibilidad.
- **Historial de consultas**: Vista completa del historial médico para pacientes y doctores.
