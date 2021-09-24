package pk.mohammadadnan.solvo.ui.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import pk.mohammadadnan.solvo.R;
import pk.mohammadadnan.solvo.UIStateChangeListener;
import pk.mohammadadnan.solvo.models.Problem;
import pk.mohammadadnan.solvo.ui.adapters.ProblemsAdapter;


public class ProblemFragment extends Fragment implements ProblemsAdapter.InterestListener{

    private FloatingActionButton fab;

    private TextView titleTxt,detailsTxt;
    private RecyclerView recyclerView;

    private UIStateChangeListener mUIStateChangeListener;

    private ProblemsAdapter adapter;
    private ArrayList<Problem> problemArrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_problem, container, false);

        fab = root.findViewById(R.id.fab_problem);
        titleTxt = root.findViewById(R.id.title_txt_problem);
        detailsTxt = root.findViewById(R.id.details_txt);
        recyclerView = root.findViewById(R.id.recycler_solutions);

        fab.setOnClickListener(view -> {
            ProblemFragmentDirections.ProblemToAdd action= ProblemFragmentDirections.problemToAdd(false);
            Navigation.findNavController(view).navigate(action);
        });

        if (getArguments() != null) {
            ProblemFragmentArgs args = ProblemFragmentArgs.fromBundle(getArguments());
            titleTxt.setText(args.getTitle());
            detailsTxt.setText(args.getDetails());
        }

        problemArrayList.add(new Problem(
                1,
                1,
                "Solution#1",
                "qwertyuiopasdfivnevinaieovnoanrioanponrpioanonriavponrineonpaenvianov",
                "date1"
        ));

        problemArrayList.add(new Problem(
                2,
                2,
                "Solution#2",
                "qwertyuiopasdfivnevinaieovnoanrioanponrpioanonriavponrineonpaenvianov",
                "date1"
        ));

        problemArrayList.add(new Problem(
                3,
                3,
                "Solution#3",
                "qwertyuiopasdfivnevinaieovnoanrioanponrpioanonriavponrineonpaenvianov",
                "date1"
        ));

        problemArrayList.add(new Problem(
                4,
                4,
                "Solution#4",
                "qwertyuiopasdfivnevinaieovnoanrioanponrpioanonriavponrineonpaenvianov",
                "date1"
        ));

        adapter = new ProblemsAdapter(getActivity(),problemArrayList);
        adapter.setInterestListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mUIStateChangeListener.onTopBarStateChange(false);
        mUIStateChangeListener.onBottomNavStateChange(false);
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
    public void onInterest(View view, int position) {

    }
}