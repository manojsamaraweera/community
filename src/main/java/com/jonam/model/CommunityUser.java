package com.jonam.model;

import java.util.List;

public class CommunityUser {
    String name;
    String userName;
    List<String> memberCommunities;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getMemberCommunities() {
        return memberCommunities;
    }

    public void setMemberCommunities(List<String> memberCommunities) {
        this.memberCommunities = memberCommunities;
    }

    public List<String> getAdminCommunities() {
        return adminCommunities;
    }

    public void setAdminCommunities(List<String> adminCommunities) {
        this.adminCommunities = adminCommunities;
    }

    List<String> adminCommunities;
}
