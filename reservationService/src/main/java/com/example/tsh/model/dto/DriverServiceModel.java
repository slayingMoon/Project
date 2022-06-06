package com.example.tsh.model.dto;

import javax.persistence.Column;
import java.io.Serializable;

public class DriverServiceModel extends BaseDto {

    private String name;

    private String phone1;

    private String phone2;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }


}
