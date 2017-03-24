package sean.com.gankio.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sean.com.gankio.BaseSupportFragment;
import sean.com.gankio.R;


public class AndroidFragment extends BaseSupportFragment {
    public AndroidFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AndroidFragment newInstance(String param1, String param2) {
        AndroidFragment fragment = new AndroidFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_android, container, false);
    }

}
