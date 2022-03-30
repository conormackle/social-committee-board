package com.aquaq.scb.config.security.oauth.zoho;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserData {
    @JsonProperty("First_Name")
    private String firstName;

    @JsonProperty("Last_Name")
    private String lastName;

    @JsonProperty("Email")
    private String email;

    @JsonProperty("Display_Name")
    private String displayName;

    @JsonProperty("ZUID")
    private String zuid;

}
