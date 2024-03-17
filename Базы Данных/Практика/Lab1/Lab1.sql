--droppping enums
DROP TYPE IF EXISTS gender_enum CASCADE;

--dropping tables
DROP TABLE IF EXISTS crew_member CASCADE;
DROP TABLE IF EXISTS ship CASCADE;
DROP TABLE IF EXISTS star_system CASCADE;
DROP TABLE IF EXISTS planet CASCADE;
DROP TABLE IF EXISTS location CASCADE;
DROP TABLE IF EXISTS coordinates CASCADE;
DROP TABLE IF EXISTS holes CASCADE;
DROP TABLE IF EXISTS columns CASCADE;
DROP TABLE IF EXISTS planetVisit CASCADE;

--creating types
CREATE TYPE gender_enum AS ENUM('male', 'female');

--creating tables
CREATE TABLE crew_member(
    passenger_id serial PRIMARY KEY,
    name text NOT NULL,
    surname text NOT NULL,
    gender gender_enum NOT NULL,
    job text NOT NULL,
    age integer NOT NULL
);


CREATE TABLE coordinates(
    coordinates_id serial PRIMARY KEY,
    x integer NOT NULL,
    y integer NOT NULL,
    z integer NOT NULL
);

CREATE TABLE star_system(
    system_id serial PRIMARY KEY,
    coordinate_id integer REFERENCES coordinates(coordinates_id),
    name text NOT NULL, 
    planet_count integer NOT NULL
);

CREATE TABLE planet(
    planet_id serial PRIMARY KEY,
    system_id integer REFERENCES star_system(system_id),
    name text NOT NULL,
    isPopulated boolean NOT NULL,
    coordinates_id integer REFERENCES coordinates(coordinates_id)
);

CREATE TABLE location(
    location_id serial PRIMARY KEY,
    planet_id integer REFERENCES planet(planet_id),
    name text NOT NULL,
    coordinates_id integer REFERENCES coordinates(coordinates_id)
);

CREATE TABLE holes(
    holes_id serial PRIMARY KEY,
    location_id integer REFERENCES location(location_id),
    count integer NOT NULL
);

CREATE TABLE columns(
    columns_id serial PRIMARY KEY,
    location_id integer REFERENCES location(location_id),
    count integer NOT NULL,
    age integer NOT NULL
);

CREATE TABLE planetVisit(
    passenger_id integer  REFERENCES crew_member(passenger_id),
    planet_id integer REFERENCES planet(planet_id),
    visit_date date NOT NULL,
    PRIMARY KEY (passenger_id, planet_id)
);

CREATE TABLE ship(
    ship_id serial PRIMARY KEY,
    model text NOT NULL,
    name text NOT NULL,
    creation_year integer NOT NULL,
    location_id integer REFERENCES location(location_id)
);

INSERT INTO crew_member(name, surname, gender, job, age) VALUES (
    'marik',
    'ivanov',
    'male',
    'engineer',
    23
);

INSERT INTO coordinates(x, y, z) VALUES (
    123,
    12,
    1
);

INSERT INTO star_system(coordinate_id, name, planet_count) VALUES (
    1,
    'Sun',
    8
);

INSERT INTO coordinates(x, y, z) VALUES (
    234,
    23,
    2
);

INSERT INTO planet(system_id, name, isPopulated, coordinates_id) VALUES(
    1,
    'Earth',
    TRUE,
    2
);

INSERT INTO coordinates(x, y, z) VALUES (
    345,
    34,
    3
);

INSERT INTO location(planet_id, name, coordinates_id) VALUES(
    1,
    'Rocks',
    3
);

INSERT INTO holes(location_id, count) VALUES(
    1,
    233
);

INSERT INTO columns(location_id, count, age) VALUES(
    1,
    322,
    98
);

INSERT INTO planetVisit(passenger_id, planet_id, visit_date) VALUES(
    1,
    1,
    '2112-05-24'
);

INSERT INTO ship(model, name, creation_year, location_id) VALUES(
    'RDAL-564464',
    'VOSTOK-99',
    2099,
    1
);

