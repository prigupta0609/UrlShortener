package com.padlet.contract.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

// Possible errors returned in response
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Error implements Serializable {

    @JsonProperty("description")
    private String description;

    @JsonProperty("code")
    private String code;

    public Error(String description, String code) {
        this.description = description;
        this.code = code;
    }
}
