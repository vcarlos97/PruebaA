package edu.upc.login;



import android.media.session.MediaSession;

import java.util.List;

import edu.upc.login.Entidades.Partida;
import edu.upc.login.Entidades.Ranking;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API {

@GET("game/ranking")
    Call <List<Ranking>> getRanking();

@GET("game/rankingPersonal")
    Call<List<Partida>> getRankingPersonal(@Body MediaSession.Token token);

@POST("auth/login")
    Call<LoginCredentials> login(@Body LoginCredentials loginCredentials);

@POST("auth/register")
    Call<RegisterCredentials> register(@Body RegisterCredentials registerCredentials);

}