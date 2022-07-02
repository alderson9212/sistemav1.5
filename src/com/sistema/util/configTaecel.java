package com.sistema.util;

public class configTaecel {

    private String url = "https://taecel.com/";
    private String basePath = "app/api/";
    private String key="b9db0c5be6a058cafb675f26a1cbbdd0";
    private String nip = "80e54e50a593872c4170739309b65097";
    public boolean bandera = false;

    public configTaecel() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public boolean isBandera() {
        return bandera;
    }

    public void setBandera(boolean bandera) {
        this.bandera = bandera;
    }

    
    


    

}
