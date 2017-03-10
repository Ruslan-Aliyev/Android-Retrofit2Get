package com.test.retrofit2get.Model;

import com.google.gson.annotations.Expose;

/**
 * Created by Victorious on 19/09/2016.
 */
public class Model {

    @Expose
    private String subject;

    @Expose
    private String info;

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public String getSubject() {
        return subject;
    }

}
