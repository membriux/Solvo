package pk.mohammadadnan.solvo.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import pk.mohammadadnan.solvo.MainActivity;
import pk.mohammadadnan.solvo.R;
import pk.mohammadadnan.solvo.UIStateChangeListener;
import pk.mohammadadnan.solvo.models.Problem;
import pk.mohammadadnan.solvo.network.APIClient;
import pk.mohammadadnan.solvo.network.GetDataService;
import pk.mohammadadnan.solvo.ui.adapters.ProblemsAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements ProblemsAdapter.ClickListener, ProblemsAdapter.InterestListener{

    public static final String TAG = "HomeFragment ------->";

    FloatingActionButton fab;
    RecyclerView recyclerView;
    ConstraintLayout progressLayout;

    private UIStateChangeListener mUIStateChangeListener;

    private ProblemsAdapter adapter;
    private ArrayList<Problem> problemArrayList = new ArrayList<>();

    private GetDataService service;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        fab = root.findViewById(R.id.fab_home);
        recyclerView = root.findViewById(R.id.recycler_home);
        progressLayout = root.findViewById(R.id.progress_layout_home);

        fab.setOnClickListener(view -> {
            HomeFragmentDirections.HomeToAdd action= HomeFragmentDirections.homeToAdd(true);
            Navigation.findNavController(view).navigate(action);
        });

        refreshRecycler();

        progressLayout.setVisibility(View.GONE);

        service = APIClient.getRetrofitInstance().create(GetDataService.class);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mUIStateChangeListener.onTopBarStateChange(true);
        mUIStateChangeListener.onBottomNavStateChange(true);

        getAllProblemsRequest();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof UIStateChangeListener) {
            mUIStateChangeListener = (UIStateChangeListener) context;
        } else {
            throw new ClassCastException(context + " must implement interface MyInterface");
        }
    }

    @Override
    public void onDetach() {
        mUIStateChangeListener = null;
        super.onDetach();
    }


    @Override
    public void onClick(View view, int position) {
        HomeFragmentDirections.HomeToProblem action = HomeFragmentDirections.homeToProblem(
                problemArrayList.get(position).getId(),
                problemArrayList.get(position).getTitle(),
                problemArrayList.get(position).getDescription()
        );
        Navigation.findNavController(view).navigate(action);
    }

    @Override
    public void onInterest(View view, int position) {

    }

    private void refreshRecycler(){
        adapter = new ProblemsAdapter(getActivity(),problemArrayList);
        adapter.setClickListener(HomeFragment.this);
        adapter.setInterestListener(HomeFragment.this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void getAllProblemsRequest(){

        progressLayout.setVisibility(View.VISIBLE);

        Call<List<Problem>> call = service.getAllProblems();
        call.enqueue(new Callback<List<Problem>>() {

            @Override
            public void onResponse(@NonNull Call<List<Problem>> call, @NonNull Response<List<Problem>> response) {

                progressLayout.setVisibility(View.GONE);

                Log.e(TAG,"Got Response");
                if (response.body() != null) {

                    problemArrayList = new ArrayList<>(response.body());
                    refreshRecycler();

                    Log.e(TAG,"Problems Found");
                }else{
                    Log.e(TAG,"No Problems Found");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Problem>> call, @NonNull Throwable t) {

                progressLayout.setVisibility(View.GONE);

                Toast.makeText(getActivity(),
                        "Error: "+t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });

    }
}