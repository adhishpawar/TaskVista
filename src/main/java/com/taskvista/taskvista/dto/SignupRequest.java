package com.taskvista.taskvista.dto;

import com.taskvista.taskvista.Enum.Role;

public class SignupRequest {
    private String email;
    private String password;
    private Role role;        // Enum: OWNER, MEMBER, VIEWER
    private String tenantId;  // Organization or team ID

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}
