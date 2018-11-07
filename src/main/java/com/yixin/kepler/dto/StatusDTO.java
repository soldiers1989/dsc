package com.yixin.kepler.dto;

public class StatusDTO {

    private String status;

    private String type;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public StatusDTO(String status, String type) {
        this.status = status;
        this.type = type;
    }

    public StatusDTO() {
    }
}
