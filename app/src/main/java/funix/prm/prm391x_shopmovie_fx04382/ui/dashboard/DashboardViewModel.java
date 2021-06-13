package funix.prm.prm391x_shopmovie_fx04382.ui.dashboard;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<String> mName = new MutableLiveData<>();
    private MutableLiveData<String> mEmail = new MutableLiveData<>();
    private MutableLiveData<String> mId = new MutableLiveData<>();

    public DashboardViewModel() {
        if(mName == null) {
        mName.setValue("This is FB Name");
        }

        if(mEmail == null) {
            mEmail.setValue("This is FB Email");
        }

        if(mId == null) {
            mId.setValue("This is FB Id");
        }
    }

    public LiveData<String> getName() {
        return mName;
    }

    public LiveData<String> getEmail() {
        return mEmail;
    }

    public LiveData<String> getId() {
        return mId;
    }

    public void setName(String s) {
        Log.d("vm", s);
        mName.setValue(s);
    }

    public void setEmail(String s) {
        Log.d("vm", s);
        mEmail.setValue(s);
    }

    public void setId(String s) {
        Log.d("vm", s);
        mId.setValue(s);
    }

}