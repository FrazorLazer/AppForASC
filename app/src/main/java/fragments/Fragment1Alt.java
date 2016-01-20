package fragments;

import android.content.Context;
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
public class Fragment1Alt extends Fragment{

    int colour;
    Fragment1AltListener activityCommander;
    View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment1_alt_layout, container, false);

        Bundle args = getArguments();
        String index = args.getString("story");
        colour = args.getInt("colour");

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            activityCommander = (Fragment1AltListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString());
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        v.setBackgroundColor(colour);
    }


    public interface Fragment1AltListener{
            public void moveLeft();
            public void moveRight();
            public void progressStory(View view);
    }

    public void goLeft(View view){
        activityCommander.moveLeft();
    }

    public void goRight(View view){
        activityCommander.moveRight();
    }



}
