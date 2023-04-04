package com.example.movie.repository.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Objects;

@Entity(name = "movie")
@NoArgsConstructor
public class MovieEntity extends PanacheEntity {

    @Column(name = "name")
    public String name;

    @Column(name = "genre")
    public String genre;

    @Column(name = "duration")
    public int duration;

    public MovieEntity(Long id, String name, String genre, int duration) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieEntity that = (MovieEntity) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}
