package funix.prm.prm391x_shopmovie_fx04382.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<String> mName = new MutableLiveData<>();
    private MutableLiveData<String> mEmail = new MutableLiveData<>();
    private MutableLiveData<String> mPicURL = new MutableLiveData<>();

    public DashboardViewModel() {
        if(mName == null) {
        mName.setValue("This is FB Name");
        }

        if(mEmail == null) {
            mEmail.setValue("This is FB Email");
        }

        if(mPicURL == null) {
            mPicURL.setValue("This is FB PicURL");
        }
    }

    public LiveData<String> getName() {
        return mName;
    }

    public LiveData<String> getEmail() {
        return mEmail;
    }

    public LiveData<String> getPicURL() {
        return mPicURL;
    }

    public void setName(String s) {
        mName.setValue(s);
    }

    public void setEmail(String s) {
        mEmail.setValue(s);
    }

    public void setPicURL(String s) {
        mPicURL.setValue(s);
    }

}