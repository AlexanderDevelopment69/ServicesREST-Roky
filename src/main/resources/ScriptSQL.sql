create database rokydb;
use rokydb;

create table rokydb.cliente
(
    id        bigint auto_increment
        primary key,
    direccion varchar(255) null,
    nombre    varchar(255) null,
    password  varchar(255) null
);

create table rokydb.menu
(
    id     bigint auto_increment
        primary key,
    nombre varchar(255) null,
    precio double       null
);

create table rokydb.personal
(
    id       bigint auto_increment
        primary key,
    cargo    varchar(255) null,
    nombre   varchar(255) null,
    password varchar(255) null
);

create table rokydb.pedido
(
    id          bigint auto_increment
        primary key,
    descripcion varchar(255) null,
    monto       double       null,
    cliente_id  bigint       null,
    personal_id bigint       null,
    constraint FK30s8j2ktpay6of18lbyqn3632
        foreign key (cliente_id) references rokydb.cliente (id),
    constraint FK9vtc74l3gp8whxetpsv60509s
        foreign key (personal_id) references rokydb.personal (id)
);

create table rokydb.pago
(
    id        bigint auto_increment
        primary key,
    metodo    varchar(255) null,
    monto     double       null,
    pedido_id bigint       null,
    constraint UK_cjukh0gqou26iq8ro20j829ug
        unique (pedido_id),
    constraint FK8fojprqy7kv7k3d192m91e027
        foreign key (pedido_id) references rokydb.pedido (id)
);

create table rokydb.pedido_menu
(
    pedido_id bigint not null,
    menu_id   bigint not null,
    constraint FKdraskuoyse1y81invi1as6fwj
        foreign key (pedido_id) references rokydb.pedido (id),
    constraint FKq0cgnbdgiqdl6dylrnkgslx2w
        foreign key (menu_id) references rokydb.menu (id)
);

create table rokydb.producto
(
    id          bigint auto_increment
        primary key,
    descripcion varchar(255) null,
    nombre      varchar(255) null,
    precio      double       null,
    stock       int          not null
);

create table rokydb.menu_productos
(
    menu_id     bigint not null,
    producto_id bigint not null,
    constraint FK77evim7cr5nimbw35bqqe7tlk
        foreign key (producto_id) references rokydb.producto (id),
    constraint FKcm5wckdhgokdcs59yrsjpw9qj
        foreign key (menu_id) references rokydb.menu (id)
);

