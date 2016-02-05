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
    Button goLeft;
    Button goRight;
    Button exitButton;
    TextView storyNumber;
    String colourString;
    int orient;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment1_alt_layout, container, false);

        Bundle args = getArguments();
        String index = args.getString("story");
        colourString = args.getString("colourString");
        String pathname1 = args.getString("pathname1");
        String pathname2 = args.getString("pathname2");
        String charName1 = args.getString("name1");
        String charName2 = args.getString("name2");
        String storyProgress = args.getString("progress");
        orient = args.getInt("orientation");

        goLeft = (Button) v.findViewById(R.id.leftButton);
        goRight = (Button) v.findViewById(R.id.rightButton);
        exitButton = (Button) v.findViewById(R.id.exitButton);
        Button prog = (Button) v.findViewById(R.id.progressButton);
        TextView storyText = (TextView) v.findViewById(R.id.storyText);
        storyNumber = (TextView) v.findViewById(R.id.storyNumber);

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
        exitButton.setOnClickListener(new View.OnClickListener() {
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
        //v.setBackgroundColor(colour);
        setTheme();
    }


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

}
