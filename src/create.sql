DROP TABLE IF EXISTS DOMICILIOS; CREATE TABLE DOMICILIOS (ID LONG AUTO_INCREMENT PRIMARY KEY, CALLE VARCHAR(50) NOT NULL, NUMERO INT NOT NULL, LOCALIDAD VARCHAR(50) NOT NULL, PROVINCIA VARCHAR(50) NOT NULL);

DROP TABLE IF EXISTS PACIENTES;
CREATE TABLE PACIENTES (ID LONG AUTO_INCREMENT PRIMARY KEY, NOMBRE VARCHAR(100) NOT NULL, APELLIDO VARCHAR(100) NOT NULL, DNI INT NOT NULL, FECHA DATE NOT NULL, DOMICILIO_ID LONG);

-- para test --
INSERT INTO DOMICILIOS(CALLE, NUMERO, LOCALIDAD, PROVINCIA) VALUES ('Av Siempre Verde', 123, 'Ciudad de México', 'Ciudad de México'), ('Calle Roble', 456, 'Madrid', 'Madrid');

INSERT INTO PACIENTES (NOMBRE, APELLIDO, DNI, FECHA, DOMICILIO_ID) VALUES ('Ana', 'Martínez', 12345678, '2023-06-01', 1), ('Juan', 'González', 87654321, '2023-06-01', 2);