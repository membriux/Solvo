package pk.mohammadadnan.solvo;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.novoda.merlin.Connectable;
import com.novoda.merlin.Disconnectable;
import com.novoda.merlin.Merlin;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import pk.mohammadadnan.solvo.models.Problems;
import pk.mohammadadnan.solvo.models.RetroPhoto;
import pk.mohammadadnan.solvo.network.APIClient;
import pk.mohammadadnan.solvo.network.GetDataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements UIStateChangeListener {

    public static final String TAG = "";
    public static BottomNavigationView navView;
    private ConstraintLayout searchTopBar,returnTopBar;
    private ImageButton returnBtn;

    private boolean isJustCreated = true;

    protected Merlin merlin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initializeViews();

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(navView, navController);

        //Library for checking network state
        merlin = new Merlin
                .Builder()
                .withConnectableCallbacks()
                .withDisconnectableCallbacks()
                .build(getApplicationContext());
        merlin.registerConnectable(new Connectable() {
            @Override
            public void onConnect() {
                if(!isJustCreated){
                    Snackbar snackbar = Snackbar.make(
                            findViewById(android.R.id.content),
                            "You're connected again!",
                            Snackbar.LENGTH_SHORT
                    );
                    snackbar.setAnchorView(navView);
                    snackbar.show();
                }else{
                    isJustCreated = false;
                }
            }
        });
        merlin.registerDisconnectable(new Disconnectable() {
            @Override
            public void onDisconnect() {
                Snackbar snackbar = Snackbar.make(
                        findViewById(android.R.id.content),
                        "Internet connection lost...",
                        Snackbar.LENGTH_LONG
                );
                snackbar.setAnchorView(navView);
                snackbar.show();
            }
        });




    }

    @Override
    protected void onResume() {
        super.onResume();
        merlin.bind();
    }

    @Override
    protected void onPause() {
        merlin.unbind();
        super.onPause();
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
        returnBtn = findViewById(R.id.return_img);

        returnBtn.setOnClickListener(view -> {
            Navigation.findNavController(this,R.id.nav_host_fragment_activity_main).popBackStack();
        });
    }

}