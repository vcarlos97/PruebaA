package edu.upc.login;



import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface API {
/*    @GET("tracks")
    Call<List<User>> getTracks();

    @POST("tracks")
    Call<User> addTrack (@Body User user);

    @PUT("tracks")
    Call<Void> updateTrack (@Body User user);

    @DELETE("tracks/{id}")
    Call<Void> deleteTrack (@Path("id") String id);
*/
@POST("auth")
    Call<LoginCredentials> login(@Body LoginCredentials loginCredentials);

}