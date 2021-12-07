SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'LATIN1';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

CREATE ROLE wim WITH
	LOGIN
	NOSUPERUSER
	NOCREATEDB
	NOCREATEROLE
	INHERIT
	NOREPLICATION
	CONNECTION LIMIT -1
	PASSWORD 'e=mc**2';

CREATE TABLE public.container_data_model (
    container_id integer NOT NULL,
    contents character varying,
    state character varying,
    current_location_type character varying,
    current_location_identifier character varying,
    destination_location_type character varying,
    destination_location_identifier character varying,
    destination_location_ready boolean
);


ALTER TABLE public.container_data_model OWNER TO wim;

COPY public.container_data_model (container_id, contents, state, current_location_type, current_location_identifier, destination_location_type, destination_location_identifier, destination_location_ready) FROM stdin;
0	hash2	REGISTERED	UNKNOWN		SHIP	WR057	f
\.

ALTER TABLE ONLY public.container_data_model
    ADD CONSTRAINT container_data_model_pkey PRIMARY KEY (container_id);
