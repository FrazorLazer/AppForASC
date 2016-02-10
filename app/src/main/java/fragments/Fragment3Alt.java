package fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.frazzle.appforasc.R;

import junit.framework.TestCase;

/**
 * Created by Frazzle on 18/01/2016.
 */
public class Fragment3Alt extends Fragment{

    String colourString;
    Button hints;
    TextView hintText;
    ImageView ima;
    TextView actText;
    Button progress;
    View v;
    Fragment3AltListener activityCommander;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            activityCommander = (Fragment3AltListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString());
        }
    }

    public interface Fragment3AltListener{
        public void backToMiddle();
        public void progressStory(View view);
    }


    public void toMiddle(View view){
        activityCommander.backToMiddle();
    }


    public void progress(View view){
        activityCommander.progressStory(view);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment3_alt_layout, container, false);

        Bundle args = getArguments();
        String index = args.getString("guess");
        colourString = args.getString("colourString");
        String hintPassage = args.getString("hint");
        String orient = args.getString("orientation");
        ima = (ImageView) v.findViewById(R.id.backButton2);
        moveButton(orient);

        actText = (TextView) v.findViewById(R.id.actText);
        actText.setText(index);
        hints = (Button) v.findViewById(R.id.hints);
        hintText = (TextView) v.findViewById(R.id.hintText);
        hintText.setText(hintPassage);
        hintText.setVisibility(View.INVISIBLE);
        progress = (Button) v.findViewById(R.id.progressButtonAlt);

        progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress(v);
            }
        });

        hints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHint(v);
            }
        });

        ima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toMiddle(v);
            }
        });

        setTextSize();
        return v;
    }

    private void moveButton(String orient) {
        ViewGroup.LayoutParams vg_lp = ima.getLayoutParams();
        RelativeLayout.LayoutParams rl_lp = new RelativeLayout.LayoutParams(vg_lp);

        if (orient.equals("left")) {
            rl_lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            rl_lp.addRule(RelativeLayout.CENTER_VERTICAL);
            ima.setRotation(180);

        } else {
            rl_lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            rl_lp.addRule(RelativeLayout.CENTER_VERTICAL);
        }

        ima.setLayoutParams(rl_lp);
    }


    @Override
    public void onStart() {
        super.onStart();
        //v.setBackgroundColor(colour);
        setTheme();
    }

    public void showHint(View view){
        hints.setVisibility(View.INVISIBLE);
        hintText.setVisibility(View.VISIBLE);
    }


    public void setTheme() {

        int colour;

        switch (colourString) {


            case ("Red"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundRed, null);
                v.setBackgroundColor(colour);

                progress.setBackgroundResource(R.drawable.greenbord_red);
                hints.setBackgroundResource(R.drawable.roundbuttonr);
                hintText.setBackgroundResource(R.drawable.border_red);
                ima.setBackgroundResource(R.drawable.border_red);
                break;

            case ("Blue"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundBlue, null);
                v.setBackgroundColor(colour);

                progress.setBackgroundResource(R.drawable.greenbord_blue);
                hints.setBackgroundResource(R.drawable.roundbutton);
                hintText.setBackgroundResource(R.drawable.borders_blue);
                ima.setBackgroundResource(R.drawable.borders_blue);

                break;

            case ("Green"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundGreen, null);
                v.setBackgroundColor(colour);

                progress.setBackgroundResource(R.drawable.redbord_green);
                hints.setBackgroundResource(R.drawable.roundbuttong);
                hintText.setBackgroundResource(R.drawable.borders_green);
                ima.setBackgroundResource(R.drawable.borders_green);
                break;

            case ("Purple"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundPurple, null);
                v.setBackgroundColor(colour);

                progress.setBackgroundResource(R.drawable.greenbord_purple);
                hints.setBackgroundResource(R.drawable.roundbuttonp);
                hintText.setBackgroundResource(R.drawable.borders_purple);
                ima.setBackgroundResource(R.drawable.borders_purple);

                break;


            default:
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundBlue, null);
                v.setBackgroundColor(colour);

                progress.setBackgroundResource(R.drawable.greenbord_blue);
                hints.setBackgroundResource(R.drawable.roundbutton);
                hintText.setBackgroundResource(R.drawable.borders_blue);
                ima.setBackgroundResource(R.drawable.borders_blue);
                break;

        }
    }

    public void setTextSize(){
        SharedPreferences sharedPref = getContext().getSharedPreferences("colourInfo", Context.MODE_PRIVATE);
        String textSize = sharedPref.getString("textSize", "");

        switch(textSize){
            case ("Small"):
                actText.setTextSize(25);
                progress.setTextSize(15);
                hintText.setTextSize(15);

                break;

            case ("Medium"):

                actText.setTextSize(30);
                progress.setTextSize(20);
                hintText.setTextSize(20);
                break;

            case ("Large"):
                actText.setTextSize(35);
                progress.setTextSize(25);
                hintText.setTextSize(25);
                break;


            default:
                actText.setTextSize(30);
                progress.setTextSize(20);
                hintText.setTextSize(30);
                break;
        }
    }

}
