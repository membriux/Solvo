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
import pk.mohammadadnan.solvo.models.Problems;
import pk.mohammadadnan.solvo.models.RetroPhoto;
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
    ConstraintLayout progressLayout,emptyLayout;

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
        emptyLayout = root.findViewById(R.id.empty_layout_home);

        fab.setOnClickListener(view -> {
            HomeFragmentDirections.HomeToAdd action= HomeFragmentDirections.homeToAdd(true);
            Navigation.findNavController(view).navigate(action);
        });

        progressLayout.setVisibility(View.GONE);
        emptyLayout.setVisibility(View.VISIBLE);

        addDummyData();
        refreshRecycler();

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
        if(problemArrayList.size() != 0){
            emptyLayout.setVisibility(View.GONE);
        }else{
            emptyLayout.setVisibility(View.VISIBLE);
        }
        adapter = new ProblemsAdapter(getActivity(),problemArrayList);
        adapter.setClickListener(HomeFragment.this);
        adapter.setInterestListener(HomeFragment.this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void getAllProblemsRequest(){


        /*Create handle for the RetrofitInstance interface*/
        GetDataService service = APIClient.getRetrofitInstance().create(GetDataService.class);
        Call<List<RetroPhoto>> call1 = service.getAllPhotos();
        call1.enqueue(new Callback<List<RetroPhoto>>() {
            @Override
            public void onResponse(Call<List<RetroPhoto>> call, Response<List<RetroPhoto>> response) {
                System.out.print("RESPONSE FROM API" + response.body());
            }

            @Override
            public void onFailure(Call<List<RetroPhoto>> call, Throwable t) {
                System.out.println("Something went wrong...Please try later!");
            }
        });

        progressLayout.setVisibility(View.VISIBLE);

//        Call<Problems> call = service.getAllProblems();
//        call.enqueue(new Callback<Problems>() {
//            @Override
//            public void onResponse(@NonNull Call<Problems> call, @NonNull Response<Problems> response) {
//                progressLayout.setVisibility(View.GONE);
//
//                Log.e(TAG,"Got Response");
//                if (response.body() != null) {
//
//                    //problemArrayList = new ArrayList<>(response.body().getProblems());
//                    Log.e(TAG, "RESPONSE FROM API" + response.body());
////                    refreshRecycler();
//
//                    Log.e(TAG,"Problems Found");
//                }else{
//                    Log.e(TAG,"No Problems Found");
//                }
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<Problems> call, @NonNull Throwable t) {
//
//                progressLayout.setVisibility(View.GONE);
//                Log.e(TAG, "ERROR WITH NETWORK REQUEST:" + t.getMessage());
//                Toast.makeText(getActivity(),
//                        "Error: "+t.getMessage(),
//                        Toast.LENGTH_LONG).show();
//            }
//        });

    }

    public void addDummyData(){
        problemArrayList.add(new Problem(
                1003,
                1,
                "Problem#1",
                "qwertyuiopasdfivnevinaieovnoanrioanponrpioanonriavponrineonpaenvianov",
                System.currentTimeMillis()
        ));

        problemArrayList.add(new Problem(
                450,
                2,
                "Problem#2",
                "qwertyuiopasdfivnevinaieovnoanrioanponrpioanonriavponrineonpaenvianov",
                System.currentTimeMillis()
        ));

        problemArrayList.add(new Problem(
                345,
                3,
                "Problem#3",
                "qwertyuiopasdfivnevinaieovnoanrioanponrpioanonriavponrineonpaenvianov",
                System.currentTimeMillis()
        ));

        problemArrayList.add(new Problem(
                45,
                4,
                "Problem#4",
                "qwertyuiopasdfivnevinaieovnoanrioanponrpioanonriavponrineonpaenvianov",
                System.currentTimeMillis()
        ));
    }
}