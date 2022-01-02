package com.src.bestflightprice.companies.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ErrorLog implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String log;
    private String company;

    public ErrorLog() {
    }

    public ErrorLog(String log, String company) {
        this.log = log;
        this.company = company;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
