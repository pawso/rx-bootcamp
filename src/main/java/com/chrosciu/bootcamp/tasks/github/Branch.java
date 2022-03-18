package com.chrosciu.bootcamp.tasks.github;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Branch {
    private String name;
}
