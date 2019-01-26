package com.mainserver.career.center.boot.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JobAnnouncement {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;
    private String company;
    private String title;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}