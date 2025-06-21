-- Crear la base de datos telemedicina. 
-- CREATE database telemedicina;
-- USE telemedicina;

-- Crear la tabla Usuario
CREATE TABLE Usuario (
    ID_Usuario INT PRIMARY KEY AUTO_INCREMENT,
    Nombre VARCHAR(100) NOT NULL,
    Apellido VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    clave VARCHAR(100) NOT NULL,
    Rol ENUM('Paciente', 'Doctor', 'Admin') NOT NULL
);

-- Crear la tabla Paciente
CREATE TABLE Paciente (
    ID_Paciente INT PRIMARY KEY AUTO_INCREMENT,
    ID_Usuario INT,
    Fecha_Nacimiento DATE,
    Genero VARCHAR(10),
    Telefono VARCHAR(15),
    FOREIGN KEY (ID_Usuario) REFERENCES Usuario(ID_Usuario)
);

-- Crear la tabla Doctor
CREATE TABLE Doctor (
    ID_Doctor INT PRIMARY KEY AUTO_INCREMENT,
    ID_Usuario INT,
    Especialidad VARCHAR(100),
    Telefono VARCHAR(15),
    Horario_Disponible VARCHAR(100),
    FOREIGN KEY (ID_Usuario) REFERENCES Usuario(ID_Usuario)
);

-- Crear la tabla Cita
CREATE TABLE Cita (
    ID_Cita INT PRIMARY KEY AUTO_INCREMENT,
    ID_Paciente INT,
    ID_Doctor INT,
    Fecha_Hora DATETIME,
    Estado ENUM('Programada', 'Completada', 'Cancelada') NOT NULL,
    FOREIGN KEY (ID_Paciente) REFERENCES Paciente(ID_Paciente),
    FOREIGN KEY (ID_Doctor) REFERENCES Doctor(ID_Doctor)
);

-- Crear la tabla Consulta
CREATE TABLE Consulta (
    ID_Consulta INT PRIMARY KEY AUTO_INCREMENT,
    ID_Cita INT,
    Sintomas TEXT,
    Diagnostico TEXT,
    Prescripcion TEXT,
    FOREIGN KEY (ID_Cita) REFERENCES Cita(ID_Cita)
);

-- Crear la tabla Historial_Medico
CREATE TABLE Historial_Medico (
    ID_Historial INT PRIMARY KEY AUTO_INCREMENT,
    ID_Paciente INT,
    Fecha DATE,
    Descripcion TEXT,
    FOREIGN KEY (ID_Paciente) REFERENCES Paciente(ID_Paciente)
);
