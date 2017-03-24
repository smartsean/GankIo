package sean.com.gankio.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sean.com.gankio.BaseSupportFragment;
import sean.com.gankio.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExpansionFragment extends BaseSupportFragment {


    public ExpansionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_expansion, container, false);
    }

}
