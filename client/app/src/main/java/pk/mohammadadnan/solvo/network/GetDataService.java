package pk.mohammadadnan.solvo.network;

import pk.mohammadadnan.solvo.models.Post;
import java.util.List;

import pk.mohammadadnan.solvo.models.Problem;
import pk.mohammadadnan.solvo.models.User;
import retrofit2.Call;
import retrofit2.http.GET;


public interface GetDataService {

    @GET("/problems")
    Call<List<Problem>> getAllProblems();

    @GET("/user/user1")
    Call<User> getUser();

}
