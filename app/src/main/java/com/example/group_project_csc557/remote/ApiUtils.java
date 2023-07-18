package com.example.group_project_csc557.remote; // do not copy this package name. use your existing package

public class ApiUtils {

    // REST API server URL
    public static final String BASE_URL = "https://csc557gp.000webhostapp.com/prestige/";

    // return UserService instance
    public static UserService getUserService() {
        return RetrofitClient.getClient(BASE_URL).create(UserService.class);
    }

}