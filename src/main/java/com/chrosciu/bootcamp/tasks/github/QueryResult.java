package com.chrosciu.bootcamp.tasks.github;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class QueryResult {
    private List<Repository> items;
}
