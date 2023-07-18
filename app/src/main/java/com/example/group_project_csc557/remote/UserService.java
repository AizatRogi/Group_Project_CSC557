package com.example.group_project_csc557.remote; // do not copy this package name. use your existing package

import com.example.group_project_csc557.model.User; // do not copy this import. Use wizard to import the User class

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserService {

    @FormUrlEncoded
    @POST("api/users/login")
    Call<User> login(@Field("username") String username, @Field("password") String password);

}