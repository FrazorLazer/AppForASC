package fragments;




import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.frazzle.appforasc.ExtendedApp;
import com.example.frazzle.appforasc.R;

import java.lang.reflect.Field;

public class TutorialFragment extends Fragment {


    TutorialFragmentListener activityCommander;
    View v;
    String colourString;
    ImageView slide;
    TextView extraText;
    Button tryButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.tutorial_fragment, container, false);

        Bundle args = getArguments();
        //String text = args.getString("story");
        colourString = args.getString("colourString");
        String slideFilename = args.getString("slideFilename");
        String theText = args.getString("theText");
        boolean first = args.getBoolean("first");
        boolean last = args.getBoolean("last");
        boolean showButton = args.getBoolean("butt");



        slide = (ImageView) v.findViewById(R.id.image);
        extraText = (TextView) v.findViewById(R.id.extraText);
        ImageView left = (ImageView) v.findViewById(R.id.left);
        ImageView right = (ImageView) v.findViewById(R.id.right);
        tryButton = (Button) v.findViewById(R.id.tryIt);

        if (first){
            left.setVisibility(View.INVISIBLE);
        }
        if (last){
            right.setVisibility(View.INVISIBLE);
        }
        if (showButton) {
            tryButton.setVisibility(View.VISIBLE);
        }

        extraText.setText(theText);

        slide.setImageResource(getImageResourceId(slideFilename));

        tryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beginTheTutorial(v);
            }
        });

        //setTextSize();

        return v;

    }

    public interface TutorialFragmentListener{
        public void begin(View view);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            activityCommander = (TutorialFragmentListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString());
        }
    }


    public void beginTheTutorial(View view){
        activityCommander.begin(view);
    }

    @Override
    public void onStart() {
        super.onStart();
        setTheme();
    }


    public void setTheme() {


        int colour;

        switch (colourString) {


            case ("Red"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundRed, null);
                v.setBackgroundColor(colour);
                tryButton.setBackgroundResource(R.drawable.roundbuttonr);
                break;

            case ("Blue"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundBlue, null);
                v.setBackgroundColor(colour);
                tryButton.setBackgroundResource(R.drawable.roundbutton);
                break;

            case ("Green"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundGreen, null);
                v.setBackgroundColor(colour);
                tryButton.setBackgroundResource(R.drawable.roundbuttong);
                break;

            case ("Purple"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundPurple, null);
                v.setBackgroundColor(colour);
                tryButton.setBackgroundResource(R.drawable.roundbuttonp);
                break;


            default:
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundBlue, null);
                v.setBackgroundColor(colour);
                tryButton.setBackgroundResource(R.drawable.roundbutton);
                break;

        }
    }

    public int getImageResourceId(String stringName)
    {
        try {
            Class res = R.drawable.class;
            Field field = res.getField(stringName);
            return field.getInt(null);
        }
        catch (Exception e) {
            Log.e("MyTag", "Failure to get raw id.", e);
        }

        return 0;
    }
