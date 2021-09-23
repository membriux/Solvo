package pk.mohammadadnan.solvo.ui.investathons;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class InvestathonsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public InvestathonsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}