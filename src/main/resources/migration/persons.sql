-- Table: public.persons

-- DROP TABLE IF EXISTS public.persons;

CREATE TABLE IF NOT EXISTS public.persons
(
    id bigint NOT NULL DEFAULT nextval('persons_id_seq'::regclass),
    login character varying(256) COLLATE pg_catalog."default",
    password character varying(256) COLLATE pg_catalog."default",
    "position" character varying(256) COLLATE pg_catalog."default",
    CONSTRAINT persons_pkey PRIMARY KEY (id)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.persons
    OWNER to postgres;