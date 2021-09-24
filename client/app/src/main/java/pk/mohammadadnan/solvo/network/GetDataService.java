package pk.mohammadadnan.solvo.network;

import pk.mohammadadnan.solvo.models.Post;
import java.util.List;

import pk.mohammadadnan.solvo.models.Problem;
import retrofit2.Call;
import retrofit2.http.GET;


public interface GetDataService {
    @GET("/photos")
    Call<List<Post>> getAllPhotos();

    @GET("/problems")
    Call<List<Problem>> getAllProblems();

}
