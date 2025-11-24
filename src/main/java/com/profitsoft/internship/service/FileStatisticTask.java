package com.profitsoft.internship.service;

import com.profitsoft.internship.domain.Movie;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

/**
 * Class responsible for processing a single specific file in a separate thread.
 * */
public class FileStatisticTask implements Callable<Map<String, Integer>> {

    private final File file;
    private final String attributeName;

    public FileStatisticTask(File file, String attributeName) {
        this.file = file;
        this.attributeName = attributeName;
    }

    @Override
    public Map<String, Integer> call() {
        MovieParser parser = new MovieParser();
        StatisticService service = new StatisticService();
        Iterator<Movie> iterator = parser.parseJsonFile(file);
        List<Map.Entry<String, Integer>> list = service.calculate(iterator, attributeName);

        return list.stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
