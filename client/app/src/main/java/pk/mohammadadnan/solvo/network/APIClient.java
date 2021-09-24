package pk.mohammadadnan.solvo.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by praka on 12/24/2017.
 */

public class APIClient {

   private static Retrofit retrofit;
   private static final String BASE_URL = "http://192.168.14.1:8088/data.json/";

   public static Retrofit getRetrofitInstance() {
      if (retrofit == null) {
         retrofit = new retrofit2.Retrofit.Builder()
                 .baseUrl(BASE_URL)
                 .addConverterFactory(GsonConverterFactory.create())
                 .build();
      }
      return retrofit;
   }

}