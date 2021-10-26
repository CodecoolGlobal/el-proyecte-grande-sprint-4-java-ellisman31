DROP TABLE IF EXISTS public.website_user CASCADE;
CREATE TABLE public.website_user
(
    id           serial  NOT NULL PRIMARY KEY,
    name         varchar(200),
    age          integer NOT NULL,
    phone_number varchar(200),
    is_admin      boolean,
    group_name     text
);

ALTER TABLE IF EXISTS ONLY public.worker DROP CONSTRAINT IF EXISTS fk_user_id CASCADE;

DROP TABLE IF EXISTS public.worker CASCADE;
CREATE TABLE public.worker
(
    id          serial  NOT NULL PRIMARY KEY,
    user_id     integer NOT NULL,
    is_available boolean,
    rate numeric,
    description text
);

ALTER TABLE ONLY public.worker
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES public.website_user(id);


DROP TABLE IF EXISTS public.profession CASCADE;
CREATE TABLE public.profession
(
    id          serial  NOT NULL PRIMARY KEY,
    profession_name     varchar(200)
);


ALTER TABLE IF EXISTS ONLY public.worker_experience DROP CONSTRAINT IF EXISTS fk_profession_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.worker_experience DROP CONSTRAINT IF EXISTS fk_worker_id CASCADE;

DROP TABLE IF EXISTS public.worker_experience CASCADE;
CREATE TABLE public.worker_experience
(
    id            serial  NOT NULL PRIMARY KEY,
    worker_id     integer NOT NULL,
    profession_id integer NOT NULL,
    experience_years    numeric NOT NULL
);

ALTER TABLE ONLY public.worker_experience
    ADD CONSTRAINT fk_profession_id FOREIGN KEY (profession_id) REFERENCES public.profession(id);

ALTER TABLE ONLY public.worker_experience
    ADD CONSTRAINT fk_worker_id FOREIGN KEY (worker_id) REFERENCES public.website_user(id);


DROP TABLE IF EXISTS public.work_object CASCADE;
CREATE TABLE public.work_object
(
    id          serial  NOT NULL PRIMARY KEY,
    work_object     varchar(200)
);

ALTER TABLE IF EXISTS ONLY public.worker_requirement DROP CONSTRAINT IF EXISTS fk_profession_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.worker_requirement DROP CONSTRAINT IF EXISTS fk_work_object_id CASCADE;


DROP TABLE IF EXISTS public.work_requirement CASCADE;
CREATE TABLE public.work_requirement
(
    id          serial  NOT NULL PRIMARY KEY,
    work_object_id     integer,
    profession_id integer
);

ALTER TABLE ONLY public.work_requirement
    ADD CONSTRAINT fk_profession_id FOREIGN KEY (profession_id) REFERENCES public.profession(id);

ALTER TABLE ONLY public.work_requirement
    ADD CONSTRAINT fk_work_object_id FOREIGN KEY (work_object_id) REFERENCES public.work_object(id);