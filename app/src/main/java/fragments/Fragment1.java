package fragments;



import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.frazzle.appforasc.ExtendedApp;
import com.example.frazzle.appforasc.R;
import com.example.frazzle.appforasc.StoryActivity;

public class Fragment1 extends Fragment {

    Button goLeft;
    Button goRight;
    Button progressButton;
    int colour;
    Fragment1Listener activityCommander;
    View v;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment1_layout, container, false);

        goLeft = (Button) v.findViewById(R.id.goLeft);
        goRight = (Button) v.findViewById(R.id.goRight);
        progressButton = (Button) v.findViewById(R.id.progressButton);

        Bundle args = getArguments();
        String index = args.getString("story");
        colour = args.getInt("colour");
        //getView().setBackgroundColor(colour);
        //RelativeLayout layout = (RelativeLayout) getView().findViewById(R.id.frament1);
        // layout.setBackgroundColor(colour);
        TextView storyText = (TextView) v.findViewById(R.id.storyText);
        storyText.setText(index);

        //v.setBackgroundColor(colour);
        goLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goLeft(v);
            }
        });

        goRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goRight(v);
            }
        });

        progressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress(v);
            }
        });


        return v;

    }


    @Override
    public void onStart() {
        super.onStart();
        v.setBackgroundColor(colour);
    }

    public interface Fragment1Listener{
        public void moveLeft();
        public void moveRight();
        public void progressStory(View view);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            activityCommander = (Fragment1Listener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString());
        }
    }

    public void goLeft(View view){
        activityCommander.moveLeft();
    }

    public void goRight(View view){
        activityCommander.moveRight();
    }

    public void progress(View view){
        activityCommander.progressStory(view);
    }


}
