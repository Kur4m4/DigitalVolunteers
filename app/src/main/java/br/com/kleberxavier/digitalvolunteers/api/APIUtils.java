package br.com.kleberxavier.digitalvolunteers.api;

/**
 * Created by Kai on 15/09/2017.
 */

public class APIUtils {

    public static final String BASE_URL = "http://www.mocky.io";

    public static DefaultUserAPI getDefaultUserDataAPIService() {
        return RetrofitClient.getClient(BASE_URL).create(DefaultUserAPI.class);
    }
}
