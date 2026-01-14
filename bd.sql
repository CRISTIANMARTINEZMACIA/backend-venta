create sequence producto_id_producto_seq;

alter sequence producto_id_producto_seq owner to postgres;

create sequence venta_id_venta_seq;

alter sequence venta_id_venta_seq owner to postgres;

create sequence vista_id_vista_seq;

alter sequence vista_id_vista_seq owner to postgres;

create sequence rol_permiso_id_rol_permiso_seq;

alter sequence rol_permiso_id_rol_permiso_seq owner to postgres;

create table if not exists rol
(
    id_rol      integer generated always as identity
        primary key,
    nombre_rol  varchar(50) not null
        unique,
    descripcion varchar(255),
    primary key (),
    constraint uk_rol_nombre
        unique ()
);

alter table rol
    owner to postgres;

create table if not exists negocio
(
    id_negocio   integer generated always as identity
        primary key,
    nombre       varchar(100) not null,
    tipo_negocio varchar(50)  not null,
    direccion    varchar(255),
    telefono     varchar(20),
    ruc_nit      varchar(50)
        unique,
    primary key (),
    constraint uk_negocio_ruc_nit
        unique ()
);

alter table negocio
    owner to postgres;

create table if not exists punto_venta
(
    id_punto_venta integer generated always as identity
        primary key,
    nombre         varchar(100) not null,
    id_negocio     integer      not null
        references negocio
        references negocio,
    activo         boolean default true,
    primary key ()
);

alter table punto_venta
    owner to postgres;

create table if not exists usuario
(
    id_usuario     integer generated always as identity
        primary key,
    nombre         varchar(100) not null,
    apellido       varchar(100),
    email          varchar(100) not null
        unique,
    password_hash  varchar(255) not null,
    id_rol         integer      not null
        references rol
        references rol,
    id_negocio     integer      not null
        references negocio
        references negocio,
    fecha_creacion timestamp default CURRENT_TIMESTAMP,
    primary key (),
    constraint uk_usuario_email
        unique ()
);

alter table usuario
    owner to postgres;

create table if not exists categoria
(
    id_categoria     integer generated always as identity
        primary key,
    nombre_categoria varchar(100) not null,
    id_negocio       integer
        references negocio
        references negocio,
    primary key ()
);

alter table categoria
    owner to postgres;

create table if not exists producto
(
    id_producto      bigint generated always as identity
        primary key,
    nombre           varchar(150)         not null,
    descripcion      varchar(255),
    precio_venta     numeric(10, 2)       not null,
    id_categoria     bigint
        references categoria,
    es_inventariable boolean default true not null
);

alter table producto
    owner to postgres;

alter sequence producto_id_producto_seq owned by producto.id_producto;

create table if not exists stock
(
    id_stock        integer generated always as identity
        primary key,
    id_producto     integer        not null
        unique
        references producto
        references producto
        references producto
        references producto,
    cantidad_actual numeric(10, 3) not null,
    unidad_medida   varchar(20)    not null,
    stock_minimo    numeric(10, 3) default 0,
    primary key (),
    unique (),
    constraint uk_stock_producto
        unique ()
);

alter table stock
    owner to postgres;

create table if not exists movimiento_inventario
(
    id_movimiento    integer generated always as identity
        primary key,
    id_stock         integer        not null
        references stock
        references stock,
    tipo_movimiento  text           not null
        constraint movimiento_inventario_tipo_movimiento_check
            check (tipo_movimiento = ANY (ARRAY ['ENTRADA'::text, 'SALIDA'::text, 'AJUSTE'::text]))
        constraint movimiento_inventario_tipo_movimiento_check
            check ((tipo_movimiento)::text = ANY (ARRAY ['ENTRADA'::text, 'SALIDA'::text, 'AJUSTE'::text])),
    cantidad         numeric(10, 3) not null,
    fecha_movimiento timestamp default CURRENT_TIMESTAMP,
    id_usuario       integer
        references usuario
        references usuario,
    primary key ()
);

alter table movimiento_inventario
    owner to postgres;

create table if not exists ingrediente
(
    id_ingrediente integer generated always as identity
        primary key,
    nombre         varchar(150) not null,
    id_categoria   integer
        references categoria
        references categoria,
    unidad_medida  varchar(20)  not null,
    primary key ()
);

