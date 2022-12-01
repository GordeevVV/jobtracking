-- Table: public.persons

-- DROP TABLE IF EXISTS public.persons;

CREATE TABLE IF NOT EXISTS public.persons
(
    id bigint NOT NULL,
    login character varying COLLATE pg_catalog."default",
    password character varying COLLATE pg_catalog."default",
    "position" character varying COLLATE pg_catalog."default",
    CONSTRAINT persons_pkey PRIMARY KEY (id),
    CONSTRAINT login_unique UNIQUE (login)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.persons
    OWNER to postgres;