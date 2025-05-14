package com.taskvista.taskvista.tenant;

public class TenantContext {  //ThreadLocal

    private static final ThreadLocal<String> currentTenant = new ThreadLocal<>();

    public static void setTenantId(String tenantId){
        currentTenant.set(tenantId);
    }

    public static String getTenantId(){
        return currentTenant.get();
    }

    public static void clear(){
        currentTenant.remove();
    }
}
