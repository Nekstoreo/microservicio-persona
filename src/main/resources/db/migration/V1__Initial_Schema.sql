-- Tabla inicial para Personas
CREATE TABLE IF NOT EXISTS personas (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT 'Identificador único de persona',
    nombre VARCHAR(100) NOT NULL COMMENT 'Primer nombre de la persona',
    apellido VARCHAR(100) NOT NULL COMMENT 'Apellido de la persona',
    email VARCHAR(255) NOT NULL UNIQUE COMMENT 'Email único de contacto',
    documento VARCHAR(20) NOT NULL UNIQUE COMMENT 'Documento de identidad único',
    estado ENUM('ACTIVO', 'INACTIVO', 'SUSPENDIDO') NOT NULL DEFAULT 'ACTIVO' COMMENT 'Estado actual de la persona',
    fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha de creación del registro',
    fecha_actualizacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha de última actualización',
    CONSTRAINT uk_email UNIQUE KEY (email),
    CONSTRAINT uk_documento UNIQUE KEY (documento)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Entidad de Personas del sistema';

-- Índices para optimizar búsquedas
CREATE INDEX idx_personas_estado ON personas(estado);
CREATE INDEX idx_personas_nombre ON personas(nombre, apellido);
