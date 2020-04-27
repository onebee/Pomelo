package one.diao.com.a12_scalable.ui.mainactivity2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import one.diao.com.a12_scalable.R;

public class MainActivity2Fragment extends Fragment {

    private MainActivity2ViewModel mViewModel;
    private ViewModelProvider.Factory mfa = new ViewModelProvider.Factory() {
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return null;
        }
    };

    public static MainActivity2Fragment newInstance() {
        return new MainActivity2Fragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_activity2_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainActivity2ViewModel.class);


        mViewModel = ViewModelProviders.of(this,mfa).get(MainActivity2ViewModel.class);
        // TODO: Use the ViewModel
    }

}
