package com.spdb.controller;

import javax.servlet.ServletRequest;

public class BaseController {
    private ServletRequest request;
    private String useName;
    private String id;

    public String getUseName() {
        return useName;
    }

    public void setUseName(String useName) {
        this.useName = useName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
