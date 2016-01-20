package fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.frazzle.appforasc.R;

/**
 * Created by Frazzle on 18/01/2016.
 */
public class Fragment2Alt extends Fragment{

    int colour;

    View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment2_alt_layout, container, false);

        Bundle args = getArguments();
        String index = args.getString("story");
        colour = args.getInt("colour");

        return v;
    }



}



