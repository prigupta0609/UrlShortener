package com.padlet.contract.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

// Possible errors returned in response
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Error {

    @JsonProperty("description")
    private String description;

    @JsonProperty("code")
    private String code;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
