package com.profitsoft.internship.service;

import com.profitsoft.internship.domain.Movie;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Service class responsible for the calculating and sorting statistics.
 * */
public class StatisticService {

    public List<Map.Entry<String, Integer>> calculate(Iterator<Movie> movies, String attributeName) {
        Map<String, Integer> frequencyMap = new ConcurrentHashMap<>();

        while (movies.hasNext()) {
            Movie movie = movies.next();
            List<String> values = extractValues(movie, attributeName);
            for (String value : values){
                frequencyMap.put(value, frequencyMap.getOrDefault(value, 0) + 1);
            }
        }

        return frequencyMap.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .collect(Collectors.toList());
    }

    private List<String> extractValues(Movie movie, String attributeName) {
        return switch (attributeName.toLowerCase()){
            case "title" -> List.of(movie.getTitle());
            case "director_name" -> List.of(movie.getDirectorName());
            case "release_year" -> List.of(String.valueOf(movie.getReleaseYear()));
            case "minutes_duration" -> List.of(String.valueOf(movie.getMinutesDuration()));
            case "comments" -> movie.getComments() != null ? movie.getComments() : Collections.emptyList();
            default -> Collections.emptyList();
        };
    }
}
