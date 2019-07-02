
/*==============================================================*/
/* Table: especialidad */
/*==============================================================*/
create table especialidad
(
   idEspecialidad       int not null,
   nombreEspecialidad   varchar(100) not null,
   primary key (idEspecialidad)
);

/*==============================================================*/
/* Table: medico                                                */
/*==============================================================*/
create table medico
(
   idMedico             int not null,
   idEspecialidad       int,
   idPersona            int,
   emailMedico          varchar(100) not null,
   estadoMedico         bool not null,
   primary key (idMedico)
);

/*==============================================================*/
/* Table: Paciente                                              */
/*==============================================================*/
create table Paciente
(
   idPaciente           int not null,
   idPersona            int,
   nombreResponsable    varchar(100) not null,
   telefonoEmergencia   varchar(30) not null,
   vinculoResponsable   varchar(50) not null,
   estadoCivil          varchar(50) not null,
   estadoPaciente       bool not null,
   primary key (idPaciente)
);

/*==============================================================*/
/* Table: Persona                                               */
/*==============================================================*/
create table persona
(
   idPersona            int not null,
   nombrePersona        varchar(100) not null,
   apellidoPersona      varchar(100) not null,
   departamento         varchar(100) not null,
   municipio            varchar(100) not null,
   telefono             varchar(20) not null,
   dui                  varchar(15) not null,
   genero               varchar(15) not null,
   fechaNacimiento      date,
   primary key (idPersona)
);

/*==============================================================*/
/* Table: Rol                                                   */
/*==============================================================*/
create table rol
(
   idRol                int not null,
   nombreRol            varchar(100) not null,
   primary key (idRol)
);

/*==============================================================*/
/* Table: Usuario                                               */
/*==============================================================*/
create table usuario
(
   idUsuario            int not null,
   idRol                int,
   idPersona            int,
   username             varchar(100) not null,
   password             varchar(250) not null,
   primary key (idUsuario)
);

alter table medico add constraint fk_es_un foreign key (idPersona)
      references persona (idPersona) on delete restrict on update restrict;

alter table medico add constraint fk_tiene foreign key (idEspecialidad)
      references especialidad (idEspecialidad) on delete restrict on update restrict;

alter table paciente add constraint fk_es foreign key (idPersona)
      references persona (idPersona) on delete restrict on update restrict;

alter table usuario add constraint fk_posee foreign key (idPersona)
      references persona (idPersona) on delete restrict on update restrict;

alter table usuario add constraint fk_tiene_un foreign key (idRol)
      references rol (idRol) on delete restrict on update restrict;

CREATE VIEW `v_usuario_rol` AS
select  u.username, u.password, r.nombreRol
from usuario u
inner join rol r on r.idRol=u.idRol;

