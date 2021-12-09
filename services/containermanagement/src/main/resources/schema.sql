

CREATE TABLE IF NOT EXISTS public.container_data_model (
                                             container_id integer NOT NULL,
                                             contents character varying,
                                             state character varying,
                                             current_location_type character varying,
                                             current_location_identifier character varying,
                                             destination_location_type character varying,
                                             destination_location_identifier character varying,
                                             destination_location_ready boolean,
					     PRIMARY KEY(container_id)
);


--- ALTER TABLE public.container_data_model OWNER TO postgres;

--- ALTER TABLE ONLY public.container_data_model
---    ADD CONSTRAINT container_data_model_pkey PRIMARY KEY (container_id);
