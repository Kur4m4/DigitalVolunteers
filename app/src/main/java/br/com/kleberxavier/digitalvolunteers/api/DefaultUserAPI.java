package br.com.kleberxavier.digitalvolunteers.api;

import br.com.kleberxavier.digitalvolunteers.model.DefaultUser;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Kai on 15/09/2017.
 */

public interface DefaultUserAPI {

    @GET("v2/58b9b1740f0000b614f09d2f")
    Call<DefaultUser> getDefaultUser();
}
