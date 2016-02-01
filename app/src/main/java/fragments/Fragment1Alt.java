package fragments;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
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
        String pathname1 = args.getString("pathname1");
        String pathname2 = args.getString("pathname2");
        String charName1 = args.getString("name1");
        String charName2 = args.getString("name2");
        String storyProgress = args.getString("progress");
        int orient = args.getInt("orientation");

        Button goLeft = (Button) v.findViewById(R.id.leftButton);
        Button goRight = (Button) v.findViewById(R.id.rightButton);
        Button exit = (Button) v.findViewById(R.id.exitButton);
        Button prog = (Button) v.findViewById(R.id.progressButton);
        TextView storyText = (TextView) v.findViewById(R.id.storyText);
        TextView storyNumber = (TextView) v.findViewById(R.id.storyNumber);

        storyText.setText(index);
        storyNumber.setText(storyProgress);

        Drawable song1 = BitmapDrawable.createFromPath(pathname1);
        Drawable song2 = BitmapDrawable.createFromPath(pathname2);
        Drawable placeholder = new ResourcesCompat().getDrawable(getResources(), R.drawable.placeholder_profile_photo, null);
        Drawable arrowL = new ResourcesCompat().getDrawable(getResources(), R.drawable.arrowlleft, null);
        Drawable arrowR = new ResourcesCompat().getDrawable(getResources(), R.drawable.arrowrright, null);

        arrowL.setBounds(0,0,30,50);
        arrowR.setBounds(0,0,30,50);

        //Setting Left Button
        if (song1 != null) {
            song1.setBounds(0, 0, 160, 160);
            goLeft.setCompoundDrawables(arrowL, null, song1, null);
        }else{
            placeholder.setBounds(0, 0, 160, 160);
            goLeft.setCompoundDrawables(arrowL, null, placeholder, null);
        }
        goLeft.setText(charName1);

        //Setting Right Button
        if (song2 != null) {
            song2.setBounds(0, 0, 160, 160);
            goRight.setCompoundDrawables(song2, null, arrowR, null);
        }else{
            placeholder.setBounds(0, 0, 160, 160);
            goRight.setCompoundDrawables(placeholder , null, arrowR, null);
        }
        goRight.setText(charName2);

        if(orient == 2){
            //leftArrow.setVisibility(View.VISIBLE);
            goLeft.setBackground(new ResourcesCompat().getDrawable(getResources(), R.drawable.border_red, null));
            goRight.setBackground(new ResourcesCompat().getDrawable(getResources(), R.drawable.borders, null));
        }else{
            //rightArrow.setVisibility(View.VISIBLE);
            goLeft.setBackground(new ResourcesCompat().getDrawable(getResources(), R.drawable.borders, null));
            goRight.setBackground(new ResourcesCompat().getDrawable(getResources(), R.drawable.border_red, null));
        }


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
