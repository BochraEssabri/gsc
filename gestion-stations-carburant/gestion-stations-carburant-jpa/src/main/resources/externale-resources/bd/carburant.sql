
DROP TABLE IF EXISTS carburant;

CREATE TABLE IF NOT EXISTS carburant (
	id_carburant int(11) NOT NULL AUTO_INCREMENT,
	
	nom varchar(16) CHARACTER SET utf8 NOT NULL,
	
	description text CHARACTER SET utf8 NOT NULL,
	
	PRIMARY KEY (id_carburant)
	
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

COMMIT;