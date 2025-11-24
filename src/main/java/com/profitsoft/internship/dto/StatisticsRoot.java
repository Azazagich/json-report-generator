package com.profitsoft.internship.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

/**
 * This class represents the entire XML document structure.
 * */
@JacksonXmlRootElement(localName = "statistics")
public class StatisticsRoot {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "item")
    private final List<StatisticItem> items;

    public StatisticsRoot(List<StatisticItem> items) {
        this.items = items;
    }

    public List<StatisticItem> getItems() { return items; }

}
