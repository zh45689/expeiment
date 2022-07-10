package com.zh.bootStart.bean;

public class Collections {
    private String ecid;
    private String eid;
    private String username;


    public Collections() {
    }

    public Collections(String eid, String username) {
        this.eid = eid;
        this.username = username;
    }

    public Collections(String ecid, String eid, String username) {
        this.ecid = ecid;
        this.eid = eid;
        this.username = username;
    }

    public String getEcid() {
        return ecid;
    }

    public void setEcid(String ecid) {
        this.ecid = ecid;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
