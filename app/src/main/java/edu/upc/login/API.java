package edu.upc.login;



import java.util.List;

import edu.upc.login.Entidades.Comentario;
import edu.upc.login.Entidades.Foro;
import edu.upc.login.Entidades.Inventario;
import edu.upc.login.Entidades.LoginCredentials;
import edu.upc.login.Entidades.Partida;
import edu.upc.login.Entidades.Ranking;
import edu.upc.login.Entidades.RegisterCredentials;
import edu.upc.login.Entidades.Token;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface API {

@GET("game/ranking")
    Call <List<Ranking>> getRanking();

@GET("game/toppartidas ")
    Call<List<Partida>> getRankingPersonal(@Query("token") String token);

//Servicio que pasa un JSON con las credenciales del login y devuelve un token
//que se guardara en sharedPreferences
//Para ver sharedPreferences: device file explorer->data->data->edu.upc.login->shared_prefs
@POST("auth/login")
    Call<Token> login(@Body LoginCredentials loginCredentials);

@POST("auth/register")
    Call<Token> register(@Body RegisterCredentials registerCredentials);

@POST("game/compra")
    Call<Void> comprar (@Body Inventario inventario);

@GET("game/objetos")
    Call<List<Inventario>> inventario (@Query("token") String token);

@GET("user/comments")
    Call<List<Foro>> getComments();

@POST("user/newcomment")
    Call<Void> addComment(@Query("token") String token, @Body Comentario comentario);
}