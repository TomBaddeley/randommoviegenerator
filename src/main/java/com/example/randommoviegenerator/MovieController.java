package com.example.randommoviegenerator;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {
    private MovieRepository repository;

    public MovieController(MovieRepository repository) {
        this.repository = repository;
    }

    @GetMapping({"/api/movies"})
    public ArrayList<Movie> Movies() {
        return new ArrayList<>(this.repository.findAll());
    }
}