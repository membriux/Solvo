package pk.mohammadadnan.solvo.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import pk.mohammadadnan.solvo.R;
import pk.mohammadadnan.solvo.UIStateChangeListener;
import pk.mohammadadnan.solvo.databinding.FragmentInvestathonsBinding;

public class InvestathonsFragment extends Fragment {

    private UIStateChangeListener mUIStateChangeListener;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_investathons, container, false);

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
}