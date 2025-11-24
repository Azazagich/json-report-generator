package com.profitsoft.internship.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.profitsoft.internship.domain.Movie;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Service class which responsible for parsing JSON files into Movie entities.
 * */
public class MovieParser {

    private static final ObjectMapper MAPPER;
    private static final Logger LOGGER = Logger.getLogger(MovieParser.class.getName());


    static {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
        MAPPER = mapper;
    }

    public Iterator<Movie> parseJsonFile(File file){
        try {
            JsonParser parser = MAPPER.createParser(file);
            JsonToken token = parser.nextToken();
            if (token == JsonToken.START_ARRAY) {
                parser.nextToken();
            }
            return MAPPER.readValues(parser, Movie.class);

        } catch (IOException ex){
            LOGGER.log(Level.SEVERE, ex.getMessage());
            return Collections.emptyIterator();
        }
    }
}
