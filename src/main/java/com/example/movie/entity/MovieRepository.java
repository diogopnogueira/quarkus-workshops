package com.example.movie.entity;

import com.example.movie.entity.domain.MovieEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@ApplicationScoped
public class MovieRepository {

    @Inject
    EntityManager entityManager;

    public String persistMovie(MovieEntity movieEntity) {
        entityManager.persist(movieEntity);

        return String.valueOf(movieEntity.getId());
    }


    public MovieEntity getMovieByName(String movieName) {
        TypedQuery<MovieEntity> query = entityManager.createNamedQuery("MovieEntity.findByName", MovieEntity.class);
        query.setParameter("name", movieName);

        return query.getSingleResult();
    }

    public MovieEntity getMovieById(String movieId) {
        return entityManager.find(MovieEntity.class, Long.valueOf(movieId));
    }

    public MovieEntity updateMovie(MovieEntity movieEntity, MovieEntity newMovieEntity) {
        movieEntity.setName(newMovieEntity.getName());
        movieEntity.setGenre(newMovieEntity.getGenre());
        movieEntity.setDuration(newMovieEntity.getDuration());

        return entityManager.merge(movieEntity);

    }

    public void deleteMovie(MovieEntity movieEntity) {
        entityManager.remove(movieEntity);
    }
}
