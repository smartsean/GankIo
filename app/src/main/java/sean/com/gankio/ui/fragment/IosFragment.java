package sean.com.gankio.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sean.com.gankio.BaseSupportFragment;
import sean.com.gankio.R;


public class IosFragment extends BaseSupportFragment {


    public IosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ios, container, false);
    }

}
