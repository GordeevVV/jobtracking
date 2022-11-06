-- Table: public.tasks

-- DROP TABLE IF EXISTS public.tasks;

CREATE TABLE IF NOT EXISTS public.tasks
(
    id bigint NOT NULL DEFAULT nextval('tasks_id_seq'::regclass),
    name character varying(256) COLLATE pg_catalog."default",
    status character varying(256) COLLATE pg_catalog."default",
    difficulty double precision,
    description character varying(256) COLLATE pg_catalog."default",
    "implementerId" bigint,
    report character varying(256) COLLATE pg_catalog."default",
    CONSTRAINT tasks_pkey PRIMARY KEY (id),
    CONSTRAINT "tasks_implementerId_fkey" FOREIGN KEY ("implementerId")
        REFERENCES public.persons (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE RESTRICT
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.tasks
    OWNER to postgres;