package projecten3_h6.evaandroid.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jensleirens on 18/07/2017.
 */

public class Config {
    public static final String BASE_URL = "http://eva-webapplication.herokuapp.com/";
    private static Retrofit retrofit = null;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit= new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            return retrofit;
        } else {
            return retrofit;
        }
    }
}
