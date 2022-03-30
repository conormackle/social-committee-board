package com.aquaq.scb.config.security.oauth.github;

import lombok.Data;

@Data
public class UserData {
    private String first;
    private String id;
    private String nodeId;
    private String avatarUrl;
    private String gravatarId;
    private String url;
    private String htmlUrl;
    private String followersUrl;
    private String followingUrl;
    private String gistsUrl;
    private String starredUrl;
    private String subscriptionsUrl;
    private String organizationsUrl;
    private String reposUrl;
    private String eventsUrl;
    private String receivedEventsUrl;
    private String type;
    private String siteAdmin;
    private String name;
    private String company;
    private String blog;
    private String location;
    private String email;
    private String hireable;
    private String bio;
    private String twitterUsername;
    private String publicRepos;
    private String publicGists;
    private String followers;
    private String following;
    private String createdAt;
    private String updatedAt;
    private String privateGists;
    private String totalPrivateRepos;
    private String ownedPrivateRepos;
    private String diskUsage;
    private String collaborators;
    private String twoFactorAuthentication;
}