/*
    public void setTheme(){


        int colour;

        switch(colourString) {


            case ("Red"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundRed, null);
                v.setBackgroundColor(colour);

                if(orient == 2){
                    //leftArrow.setVisibility(View.VISIBLE);
                    goLeft.setBackgroundResource(R.drawable.greenbord_red);
                    goRight.setBackgroundResource(R.drawable.border_red);
                }else{
                    //rightArrow.setVisibility(View.VISIBLE);
                    goLeft.setBackgroundResource(R.drawable.border_red);
                    goRight.setBackgroundResource(R.drawable.greenbord_red);
                }

                goLeft.setTextColor(new ResourcesCompat().getColor(getResources(), R.color.LightestRed, null));
                goRight.setTextColor(new ResourcesCompat().getColor(getResources(), R.color.LightestRed, null));
                exitButton.setBackgroundResource(R.drawable.roundbuttonr);
                storyNumber.setTextColor(new ResourcesCompat().getColor(getResources(), R.color.LightRed, null));
                break;

            case ("Blue"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundBlue, null);
                v.setBackgroundColor(colour);

                if(orient == 2){
                    //leftArrow.setVisibility(View.VISIBLE);
                    goLeft.setBackgroundResource(R.drawable.greenbord_blue);
                    goRight.setBackgroundResource(R.drawable.borders_blue);
                }else{
                    //rightArrow.setVisibility(View.VISIBLE);
                    goLeft.setBackgroundResource(R.drawable.borders_blue);
                    goRight.setBackgroundResource(R.drawable.greenbord_blue);
                }

                goLeft.setTextColor(new ResourcesCompat().getColor(getResources(), R.color.LightestBlue, null));
                goRight.setTextColor(new ResourcesCompat().getColor(getResources(), R.color.LightestBlue, null));
                exitButton.setBackgroundResource(R.drawable.roundbutton);
                storyNumber.setTextColor(new ResourcesCompat().getColor(getResources(), R.color.LightBlue, null));

                break;

            case ("Green"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundGreen, null);
                v.setBackgroundColor(colour);

                if(orient == 2){
                    //leftArrow.setVisibility(View.VISIBLE);
                    goLeft.setBackgroundResource(R.drawable.redbord_green);
                    goRight.setBackgroundResource(R.drawable.borders_green);
                }else{
                    //rightArrow.setVisibility(View.VISIBLE);
                    goLeft.setBackgroundResource(R.drawable.borders_green);
                    goRight.setBackgroundResource(R.drawable.redbord_green);
                }

                goLeft.setTextColor(new ResourcesCompat().getColor(getResources(), R.color.LightestGreen, null));
                goRight.setTextColor(new ResourcesCompat().getColor(getResources(), R.color.LightestGreen, null));
                exitButton.setBackgroundResource(R.drawable.roundbuttong);
                storyNumber.setTextColor(new ResourcesCompat().getColor(getResources(), R.color.LightGreen, null));
                break;

            case ("Purple"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundPurple, null);
                v.setBackgroundColor(colour);

                if(orient == 2){
                    //leftArrow.setVisibility(View.VISIBLE);
                    goLeft.setBackgroundResource(R.drawable.greenbord_purple);
                    goRight.setBackgroundResource(R.drawable.borders_purple);
                }else{
                    //rightArrow.setVisibility(View.VISIBLE);
                    goLeft.setBackgroundResource(R.drawable.borders_purple);
                    goRight.setBackgroundResource(R.drawable.greenbord_purple);
                }

                goLeft.setTextColor(new ResourcesCompat().getColor(getResources(), R.color.LightestPurple, null));
                goRight.setTextColor(new ResourcesCompat().getColor(getResources(), R.color.LightestPurple, null));
                exitButton.setBackgroundResource(R.drawable.roundbuttonp);
                storyNumber.setTextColor(new ResourcesCompat().getColor(getResources(), R.color.LightPurple, null));

                break;


            default:
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundBlue, null);
                v.setBackgroundColor(colour);

                if(orient == 2){
                    //leftArrow.setVisibility(View.VISIBLE);
                    goLeft.setBackgroundResource(R.drawable.greenbord_blue);
                    goRight.setBackgroundResource(R.drawable.borders_blue);
                }else{
                    //rightArrow.setVisibility(View.VISIBLE);
                    goLeft.setBackgroundResource(R.drawable.borders_blue);
                    goRight.setBackgroundResource(R.drawable.greenbord_blue);
                }

                goLeft.setTextColor(new ResourcesCompat().getColor(getResources(), R.color.LightestBlue, null));
                goRight.setTextColor(new ResourcesCompat().getColor(getResources(), R.color.LightestBlue, null));
                exitButton.setBackgroundResource(R.drawable.roundbutton);
                storyNumber.setTextColor(new ResourcesCompat().getColor(getResources(), R.color.LightBlue, null));
                break;

        }


    }

    public interface Fragment1Listener{
        public void moveLeft();
        public void moveRight();
        public void progressStory(View view);
        public void exitStory(View view);
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

    public void exit(View view){
        activityCommander.exitStory(view);
    }


    public void setTextSize(){
        SharedPreferences sharedPref = getContext().getSharedPreferences("colourInfo", Context.MODE_PRIVATE);
        String textSize = sharedPref.getString("textSize", "");

        switch(textSize){
            case ("Small"):
                storyText.setTextSize(20);
                break;

            case ("Medium"):

                storyText.setTextSize(26);
                break;

            case ("Large"):
                storyText.setTextSize(32);
                break;


            default:
                storyText.setTextSize(26);
                break;
        }
    }
*/

}
