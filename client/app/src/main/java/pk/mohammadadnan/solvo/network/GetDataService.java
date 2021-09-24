package pk.mohammadadnan.solvo.network;

import java.util.List;

import pk.mohammadadnan.solvo.models.Problems;
import pk.mohammadadnan.solvo.models.User;
import retrofit2.Call;
import retrofit2.http.GET;


public interface GetDataService {

    @GET("problems")
    Call<Problems> getAllProblems();

    @GET("user/user1")
    Call<User> getUser();

}
