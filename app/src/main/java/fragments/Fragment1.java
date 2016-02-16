package fragments;



import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.frazzle.appforasc.ExtendedApp;
import com.example.frazzle.appforasc.R;
import com.example.frazzle.appforasc.StoryActivity;

import org.w3c.dom.Text;

public class Fragment1 extends Fragment {

    Button goLeft;
    Button goRight;
    Button progressButton;
    Button exitButton;
    TextView storyNumber;
    int orient;
    String colourString;
    Fragment1Listener activityCommander;
    View v;
    TextView storyText;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment1_layout, container, false);

        goLeft = (Button) v.findViewById(R.id.goLeft);
        goRight = (Button) v.findViewById(R.id.goRight);
        progressButton = (Button) v.findViewById(R.id.progressButton);
        exitButton = (Button) v.findViewById(R.id.exitButton);



        Bundle args = getArguments();
        String index = args.getString("story");
        colourString = args.getString("colourString");
        String pathname1 = args.getString("pathname1");
        String pathname2 = args.getString("pathname2");
        String charName1 = args.getString("name1");
        String charName2 = args.getString("name2");
        String storyProgress = args.getString("progress");
        orient = args.getInt("orientation");


        if(orient == 2){
            //leftArrow.setVisibility(View.VISIBLE);
            goLeft.setBackground(new ResourcesCompat().getDrawable(getResources(), R.drawable.border_red, null));
            goRight.setBackground(new ResourcesCompat().getDrawable(getResources(), R.drawable.borders, null));
        }else{
            //rightArrow.setVisibility(View.VISIBLE);
            goLeft.setBackground(new ResourcesCompat().getDrawable(getResources(), R.drawable.borders, null));
            goRight.setBackground(new ResourcesCompat().getDrawable(getResources(), R.drawable.border_red, null));
        }



        storyNumber = (TextView) v.findViewById(R.id.storyNumber);
        storyText = (TextView) v.findViewById(R.id.storyText);
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

        setTextSize();

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
        progressButton.setVisibility(View.GONE);

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exit(v);
            }
        });

        return v;

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


}
