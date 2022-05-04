package com.aquaq.scb.entities.ZohoEntity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ZohoThankYouModel {

    @JsonProperty("recordId")
    private String recordId;
    @JsonProperty("modifiedTime")
    private String modifiedTime;
    @JsonProperty("Category")
    private String category;
    @JsonProperty("ownerName")
    private String ownerName;
    @JsonProperty("Added time")
    private String added_Time;
    @JsonProperty("Would Like to Thank")
    private String would_Like_To_Thank;
    @JsonProperty("createdTime")
    private String createdTime;
    @JsonProperty("Added By")
    private String added_By;
    @JsonProperty("ownerID")
    private String ownerId;
    @JsonProperty("Client")
    private String client;
    @JsonProperty("ApprovalStatus")
    private String approvalStatus;
    @JsonProperty("Reason")
    private String reason;
}
