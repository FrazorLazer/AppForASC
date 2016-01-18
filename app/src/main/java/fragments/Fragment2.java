package fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.frazzle.appforasc.R;

public class Fragment2 extends Fragment {

    View v;
    int colour;
    //Button toMiddle;

    Fragment2Listener activityCommander;

    public interface Fragment2Listener{
        public void backToMiddle();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment2_layout, container, false);



        Bundle args = getArguments();
        String index = args.getString("act");
        colour = args.getInt("colour");

        //toMiddle = (Button) v.findViewById(R.id.backButton);
        TextView storyText = (TextView) v.findViewById(R.id.actText);
        storyText.setText(index);
        

        return v;

    }

    public void toMiddle(){
        activityCommander.backToMiddle();
    }


    @Override
    public void onStart() {
        super.onStart();
        v.setBackgroundColor(colour);
    }
}
