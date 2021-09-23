package pk.mohammadadnan.solvo;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import pk.mohammadadnan.solvo.databinding.ActivityMainBinding;
import pk.mohammadadnan.solvo.models.Problem;
import pk.mohammadadnan.solvo.network.APIClient;
import pk.mohammadadnan.solvo.network.GetDataService;
import pk.mohammadadnan.solvo.ui.home.HomeViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private List<Problem> dataList;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(binding.navView, navController);

        /* ––––- ––––– */
        /* ––––- API Client ––––– */
        /* ––––- ––––– */
        /*Create handle for the RetrofitInstance interface*/
        GetDataService service = APIClient.getRetrofitInstance().create(GetDataService.class);

        Call<List<Problem>> call = service.getAllProblems();
        call.enqueue(new Callback<List<Problem>>() {

            @Override
            public void onResponse(Call<List<Problem>> call, Response<List<Problem>> response) {
//                progressDoalog.dismiss();
//                generateDataList(response.body());
                System.out.println("Got Problems");
                System.out.println(response.body());

            }

            @Override
            public void onFailure(Call<List<Problem>> call, Throwable t) {
//                progressDoalog.dismiss();
                Toast.makeText(MainActivity.this,
                        "Error getting list of Problems",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

}