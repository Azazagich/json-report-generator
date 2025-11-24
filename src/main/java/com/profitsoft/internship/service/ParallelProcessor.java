package com.profitsoft.internship.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Service class responsible for the parallel processing of JSON files.
 * */
public class ParallelProcessor {

    private static final Logger LOGGER = Logger.getLogger(ParallelProcessor.class.getName());

    public Map<String, Integer> process(File folder, String attributeName, int threads) {
        if (!folder.isDirectory()) {
            throw new IllegalArgumentException("File isn't a folder: " + folder.getAbsolutePath());
        }
        File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".json"));

        if (files == null || files.length == 0) return new HashMap<>();

        ExecutorService executor = Executors.newFixedThreadPool(threads);
        List<Future<Map<String, Integer>>> futures = new ArrayList<>();

        for (File file : files) {
            futures.add(executor.submit(new FileStatisticTask(file, attributeName)));
        }

        Map<String, Integer> globalStats = new HashMap<>();
        for (Future<Map<String, Integer>> future : futures) {
            try {
                Map<String, Integer> localStats = future.get();
                localStats.forEach((k, v) -> globalStats.merge(k, v, Integer::sum));
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, ex.getMessage());
            }
        }

        executor.shutdown();
        return globalStats;
    }
}
