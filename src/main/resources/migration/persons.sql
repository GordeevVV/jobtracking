-- Table: public.persons

-- DROP TABLE IF EXISTS public.persons;

CREATE TABLE IF NOT EXISTS public.persons
(
    id bigint NOT NULL,
    login character varying COLLATE pg_catalog."default",
    password character varying COLLATE pg_catalog."default",
    role character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT persons_pkey PRIMARY KEY (id),
    CONSTRAINT ukbe68cfdb8jkwvgh9902j3bq1l UNIQUE (login)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.persons
    OWNER to postgres;