alter table ingrediente
    owner to postgres;

create table if not exists receta
(
    id_receta          integer generated always as identity
        primary key,
    id_producto        integer        not null
        references producto
        references producto
        references producto
        references producto,
    id_ingrediente     integer        not null
        references ingrediente
        references ingrediente,
    cantidad_necesaria numeric(10, 3) not null,
    primary key (),
    unique (id_producto, id_ingrediente),
    unique (),
    constraint uk_receta_prod_ing
        unique ()
);

alter table receta
    owner to postgres;

create table if not exists cliente
(
    id_cliente integer generated always as identity
        primary key,
    nombre     varchar(100) not null,
    apellido   varchar(100),
    telefono   varchar(20),
    email      varchar(100),
    id_negocio integer      not null
        references negocio
        references negocio,
    primary key ()
);

alter table cliente
    owner to postgres;

create table if not exists venta
(
    id_venta       bigint generated always as identity
        primary key,
    id_punto_venta bigint
        references punto_venta,
    id_usuario     bigint         not null
        references usuario,
    id_cliente     bigint
        references cliente,
    fecha_venta    timestamp default CURRENT_TIMESTAMP,
    total          numeric(10, 2) not null,
    tipo_pago      varchar(50)    not null,
    estado         varchar(255)   not null
        constraint venta_estado_check
            check ((estado)::text = ANY (ARRAY ['PENDIENTE'::text, 'COMPLETADA'::text, 'CANCELADA'::text])),
    sale_order     integer,
    sales_order    integer
);

alter table venta
    owner to postgres;

alter sequence venta_id_venta_seq owned by venta.id_venta;

create table if not exists detalle_venta
(
    id_detalle      integer generated always as identity
        primary key,
    id_venta        integer        not null
        references venta
        references venta,
    id_producto     integer        not null
        references producto
        references producto
        references producto
        references producto,
    cantidad        numeric(10, 2) not null,
    precio_unitario numeric(10, 2) not null,
    subtotal        numeric(10, 2) not null,
    primary key ()
);

alter table detalle_venta
    owner to postgres;

create table if not exists vista
(
    id_vista bigint generated always as identity
        primary key,
    nombre   varchar(100) not null
        unique
        constraint uk_vista_nombre
            unique,
    ruta     varchar(255) not null
        unique
        constraint uk_vista_ruta
            unique,
    icono    varchar(50),
    activo   boolean default true,
    padre    integer
        constraint vista_vista_id_vista_fk
            references vista
        constraint vista_vista_id_vista_fk
            references vista
);

alter table vista
    owner to postgres;

alter sequence vista_id_vista_seq owned by vista.id_vista;

create table if not exists permiso
(
    id_permiso    integer generated always as identity
        primary key,
    nombre        varchar(100) not null
        unique,
    endpoint_base varchar(255) not null
        unique,
    descripcion   text,
    primary key (),
    constraint uk_permiso_nombre
        unique (),
    constraint uk_permiso_endpoint
        unique ()
);

alter table permiso
    owner to postgres;

create table if not exists vista_permiso
(
    id_vista_permiso integer generated always as identity
        primary key,
    id_vista         integer not null
        references vista
        references vista,
    id_permiso       integer not null
        references permiso
        references permiso,
    leer             boolean default false,
    escribir         boolean default false,
    actualizar       boolean default false,
    eliminar         boolean default false,
    imprimir         boolean default false,
    lider            boolean default false,
    primary key (),
    unique (id_vista, id_permiso),
    unique (),
    constraint uk_vista_permiso
        unique ()
);

alter table vista_permiso
    owner to postgres;

create table if not exists rol_permiso
(
    id_rol_permiso        bigint generated always as identity
        primary key,
    id_rol                bigint not null
        references rol,
    id_vista_permiso      bigint not null
        references vista_permiso,
    rol_permissions_order integer,
    eliminar              boolean,
    lider                 boolean,
    imprimir              boolean,
    leer                  boolean,
    actualizar            boolean,
    escribir              boolean,
    unique (id_rol, id_vista_permiso),
    constraint uk_rol_vistapermiso
        unique (id_rol, id_vista_permiso)
);

alter table rol_permiso
    owner to postgres;

alter sequence rol_permiso_id_rol_permiso_seq owned by rol_permiso.id_rol_permiso;

