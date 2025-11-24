package com.profitsoft.internship.service;

import com.profitsoft.internship.domain.Movie;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StatisticServiceTest {
    private StatisticService statisticService;
    private List<Movie> testMovies;

    @BeforeEach
    void setUp() {
        statisticService = new StatisticService();
        testMovies = new ArrayList<>();

        testMovies.add(new Movie(
                "Inception",
                List.of("Sci-Fi", "Thriller"),
                148,
                "Desc 1",
                (short) 2010,
                "Christopher Nolan"
        ));

        testMovies.add(new Movie(
                "Interstellar",
                List.of("Sci-Fi", "Drama"),
                169,
                "Desc 2",
                (short) 2014,
                "Christopher Nolan"
        ));

        testMovies.add(new Movie(
                "Jaws",
                List.of("Thriller"),
                124,
                "Desc 3",
                (short) 1975,
                "Steven Spielberg"
        ));
    }

    @Test
    public void testCalculateBySimpleAttributeDirector() {
        List<Map.Entry<String, Integer>> result = statisticService.calculate(testMovies.iterator(), "director_name");

        assertEquals(2, result.size());

        assertEquals("Christopher Nolan", result.get(0).getKey());
        assertEquals(2, result.get(0).getValue());

        assertEquals("Steven Spielberg", result.get(1).getKey());
        assertEquals(1, result.get(1).getValue());
    }

    @Test
    public void testCalculateByListAttributeComments() {
        testMovies.get(0).setComments(List.of("Cool", "Wow"));
        testMovies.get(1).setComments(List.of("Wow"));
        testMovies.get(2).setComments(List.of("Old but gold"));

        List<Map.Entry<String, Integer>> result = statisticService.calculate(testMovies.iterator(), "comments");

        assertEquals("Wow", result.get(0).getKey());
        assertEquals(2, result.get(0).getValue());
        assertEquals(3, result.size());
    }

    @Test
    public void testCalculateByNumericAttributeYear() {
        List<Map.Entry<String, Integer>> result = statisticService.calculate(testMovies.iterator(), "release_year");
        assertEquals(3, result.size());
        assertEquals(1, result.get(0).getValue());
    }

    @Test
    public void testCalculateWithEmptyList() {
        List<Map.Entry<String, Integer>> result = statisticService.calculate(new ArrayList<Movie>().iterator(), "title");
        assertTrue(result.isEmpty());
    }

}
