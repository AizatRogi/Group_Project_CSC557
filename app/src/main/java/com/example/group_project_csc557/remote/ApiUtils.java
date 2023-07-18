package com.example.group_project_csc557.remote; // do not copy this package name. use your existing package

import com.example.group_project_csc557.model.Reservation;

public class ApiUtils {

    // REST API server URL
    public static final String BASE_URL = "https://nacreous-reactor.000webhostapp.com/prestige/";

    // return UserService instance
    public static UserService getUserService() {
        return RetrofitClient.getClient(BASE_URL).create(UserService.class);
    }

    // return ReservationService instance
    public static ReservationService getReservationService() {
        return RetrofitClient.getClient(BASE_URL).create(ReservationService.class);
    }

}