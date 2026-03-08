--
-- PostgreSQL database dump
--

-- Dumped from database version 16.13 (Debian 16.13-1.pgdg13+1)
-- Dumped by pg_dump version 16.13 (Debian 16.13-1.pgdg13+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: comment_likes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.comment_likes (
    id uuid NOT NULL,
    created_at timestamp(6) without time zone NOT NULL,
    updated_at timestamp(6) without time zone NOT NULL,
    comment_id uuid NOT NULL,
    user_id uuid NOT NULL
);




--
-- Name: comments; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.comments (
    id uuid NOT NULL,
    created_at timestamp(6) without time zone NOT NULL,
    updated_at timestamp(6) without time zone NOT NULL,
    content text NOT NULL,
    author_id uuid NOT NULL,
    review_id uuid NOT NULL
);



--
-- Name: movie_genres; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.movie_genres (
    movie_id uuid NOT NULL,
    genre character varying(255),
    CONSTRAINT movie_genres_genre_check CHECK (((genre)::text = ANY ((ARRAY['ACTION'::character varying, 'ADVENTURE'::character varying, 'ANIMATION'::character varying, 'BIOGRAPHY'::character varying, 'COMEDY'::character varying, 'CRIME'::character varying, 'DOCUMENTARY'::character varying, 'DRAMA'::character varying, 'FAMILY'::character varying, 'FANTASY'::character varying, 'HISTORY'::character varying, 'HORROR'::character varying, 'MUSIC'::character varying, 'MUSICAL'::character varying, 'MYSTERY'::character varying, 'ROMANCE'::character varying, 'SCIENCE_FICTION'::character varying, 'SPORT'::character varying, 'THRILLER'::character varying, 'WAR'::character varying, 'WESTERN'::character varying])::text[])))
);



--
-- Name: movies; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.movies (
    id uuid NOT NULL,
    created_at timestamp(6) without time zone NOT NULL,
    updated_at timestamp(6) without time zone NOT NULL,
    director character varying(255) NOT NULL,
    release_year integer NOT NULL,
    synopsis text,
    title character varying(255) NOT NULL
);



--
-- Name: review_likes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.review_likes (
    id uuid NOT NULL,
    created_at timestamp(6) without time zone NOT NULL,
    updated_at timestamp(6) without time zone NOT NULL,
    review_id uuid NOT NULL,
    user_id uuid NOT NULL
);



--
-- Name: reviews; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.reviews (
    id uuid NOT NULL,
    created_at timestamp(6) without time zone NOT NULL,
    updated_at timestamp(6) without time zone NOT NULL,
    content text NOT NULL,
    score numeric(3,1) NOT NULL,
    author_id uuid NOT NULL,
    movie_id uuid NOT NULL
);



--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id uuid NOT NULL,
    created_at timestamp(6) without time zone NOT NULL,
    updated_at timestamp(6) without time zone NOT NULL,
    email character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    password_hash character varying(255) NOT NULL,
    role character varying(255) NOT NULL,
    status character varying(255) NOT NULL,
    CONSTRAINT users_role_check CHECK (((role)::text = ANY ((ARRAY['USER'::character varying, 'ADMIN'::character varying])::text[]))),
    CONSTRAINT users_status_check CHECK (((status)::text = ANY ((ARRAY['ACTIVE'::character varying, 'BANNED'::character varying, 'INACTIVE'::character varying])::text[])))
);



--
-- Name: comment_likes comment_likes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comment_likes
    ADD CONSTRAINT comment_likes_pkey PRIMARY KEY (id);


--
-- Name: comments comments_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comments
    ADD CONSTRAINT comments_pkey PRIMARY KEY (id);


--
-- Name: movies movies_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movies
    ADD CONSTRAINT movies_pkey PRIMARY KEY (id);


--
-- Name: review_likes review_likes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.review_likes
    ADD CONSTRAINT review_likes_pkey PRIMARY KEY (id);


--
-- Name: reviews reviews_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT reviews_pkey PRIMARY KEY (id);


--
-- Name: reviews uk2pmlmktduoqoehplij8v31dqe; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT uk2pmlmktduoqoehplij8v31dqe UNIQUE (author_id, movie_id);


--
-- Name: users uk6dotkott2kjsp8vw4d0m25fb7; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email);


--
-- Name: review_likes ukb74o5l2fmrgqg556d9nyop0ns; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.review_likes
    ADD CONSTRAINT ukb74o5l2fmrgqg556d9nyop0ns UNIQUE (user_id, review_id);


--
-- Name: comment_likes ukgu1pee3567af29uutdfy0fcjd; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comment_likes
    ADD CONSTRAINT ukgu1pee3567af29uutdfy0fcjd UNIQUE (user_id, comment_id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: comment_likes fk3wa5u7bs1p1o9hmavtgdgk1go; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comment_likes
    ADD CONSTRAINT fk3wa5u7bs1p1o9hmavtgdgk1go FOREIGN KEY (comment_id) REFERENCES public.comments(id);


--
-- Name: movie_genres fk4ak9svw913jblkfgru84h2phd; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movie_genres
    ADD CONSTRAINT fk4ak9svw913jblkfgru84h2phd FOREIGN KEY (movie_id) REFERENCES public.movies(id);


--
-- Name: comment_likes fk6h3lbneryl5pyb9ykaju7werx; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comment_likes
    ADD CONSTRAINT fk6h3lbneryl5pyb9ykaju7werx FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: reviews fk87tlqya0rq8ijfjscldpvvdyq; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT fk87tlqya0rq8ijfjscldpvvdyq FOREIGN KEY (movie_id) REFERENCES public.movies(id);


--
-- Name: comments fkdpo60i7auk5cudv7kkny8jrqb; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comments
    ADD CONSTRAINT fkdpo60i7auk5cudv7kkny8jrqb FOREIGN KEY (review_id) REFERENCES public.reviews(id);


--
-- Name: review_likes fkm2uonfg8ky6jwtu6iugkilox8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.review_likes
    ADD CONSTRAINT fkm2uonfg8ky6jwtu6iugkilox8 FOREIGN KEY (review_id) REFERENCES public.reviews(id);


--
-- Name: comments fkn2na60ukhs76ibtpt9burkm27; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comments
    ADD CONSTRAINT fkn2na60ukhs76ibtpt9burkm27 FOREIGN KEY (author_id) REFERENCES public.users(id);


--
-- Name: review_likes fknual15vv88tiqnwmi60tb2l8d; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.review_likes
    ADD CONSTRAINT fknual15vv88tiqnwmi60tb2l8d FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: reviews fkse5kx11600wtv0jh9jobvrdpi; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT fkse5kx11600wtv0jh9jobvrdpi FOREIGN KEY (author_id) REFERENCES public.users(id);


--
-- PostgreSQL database dump complete
--


