package pk.mohammadadnan.solvo;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import pk.mohammadadnan.solvo.models.Problem;
import pk.mohammadadnan.solvo.network.APIClient;
import pk.mohammadadnan.solvo.network.GetDataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements UIStateChangeListener {

    public static BottomNavigationView navView;
    private ConstraintLayout searchTopBar,returnTopBar;

    private List<Problem> dataList;
    private Context context;

    private GetDataService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initializeViews();

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(navView, navController);

        /* ––––- ––––– */
        /* ––––- API Client ––––– */
        /* ––––- ––––– */
        /*Create handle for the RetrofitInstance interface*/
        service = APIClient.getRetrofitInstance().create(GetDataService.class);
        getAllProblemsRequest();

    }

    @Override
    public void onBottomNavStateChange(boolean isVisible) {
        if(isVisible){
            navView.setVisibility(View.VISIBLE);
        }else{
            navView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onTopBarStateChange(boolean isSearchTopBar) {
        if(isSearchTopBar){
            searchTopBar.setVisibility(View.VISIBLE);
            returnTopBar.setVisibility(View.GONE);
        }else{
            searchTopBar.setVisibility(View.GONE);
            returnTopBar.setVisibility(View.VISIBLE);
        }
    }

    private void initializeViews(){
        navView = findViewById(R.id.nav_view);
        searchTopBar = findViewById(R.id.search_topbar);
        returnTopBar = findViewById(R.id.return_topbar);
    }

    private void getAllProblemsRequest(){

        Call<List<Problem>> call = service.getAllProblems();
        call.enqueue(new Callback<List<Problem>>() {

            @Override
            public void onResponse(Call<List<Problem>> call, Response<List<Problem>> response) {
/*
                progressDialog.dismiss();
                generateDataList(response.body());
*/
                System.out.println("Got Problems");
                System.out.println(response.body());

            }

            @Override
            public void onFailure(Call<List<Problem>> call, Throwable t) {
//                progressDialog.dismiss();
                Toast.makeText(MainActivity.this,
                        "Error getting list of Problems",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

}