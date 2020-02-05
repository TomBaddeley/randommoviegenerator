package com.example.randommoviegenerator;

import java.util.stream.Stream;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MovieCommandLineRunner implements CommandLineRunner {
    private final MovieRepository repository;

    public MovieCommandLineRunner(MovieRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) throws Exception {
        Stream.of("Star Wars: Episode IV - A New Hope", "The Joker", "Lord of the Rings:The Return Of The King", "300",
                "Fight Club", "Interstellar").forEach(name ->
                repository.save(new Movie(name))
        );
        repository.findAll().forEach(System.out::println);
    }
}