package com.profitsoft.internship.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * Represent a single row in the statistics report.
 * */
public class StatisticItem {

    @JacksonXmlProperty(localName = "value")
    private final String value;

    @JacksonXmlProperty(localName = "count")
    private final int count;

    public StatisticItem(String value, int count){
        this.value = value;
        this.count = count;
    }

    public String getValue(){
        return value;
    }

    public int getCount() {
        return count;
    }
}
