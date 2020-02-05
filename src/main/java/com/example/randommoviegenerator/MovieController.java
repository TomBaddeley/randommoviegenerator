package com.example.randommoviegenerator;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class MovieController {
    private final Logger log = LoggerFactory.getLogger(MovieController.class);
    private MovieRepository repository;

    public MovieController(MovieRepository repository) {
        this.repository = repository;
    }

    @GetMapping({"movie"})
    public ArrayList<Movie> Movies() {
        return new ArrayList<>(this.repository.findAll());
    }

    @PostMapping("/movie")
    ResponseEntity<Movie> createMovie(@Valid @RequestBody Movie movie) throws URISyntaxException {
        log.info("Request to create Movie: {}", movie);
        Movie result = repository.save(movie);
        return ResponseEntity.created(new URI("/api/movie/" + result.getId()))
                .body(result);
    }

    @PutMapping("/movie/{id}")
    ResponseEntity<Movie> updateMovie(@Valid @RequestBody Movie movie) {
        log.info("Request to update movie: {}", movie);
        Movie result = repository.save(movie);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/movie/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Long id) {
        log.info("Request to delete movie: {}", id);
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}