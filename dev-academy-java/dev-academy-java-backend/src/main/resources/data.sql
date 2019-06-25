DROP TABLE IF EXISTS colour;
DROP TABLE IF EXISTS people;
DROP TABLE IF EXISTS person_colours;

CREATE TABLE colour (
	id BIGINT NOT NULL PRIMARY KEY,
	name VARCHAR(10) NOT NULL
);

CREATE TABLE people (
	id BIGINT NOT NULL PRIMARY KEY,
	first_name VARCHAR(20) NOT NULL,
	last_name VARCHAR(20) NOT NULL,
	authorised BIT NOT NULL,
	enabled BIT NOT NULL
);

CREATE TABLE person_colours (
	person_id BIGINT NOT NULL,
	colour_id BIGINT NOT NULL,
	FOREIGN KEY (person_id) REFERENCES people(id),
	FOREIGN KEY (colour_id) REFERENCES colour(id)
);

INSERT INTO colour 
 (id, name) 
VALUES 
 (1, 'Red'),
 (2, 'Green'),
 (3, 'Blue');
 
INSERT INTO people 
 (id, first_name, last_name, authorised, enabled) 
VALUES 
 (1, 'Bo', 'Bob', true, false),
 (2, 'Brian', 'Allen', true, true),
 (3, 'Courtney', 'Arnold', true, true),
 (4, 'Gabriel', 'Francis', false, false),
 (5, 'George', 'Edwards', true, false),
 (6, 'Imogen', 'Kent', false, false),
 (7, 'Joel', 'Daly', true, true),
 (8, 'Lilly', 'Hale', false, false),
 (9, 'Patrick', 'Kerr', true, true),
 (10, 'Sharon', 'Halt', false, false),
 (11, 'Willis', 'Tibbs', true, false);
 
INSERT INTO person_colours 
 (person_id, colour_id) 
VALUES 
 (1, 1),
 (2, 1),
 (2, 2),
 (2, 3),
 (3, 1),
 (4, 2),
 (5, 2),
 (5, 3),
 (6, 1),
 (6, 2),
 (7, 2),
 (8, 1),
 (8, 2),
 (8, 3),
 (9, 2),
 (10, 1),
 (10, 2),
 (10, 3),
 (11, 1),
 (11, 2),
 (11, 3);
 