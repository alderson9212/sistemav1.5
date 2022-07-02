
DROP TABLE IF EXISTS clientes;
CREATE TABLE clientes(
idcliente integer not null,
nombre text not null,
domicilio text,
email text,
telefono varchar(45),
activo boolean,
sexo   varchar(45),
idcolonia integer not null,

primary key(idcliente),
foreign key(idcolonia) references colonias(idcolonia)
);



DROP TABLE IF EXISTS municipios;
CREATE TABLE municipios(
idmunicipio integer not null,
nombre text not null,
idestado integer not null,

primary key(idmunicipio),
foreign key(idestado)references estados(idestado)
);


DROP TABLE IF EXISTS estados;
CREATE TABLE estados(
idestado integer not null,
nombre text not null,
primary key(idestado)
);


DROP TABLE IF EXISTS colonias;
CREATE TABLE colonias(
idcolonia integer not null,
nombre text not null,
idmunicipio integer not null,

primary key(idcolonia),
foreign key(idmunicipio) references municipios(idmunicipio)
);


DROP TABLE IF EXISTS municipios;
CREATE TABLE municipios(
idmunicipio integer not null,
nombre text not null,
idestado integer not null,

primary key(idmunicipio),
foreign key(idestado)references estados(idestado)
);




DROP TABLE IF EXISTS productos;
CREATE TABLE productos(
	idproducto integer not null,
	nombre text not null,
	precio numeric not null,
	preciocliente numeric,
	stock integer,
	activarpreciocliente boolean,
	preciodeproveedor numeric,
	idcategoria integer not null,
	idproveedor integer not null,
	idunidadm integer not null,

	primary key(idproducto)

);



DROP TABLE IF EXISTS unidadesm;
CREATE TABLE unidadesm(
      idunidadm integer,
      descripcion varchar(45),

      primary key(idunidadm)
       
);


DROP TABLE IF EXISTS proveedores;
CREATE TABLE proveedores(
     idproveedor integer,
     nombre      text,
     telefono    varchar(45),
     mail        text,

     primary key(idproveedor)
       
);


DROP TABLE IF EXISTS categorias;
CREATE TABLE categorias(
      idcategoria integer,
      descripcion varchar(45),

      primary key(idcategoria)
       
);
DROP TABLE IF EXISTS ventas_realizadas;
CREATE TABLE ventas_realizadas(
   idticket integer,
   fecha timestamp,
   tipoventa varchar(45),
   totalventa numeric,
   idusuario varchar(45),

   primary key(idticket)   
);


DROP TABLE IF EXISTS detalles_venta;
CREATE TABLE detalles_venta(
idticket integer,
producto varchar(45),
totalproductos integer,
total numeric,
cliente text default 'xxxxxxxxxxxx'
);



DROP TABLE IF EXISTS usuarios;
CREATE TABLE usuarios(
id integer,
nombre varchar(45),
pass text,

primary key(nombre)
);

insert into usuarios values(1,'root','63a9f0ea7bb98050796b649e85481845');



DROP TABLE IF EXISTS tiposventa;
CREATE TABLE tiposventa(
idtipo integer,
descripcion varchar(45),
primary key(idtipo)

);


INSERT INTO tiposventa VALUES(1,'CLIENTE');
INSERT INTO tiposventa VALUES(2,'PUBLICO');


DROP TABLE IF EXISTS nombre_negocio;
CREATE TABLE nombre_negocio(
   nombre text
);
