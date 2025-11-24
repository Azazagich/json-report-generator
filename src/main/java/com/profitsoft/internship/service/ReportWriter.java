package com.profitsoft.internship.service;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.profitsoft.internship.dto.StatisticItem;
import com.profitsoft.internship.dto.StatisticsRoot;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Service class responsible for generating the XML file report.
 * */
public class ReportWriter {

    private static final Logger LOGGER = Logger.getLogger(ReportWriter.class.getName());
    private final XmlMapper xmlMapper;
    private final File outputDir;

    public ReportWriter(String outputFolderPath) {
        this.xmlMapper = new XmlMapper();
        this.xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        this.outputDir = new File(outputFolderPath);
    }

    public void writeReport(List<Map.Entry<String, Integer>> statistics, String attributeName) {
        if (!outputDir.exists()) outputDir.mkdirs();

        File outputFile = new File(outputDir, "statistics_by_" + attributeName + ".xml");
        List<StatisticItem> items = statistics.stream()
                .map(e -> new StatisticItem(e.getKey(), e.getValue()))
                .collect(Collectors.toList());

        try {
            xmlMapper.writeValue(outputFile, new StatisticsRoot(items));
            LOGGER.log(Level.INFO, "Report saved: " + outputFile.getAbsolutePath());
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage());
        }
    }
}
