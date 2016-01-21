package fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.frazzle.appforasc.R;


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

        Button goLeft = (Button) v.findViewById(R.id.leftButton);
        Button goRight = (Button) v.findViewById(R.id.rightButton);
        Button exit = (Button) v.findViewById(R.id.exitButton);
        Button prog = (Button) v.findViewById(R.id.progressButton);
        TextView storyText = (TextView) v.findViewById(R.id.storyText);

        storyText.setText(index);

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
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exit(v);
            }
        });
        prog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress(v);
            }
        });


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




    public interface Fragment1AltListener{
        public void moveLeft();
        public void moveRight();
        public void exitStory(View view);
        public void progressStory(View view);
    }

    public void goLeft(View view){
        activityCommander.moveLeft();
    }

    public void goRight(View view){
        activityCommander.moveRight();
    }

    public void exit(View view){
        activityCommander.exitStory(view);
    }

    public void progress(View view){
        activityCommander.progressStory(view);
    }



    @Override
    public void onStart() {
        super.onStart();
        v.setBackgroundColor(colour);
    }

}
