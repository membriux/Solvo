package pk.mohammadadnan.solvo.ui;

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
import pk.mohammadadnan.solvo.databinding.FragmentInvestathonsBinding;

public class InvestathonsFragment extends Fragment {

    private FragmentInvestathonsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_investathons, container, false);

        return root;
    }
}