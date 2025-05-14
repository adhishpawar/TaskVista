package com.taskvista.taskvista.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Tenant {

    @Id
    private String id;
    private String name;
    private String adminEmail;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }
}
