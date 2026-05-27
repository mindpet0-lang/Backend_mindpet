-- Tabla Usuario
CREATE TABLE IF NOT EXISTS usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    correo VARCHAR(255) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL,
    fecha_nacimiento VARCHAR(255),
    monedas INT DEFAULT 0,
    foto_perfil LONGTEXT
);

-- Tabla Rol
CREATE TABLE IF NOT EXISTS roles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    descripcion VARCHAR(255)
);

-- Tabla Mascotas
CREATE TABLE IF NOT EXISTS mascotas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    nivel INT,
    energia INT,
    felicidad INT,
    higiene INT,
    hambre INT,
    duenio_id BIGINT,
    last_update BIGINT,
    is_sleeping BOOLEAN,
    FOREIGN KEY (duenio_id) REFERENCES usuario(id)
);

-- Tabla Publicaciones
CREATE TABLE IF NOT EXISTS publicaciones (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    contenido LONGTEXT NOT NULL,
    fecha_creacion DATETIME,
    usuario_id BIGINT NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

-- Tabla Comentarios
CREATE TABLE IF NOT EXISTS comentarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    contenido LONGTEXT NOT NULL,
    fecha_creacion DATETIME DEFAULT CURRENT_TIMESTAMP,
    publicacion_id BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL,
    FOREIGN KEY (publicacion_id) REFERENCES publicaciones(id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

-- Tabla Likes Publicaciones
CREATE TABLE IF NOT EXISTS likes_publicaciones (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    publicacion_id BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL,
    UNIQUE KEY uk_likes_pub (publicacion_id, usuario_id),
    FOREIGN KEY (publicacion_id) REFERENCES publicaciones(id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

-- Tabla Likes Comentarios
CREATE TABLE IF NOT EXISTS likes_comentarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    comentario_id BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL,
    UNIQUE KEY uk_likes_com (comentario_id, usuario_id),
    FOREIGN KEY (comentario_id) REFERENCES comentarios(id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

-- Tabla Diarios
CREATE TABLE IF NOT EXISTS diarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    contenido LONGTEXT,
    titulo VARCHAR(255),
    emocion VARCHAR(255),
    usuario_id INT
);

-- Tabla Comida Mascota
CREATE TABLE IF NOT EXISTS comida_mascota (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    energia INT
);

-- Tabla Inventarios
CREATE TABLE IF NOT EXISTS inventarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    nombre VARCHAR(255),
    imagen VARCHAR(255),
    cantidad INT,
    categoria VARCHAR(255)
);

-- Tabla Messages
CREATE TABLE IF NOT EXISTS messages (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    content TEXT,
    sender VARCHAR(255),
    timestamp DATETIME
);
