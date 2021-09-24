package pk.mohammadadnan.solvo.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

import pk.mohammadadnan.solvo.R;
import pk.mohammadadnan.solvo.UIStateChangeListener;
import pk.mohammadadnan.solvo.models.Problem;
import pk.mohammadadnan.solvo.ui.adapters.ProblemsAdapter;

public class InterestsFragment extends Fragment implements ProblemsAdapter.ClickListener, ProblemsAdapter.InterestListener{

    private RecyclerView recyclerView;
    ConstraintLayout progressLayout,emptyLayout;

    private UIStateChangeListener mUIStateChangeListener;

    private ProblemsAdapter adapter;
    private ArrayList<Problem> interestsArrayList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_interests, container, false);

        recyclerView = root.findViewById(R.id.recycler_interests);
        progressLayout = root.findViewById(R.id.progress_layout_interests);
        emptyLayout = root.findViewById(R.id.empty_layout_interests);

        progressLayout.setVisibility(View.GONE);

        interestsArrayList.add(new Problem(
                1,
                1,
                "Interest#1",
                "qwertyuiopasdfivnevinaieovnoanrioanponrpioanonriavponrineonpaenvianov",
                System.currentTimeMillis()
        ));

        interestsArrayList.add(new Problem(
                2,
                2,
                "Interest#2",
                "qwertyuiopasdfivnevinaieovnoanrioanponrpioanonriavponrineonpaenvianov",
                System.currentTimeMillis()
        ));

        interestsArrayList.add(new Problem(
                3,
                3,
                "Interest#3",
                "qwertyuiopasdfivnevinaieovnoanrioanponrpioanonriavponrineonpaenvianov",
                System.currentTimeMillis()
        ));

        interestsArrayList.add(new Problem(
                4,
                4,
                "Interest#4",
                "qwertyuiopasdfivnevinaieovnoanrioanponrpioanonriavponrineonpaenvianov",
                System.currentTimeMillis()
        ));

        adapter = new ProblemsAdapter(getActivity(), interestsArrayList);
        adapter.setClickListener(this);
        adapter.setInterestListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if(interestsArrayList.size() != 0){
            emptyLayout.setVisibility(View.GONE);
        }else{
            emptyLayout.setVisibility(View.VISIBLE);
        }

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
        InterestsFragmentDirections.InterestToProblem action = InterestsFragmentDirections.interestToProblem(
                interestsArrayList.get(position).getId(),
                interestsArrayList.get(position).getTitle(),
                interestsArrayList.get(position).getDescription()
        );
        Navigation.findNavController(view).navigate(action);
    }

    @Override
    public void onInterest(View view, int position) {

    }
}