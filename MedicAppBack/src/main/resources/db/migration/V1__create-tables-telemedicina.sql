-- Crear la base de datos telemedicina.
-- CREATE database telemedicina;
-- USE telemedicina;

-- Crear la tabla Usuario
CREATE TABLE usuario (
    id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    clave VARCHAR(100) NOT NULL,
    telefono VARCHAR(15),
    rol VARCHAR(30) NOT NULL,
    fecha_Creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deprecado BOOLEAN DEFAULT FALSE
);

-- Crear la tabla Paciente
CREATE TABLE paciente (
    id_paciente INT PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT,
    fecha_Nacimiento DATE,
    genero VARCHAR(10),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

-- Crear la tabla Especialidades
CREATE TABLE especialidades (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL UNIQUE
);

-- Crear la tabla Doctor
CREATE TABLE doctor (
    id_doctor INT PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT,
    id_especialidad BIGINT,
    fecha_Nacimiento DATE,
    genero VARCHAR(10),
    biografia VARCHAR(100),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    FOREIGN KEY (id_especialidad) REFERENCES especialidades(id)
);

-- Crear la tabla Cita
CREATE TABLE cita (
    id_cita INT PRIMARY KEY AUTO_INCREMENT,
    id_paciente INT,
    id_doctor INT,
    estado VARCHAR(30) NOT NULL,
    notas VARCHAR(255),
    fecha_Cita DATETIME NOT NULL,
    fecha_Creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_paciente) REFERENCES paciente(id_paciente),
    FOREIGN KEY (id_doctor) REFERENCES doctor(id_doctor)
);

-- Crear la tabla Consulta
CREATE TABLE consulta (
    id_consulta INT PRIMARY KEY AUTO_INCREMENT,
    id_cita INT,
    sintomas VARCHAR(255),
    diagnostico VARCHAR(255),
    prescripcion VARCHAR(255),
    fecha_consulta DATETIME NOT NULL,
    fecha_Creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_cita) REFERENCES cita(id_cita)
);

-- Crear la tabla Historial_Medico
CREATE TABLE historial_Medico (
    id_historial INT PRIMARY KEY AUTO_INCREMENT,
    id_paciente INT,
    descripcion VARCHAR(255),
    tipo VARCHAR(50),
    fecha_Creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_paciente) REFERENCES paciente(id_paciente)
);

-- Crear la tabla Horario_Doctor
CREATE TABLE horario_doctor (
    id_Horario INT PRIMARY KEY AUTO_INCREMENT,
    id_Doctor INT,
    dia_semana VARCHAR(20) NOT NULL,
    hora_inicio TIME,
    hora_fin TIME,
    FOREIGN KEY (id_Doctor) REFERENCES doctor(id_doctor)
);