-- database: ../DataBase/ZJ_EcuaFauna.sqlite

DROP TABLE IF EXISTS ZJHormiga;
DROP TABLE IF EXISTS ZJLocalidad;
DROP TABLE IF EXISTS ZJLocalidadEstructura;
DROP TABLE IF EXISTS ZJAlimento;
DROP TABLE IF EXISTS ZJSexo;

CREATE TABLE ZJLocalidadEstructura (
     IdLocalidadEstructura      INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT
    ,IdLocalidadEstructuraPadre INTEGER REFERENCES  ZJLocalidadEstructura(IdLocalidadEstructura)
    ,Nombre                    VARCHAR(10) NOT NULL
    ,Estado                    VARCHAR(1)  NOT NULL DEFAULT('A')
    ,FechaCreacion             DATETIME    DEFAULT(datetime('now','localtime'))
    ,FechaModificacion         DATETIME
);

CREATE TABLE ZJLocalidad (
     IdLocalidad             INTEGER     NOT NULL PRIMARY KEY AUTOINCREMENT
    ,IdLocalidadPadre        INTEGER     REFERENCES  ZJLocalidad (IdLocalidad)
    ,IdLocalidadEstructura   INTEGER     REFERENCES  ZJLocalidadEstructura(IdLocalidadEstructura)
    ,Nombre                 VARCHAR(20) NOT NULL
    ,Estado                 VARCHAR(1)  NOT NULL DEFAULT('A')
    ,FechaCrea              DATETIME    DEFAULT(datetime('now','localtime'))
    ,FechaModifica          DATETIME
);

CREATE TABLE ZJSexo (
     IdSexo         INTEGER     NOT NULL PRIMARY KEY AUTOINCREMENT
    ,Nombre         VARCHAR(10) NOT NULL UNIQUE
    ,Estado         VARCHAR(1)  NOT NULL DEFAULT('A')
    ,FechaCrea      DATETIME    DEFAULT(datetime('now','localtime'))
    ,FechaModifica  DATETIME
);


CREATE TABLE ZJAlimento(
    IDAlimento          INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT
    ,IDAlimentoPadre    INTEGER REFERENCES ZJAlimento(IDAlimento)
    ,Nombre             VARCHAR(10) NOT NULL
    ,Estado             VARCHAR(1) NOT NULL DEFAULT('A')
    ,FechaCreacion      DATETIME DEFAULT(datetime('now','localtime'))
    ,FechaModificacion  DATETIME
);

CREATE TABLE ZJHormiga
(
    IDHormiga       INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    TipoHormiga     VARCHAR(50),
    Sexo            INTEGER REFERENCES ZJSexo(IdSexo),
    Provincia       INTEGER REFERENCES ZJLocalidad(IdLocalidad),
    GenoAlimento    INTEGER REFERENCES ZJAlimento(IDAlimento),
    IngestaNativa   INTEGER REFERENCES ZJAlimento(IDAlimento),
    EstadoCondición VARCHAR(10)  NOT NULL DEFAULT('Vivo'),
    Estado          VARCHAR(1)  NOT NULL DEFAULT('A'),
    FechaCrea       DATETIME    DEFAULT(datetime('now','localtime')),
    FechaModifica   DATETIME
);