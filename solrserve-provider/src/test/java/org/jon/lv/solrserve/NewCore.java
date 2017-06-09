package org.jon.lv.solrserve;

import java.io.Serializable;

/**
 * Created by 002315 on 2015/12/2.
 */
public class NewCore implements Serializable {

    private Long id;
    private String name;
    private String phone;
    private String[] email;
    private String city;
    private String address;
    private String text;


    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String[] getEmail() {
        return email;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getText() {
        return text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String... email) {
        this.email = email;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setText(String text) {
        this.text = text;
    }
}
