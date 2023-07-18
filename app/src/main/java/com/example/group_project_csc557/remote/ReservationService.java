package com.example.group_project_csc557.remote;

import com.example.group_project_csc557.model.Reservation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ReservationService {

    @GET("api/reservation")
    Call<List<Reservation>> getAllReservations(@Header("api-key") String api_key);

    @GET("api/reservation/{id}")
    Call<Reservation> getReservation(@Header("api-key") String api_key, @Path("id") int id);

    /**
     * Add book by sending a single Book JSON
     * @return book object
     */
    @POST("api/reservation")
    Call<Reservation> addReservation(@Header ("api-key") String apiKey, @Body Reservation reservation);
}
