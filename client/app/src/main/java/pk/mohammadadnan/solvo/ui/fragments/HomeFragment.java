package pk.mohammadadnan.solvo.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import pk.mohammadadnan.solvo.R;
import pk.mohammadadnan.solvo.UIStateChangeListener;
import pk.mohammadadnan.solvo.models.Problem;
import pk.mohammadadnan.solvo.ui.adapters.ProblemsAdapter;

public class HomeFragment extends Fragment implements ProblemsAdapter.ClickListener, ProblemsAdapter.InterestListener{

    FloatingActionButton fab;
    RecyclerView recyclerView;

    private UIStateChangeListener mUIStateChangeListener;

    private ProblemsAdapter adapter;
    private ArrayList<Problem> problemArrayList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        fab = root.findViewById(R.id.fab_home);
        recyclerView = root.findViewById(R.id.recycler_home);

        fab.setOnClickListener(view -> {
            HomeFragmentDirections.HomeToAdd action= HomeFragmentDirections.homeToAdd(true);
            Navigation.findNavController(view).navigate(action);
        });

        problemArrayList.add(new Problem(
                1,
                1,
                "Problem#1",
                "qwertyuiopasdfivnevinaieovnoanrioanponrpioanonriavponrineonpaenvianov"
        ));

        problemArrayList.add(new Problem(
                2,
                2,
                "Problem#2",
                "qwertyuiopasdfivnevinaieovnoanrioanponrpioanonriavponrineonpaenvianov"
        ));

        problemArrayList.add(new Problem(
                3,
                3,
                "Problem#3",
                "qwertyuiopasdfivnevinaieovnoanrioanponrpioanonriavponrineonpaenvianov"
        ));

        problemArrayList.add(new Problem(
                4,
                4,
                "Problem#4",
                "qwertyuiopasdfivnevinaieovnoanrioanponrpioanonriavponrineonpaenvianov"
        ));

        adapter = new ProblemsAdapter(getActivity(),problemArrayList);
        adapter.setClickListener(this);
        adapter.setInterestListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mUIStateChangeListener.onTopBarStateChange(true);
        mUIStateChangeListener.onBottomNavStateChange(true);
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
}