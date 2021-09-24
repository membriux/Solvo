package pk.mohammadadnan.solvo.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

   private static Retrofit retrofit;
//   private static final String BASE_URL = "http://10.0.2.2:8000/";
   private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

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