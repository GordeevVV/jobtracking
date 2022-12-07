-- Table: public.tasks

-- DROP TABLE IF EXISTS public.tasks;

CREATE TABLE IF NOT EXISTS public.tasks
(
    id bigint NOT NULL,
    name character varying COLLATE pg_catalog."default",
    status character varying COLLATE pg_catalog."default",
    difficulty double precision,
    description character varying COLLATE pg_catalog."default",
    report character varying COLLATE pg_catalog."default",
    person_id bigint NOT NULL,
    CONSTRAINT tasks_pkey PRIMARY KEY (id),
    CONSTRAINT fk83wtk1uwa6l9aokatgtwbnik9 FOREIGN KEY (person_id)
        REFERENCES public.persons (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.tasks
    OWNER to postgres;