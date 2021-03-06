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
import pk.mohammadadnan.solvo.models.Problems;
import pk.mohammadadnan.solvo.ui.adapters.ProblemsAdapter;

public class InvestathonsFragment extends Fragment implements ProblemsAdapter.ClickListener, ProblemsAdapter.InterestListener{

    private RecyclerView recyclerView;
    ConstraintLayout progressLayout,emptyLayout;

    private UIStateChangeListener mUIStateChangeListener;

    private ProblemsAdapter adapter;
    private ArrayList<Problem> investathonsArrayList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_investathons, container, false);

        recyclerView = root.findViewById(R.id.recycler_investathons);
        progressLayout = root.findViewById(R.id.progress_layout_investathons);
        emptyLayout = root.findViewById(R.id.empty_layout_investathons);

        progressLayout.setVisibility(View.GONE);

        addDummyData();

        adapter = new ProblemsAdapter(getActivity(), investathonsArrayList);
        adapter.setClickListener(this);
        adapter.setInterestListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if(investathonsArrayList.size() != 0){
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
        InvestathonsFragmentDirections.InvestathonToProblem action = InvestathonsFragmentDirections.investathonToProblem(
                investathonsArrayList.get(position).getId(),
                investathonsArrayList.get(position).getTitle(),
                investathonsArrayList.get(position).getDescription()
        );
        Navigation.findNavController(view).navigate(action);
    }

    @Override
    public void onInterest(View view, int position) {

    }

    private void addDummyData(){
        investathonsArrayList.add(new Problem(
                1,
                1,
                "Backpack with raincoat",
                "qwertyuiopasdfivnevinaieovnoanrioanponrpioanonriavponrineonpaenvianov",
                System.currentTimeMillis()
        ));

        investathonsArrayList.add(new Problem(
                2,
                2,
                "Investathon#2",
                "qwertyuiopasdfivnevinaieovnoanrioanponrpioanonriavponrineonpaenvianov",
                System.currentTimeMillis()
        ));

        investathonsArrayList.add(new Problem(
                3,
                3,
                "Investathon#3",
                "qwertyuiopasdfivnevinaieovnoanrioanponrpioanonriavponrineonpaenvianov",
                System.currentTimeMillis()
        ));

        investathonsArrayList.add(new Problem(
                4,
                4,
                "Investathon#4",
                "qwertyuiopasdfivnevinaieovnoanrioanponrpioanonriavponrineonpaenvianov",
                System.currentTimeMillis()
        ));
    }
}