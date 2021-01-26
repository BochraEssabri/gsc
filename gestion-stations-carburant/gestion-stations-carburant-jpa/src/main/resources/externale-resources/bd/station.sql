
DROP TABLE IF EXISTS station;

CREATE TABLE IF NOT EXISTS station (
	id_station int(11) NOT NULL AUTO_INCREMENT,
	
	nom_station varchar(64) CHARACTER SET utf8 NOT NULL,
	
	ville varchar(64) CHARACTER SET utf8 NOT NULL,
	
	adresse text CHARACTER SET utf8 NOT NULL,
	
	PRIMARY KEY (id_station)
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

COMMIT;