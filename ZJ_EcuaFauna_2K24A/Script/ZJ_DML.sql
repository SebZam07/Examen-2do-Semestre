-- database: ../DataBase/ZJ_EcuaFauna.sqlite

INSERT INTO ZJLocalidadEstructura
(IdLocalidadEstructuraPadre, Nombre) VALUES
(NULL                      , "Pais"     ),
(1                         , "Region"),
(2                         , "Provincia");

INSERT INTO ZJLocalidad (IdLocalidadPadre, IdLocalidadEstructura, Nombre) VALUES
(NULL,1,"Euador"),
(1,2,"Costa"),
(1,2,"Sierra"),
(1,2,"Oriente"),
(1,2,"Galapagos"),
--Costa
(2,3,'Esmeraladas'                   ),
(2,3,'Manabi'                        ),
(2,3,'Guayas'                        ),
(2,3,'Santa Elena'                   ),
(2,3,'Los Rios'                      ),
(2,3,'El Oro'                        ),
(2,3,'Santo Domingo de los Tsachilas'),
--Sierra
(3,3,'Carchi'    ),
(3,3,'Imbabura'  ),
(3,3,'Pichincha' ),
(3,3,'Cotopaxi'  ),
(3,3,'Tungurahua'),
(3,3,'Chimborazo'),
(3,3,'Bolivar'   ),
(3,3,'Caniar'    ),
(3,3,'Azuay'     ),
(3,3,'Loja'      ),
--Oriente
(4,3,'Sucumbios'       ),
(4,3,'Orellana'        ),
(4,3,'Napo'            ),
(4,3,'Pastaza'         ),
(4,3,'Morona Santiago' ),
(4,3,'Zamora Chinchipe'),
--Galapagos
(5,3,'Galapagos');

INSERT INTO ZJAlimento
(IDAlimentoPadre,Nombre)VALUES
(NULL           ,'Ingesta Nativa'),
(NULL           ,'GenoAlimento'),
(NULL           ,'Vacio'),
(1              ,'Carnivoro'),
(1              ,'Herbivoro'),
(1              ,'Omnivoro'),
(1              ,'Insectivoro'),
(2              ,'X'),
(2              ,'XX'),
(2              ,'XY');

INSERT INTO ZJSexo (NOMBRE) VALUES
 ("Macho")
,("Hembra")
,("Asexual")
,("Sin Confirmar");

INSERT INTO ZJHormiga
(TipoHormiga,Sexo,Provincia,GenoAlimento,IngestaNativa,EstadoCondición)VALUES
('Larva'    ,1   ,7        , 8          , 7           ,'Vivo'),
('Larva'    ,1   ,8        , 9          , 5           ,'Vivo'),
('Larva'    ,1   ,9        , 10         , 6          ,'Muerto'),
('Larva'    ,4   ,10       , 3          , 3           ,'Vivo'),
('Larva'    ,4   ,11       , 3          , 3           ,'Vivo'),
('Larva'    ,4   ,12       , 3          , 3           ,'Muerto')
;


SELECT ROW_NUMBER () OVER ( ORDER BY IDHormiga ) RowNum
,h.IDHormiga
,h.TipoHormiga
,a1.Nombre
,a2.Nombre
,s.Nombre
,l.Nombre
,h.EstadoCondición
FROM ZJHormiga h
JOIN ZJAlimento a1 ON h.GenoAlimento  = a1.IDAlimento
JOIN ZJAlimento a2 ON h.IngestaNativa = a2.IDAlimento
JOIN ZJSexo s ON h.Sexo = s.IdSexo
JOIN ZJLocalidad l ON h.Provincia = l.IdLocalidad
WHERE h.Estado = 'A';
SELECT * FROM ZJHormiga;

UPDATE ZJHormiga
SET Estado = 'X'
WHERE IDHormiga = 1;

SELECT RowNum
    ,sub.IDHormiga
    ,sub.TipoHormiga
    ,sub.NombreAlimento1
    ,sub.NombreAlimento2
    ,sub.Sexo
    ,sub.provincia
    ,sub.estadoCondicion
FROM (
    SELECT ROW_NUMBER() OVER (ORDER BY h.IDHormiga) AS RowNum
        ,h.IDHormiga
        ,h.TipoHormiga
        ,a1.Nombre AS NombreAlimento1
        ,a2.Nombre AS NombreAlimento2
        ,l.Nombre AS provincia
        ,s.Nombre AS Sexo
        ,h.EstadoCondición AS estadoCondicion
    FROM ZJHormiga h
    JOIN ZJAlimento a1 ON h.GenoAlimento = a1.IDAlimento
    JOIN ZJAlimento a2 ON h.IngestaNativa = a2.IDAlimento
    JOIN ZJSexo s ON h.Sexo = s.IdSexo
    JOIN ZJLocalidad l ON h.Provincia = l.IdLocalidad
    WHERE h.Estado = 'A'
) sub
WHERE RowNum = 11;

SELECT COUNT (*) TotalRegistros
FROM ZJHormiga
WHERE Estado = 'A';

UPDATE ZJHormiga
SET GenoAlimento = 10,
Sexo = 1
WHERE IDHormiga = 2;

UPDATE ZJHormiga
SET IngestaNativa = 6,
TipoHormiga = 'Carpinero'
WHERE IDHormiga = 2;