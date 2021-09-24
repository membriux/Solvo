package pk.mohammadadnan.solvo.ui.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import pk.mohammadadnan.solvo.R;
import pk.mohammadadnan.solvo.UIStateChangeListener;

public class AddProblemFragment extends Fragment {

    private TextView titleTxt;
    private EditText titleEdit,detailsEdit;
    private Button postBtn;

    private UIStateChangeListener mUIStateChangeListener;

    private boolean isAddProblem = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_add_problem, container, false);

        titleTxt = root.findViewById(R.id.title_txt_add);
        titleEdit = root.findViewById(R.id.title_edit);
        detailsEdit = root.findViewById(R.id.details_edit);
        postBtn = root.findViewById(R.id.post_btn);

        if (getArguments() != null) {
            AddProblemFragmentArgs args = AddProblemFragmentArgs.fromBundle(getArguments());
            isAddProblem = args.getIsAddProblem();
            if(isAddProblem){
                titleTxt.setText("Add New Problem");
            }else{
                titleTxt.setText("Add New Solution");
            }
        }

        postBtn.setOnClickListener(view -> Navigation.findNavController(view).popBackStack());

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
}