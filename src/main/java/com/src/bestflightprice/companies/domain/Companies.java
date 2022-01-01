package com.src.bestflightprice.companies.domain;

public enum Companies {

    LATAM_AIR_LINES("Latam Air Line", "https://www.latamairlines.com/br/pt"),
    VOE_AZUL("Voe Azul", "https://www.voeazul.com.br/");

    Companies (String name, String site) {
        this.name = name;
        this.site = site;
    }

    private String name;
    private String site;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
