package com.profitsoft.internship;

import com.profitsoft.internship.service.ParallelProcessor;
import com.profitsoft.internship.service.ReportWriter;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public class App {

    private static final String OUTPUT_FOLDER = "statistics";
    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {

        final String pathToFile = args[0];
        final String attribute = args[1];

        File dataDir = new File(pathToFile);

        ParallelProcessor processor = new ParallelProcessor();
        processor.process(new File(pathToFile), attribute, 1);

        int[] threads = {1, 2, 4, 8};
        Map<String, Integer> result = null;

        LOGGER.log(Level.INFO,"Performance testing:");
        for (int t : threads) {
            long start = System.currentTimeMillis();
            result = processor.process(new File(pathToFile), attribute, t);
            long end = System.currentTimeMillis();
            LOGGER.log(Level.INFO,"Threads: " + t + " | Time: " + (end - start) + " ms");
        }

        if (result != null && !result.isEmpty()) {
            ReportWriter writer = new ReportWriter(OUTPUT_FOLDER);
            List<Map.Entry<String, Integer>> sortedStats = result.entrySet().stream()
                    .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                    .collect(Collectors.toList());
            writer.writeReport(sortedStats, attribute);
        }
    }
}