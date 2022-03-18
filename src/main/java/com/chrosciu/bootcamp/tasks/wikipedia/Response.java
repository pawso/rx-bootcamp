package com.chrosciu.bootcamp.tasks.wikipedia;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Response {
    private Query query;
}