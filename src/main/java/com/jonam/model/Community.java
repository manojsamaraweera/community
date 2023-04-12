package com.jonam.model;

import io.quarkus.mongodb.panache.PanacheMongoEntity;

import java.util.List;

public class Community extends PanacheMongoEntity {
    private String name;
    private String description;
    private String createdBy;
    private List<CommunityUser> members;
    private List<CommunityUser> blackList;
    private List<CommunityUser> adminUsers;
    private CommunityUser createdByUser;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public List<CommunityUser> getMembers() {
        return members;
    }

    public void setMembers(List<CommunityUser> members) {
        this.members = members;
    }

    public List<CommunityUser> getBlackList() {
        return blackList;
    }

    public void setBlackList(List<CommunityUser> blackList) {
        this.blackList = blackList;
    }

    public List<CommunityUser> getAdminUsers() {
        return adminUsers;
    }

    public void setAdminUsers(List<CommunityUser> adminUsers) {
        this.adminUsers = adminUsers;
    }

    public CommunityUser getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(CommunityUser createdByUser) {
        this.createdByUser = createdByUser;
    }

    public Community(String name, String description, String createdBy) {
        this.name = name;
        this.description = description;
        this.createdBy = createdBy;
    }

    public Community() {
    }
}
