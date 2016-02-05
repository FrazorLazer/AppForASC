package fragments;

import android.content.Context;
import android.content.Intent;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.frazzle.appforasc.CongratulationsActivity;
import com.example.frazzle.appforasc.R;

public class Fragment3 extends Fragment {

    View v;
    int colour;
    int correctAnswer;
    String colourString;
    Button answer1;
    Button answer2;
    Button answer3;
    ImageView ima;
    Fragment3Listener activityCommander;
    int IncorrectAnswers = 0;

    public interface Fragment3Listener{
        public void backToMiddle();
        public void progressStory(View view);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment3_layout, container, false);

        Bundle args = getArguments();
        String index = args.getString("guess");
        colour = args.getInt("colour");
        colourString = args.getString("colourString");
        correctAnswer = args.getInt("answer");
        String [] options = args.getStringArray("options");
        ima = (ImageView) v.findViewById(R.id.backButton2);


        TextView storyText = (TextView) v.findViewById(R.id.guessText);
        storyText.setText(index);

        //toMiddle = (Button) v.findViewById(R.id.backButton2);
        answer1 = (Button) v.findViewById(R.id.answer1Button);
        answer2 = (Button) v.findViewById(R.id.answer2Button);
        answer3 = (Button) v.findViewById(R.id.answer3Button);

        answer1.setText(options[0]);
        answer2.setText(options[1]);
        answer3.setText(options[2]);

        String orient = args.getString("orientation");
        moveButton(orient);

        ima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toMiddle(v);
            }
        });

/*
        toMiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toMiddle(v);
            }
        });
*/
        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer1Clicked(v);
            }
        });

        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer2Clicked(v);
            }
        });

        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer3Clicked(v);
            }
        });

        return v;
    }


    private void moveButton(String orient){
        ViewGroup.LayoutParams vg_lp = ima.getLayoutParams();
        RelativeLayout.LayoutParams rl_lp = new RelativeLayout.LayoutParams(vg_lp);

        if (orient.equals("left")){
            rl_lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            rl_lp.addRule(RelativeLayout.CENTER_VERTICAL);
            ima.setRotation(180);

        }else{
            rl_lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            rl_lp.addRule(RelativeLayout.CENTER_VERTICAL);
        }

        ima.setLayoutParams(rl_lp);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            activityCommander = (Fragment3Listener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString());
        }
    }

    public void toMiddle(View view){
        activityCommander.backToMiddle();
    }


    public void progress(View view){
        activityCommander.progressStory(view);
    }



    public void answer1Clicked(View view){

        if (correctAnswer == 1){
            progress(view);
            //Intent i = new Intent (this, CongratulationsActivity.class);
            //Toast.makeText(getActivity(), "Correct Answer", Toast.LENGTH_SHORT).show();
            return;
        }

        if(IncorrectAnswers % 2 == 0) {
            for (int i=0; i < 2; i++)
            {
                Toast.makeText(getActivity(), "Good effort! Try giving it another go.", Toast.LENGTH_LONG).show();
            }

        }else{
            for (int i=0; i < 2; i++)
            {
                Toast.makeText(getActivity(), "Unlucky, try one more.", Toast.LENGTH_LONG).show();
            }
        }
        IncorrectAnswers++;
    }

    public void answer2Clicked(View view){

        if (correctAnswer == 2){
            progress(view);


            return;
        }

        if(IncorrectAnswers % 2 == 0) {
            for (int i=0; i < 2; i++)
            {
                Toast.makeText(getActivity(), "Good effort! Try giving it another go.", Toast.LENGTH_LONG).show();
            }

        }else{
            for (int i=0; i < 2; i++)
            {
                Toast.makeText(getActivity(), "Unlucky, try one more.", Toast.LENGTH_LONG).show();
            }
        }
        IncorrectAnswers++;
    }

    public void answer3Clicked(View view){

        if (correctAnswer == 3){
            progress(view);
            return;
        }


        if(IncorrectAnswers % 2 == 0) {
            for (int i=0; i < 2; i++)
            {
                Toast.makeText(getActivity(), "Good effort! Try giving it another go.", Toast.LENGTH_LONG).show();
            }

        }else{
            for (int i=0; i < 2; i++)
            {
                Toast.makeText(getActivity(), "Unlucky, try one more.", Toast.LENGTH_LONG).show();
            }
        }
        IncorrectAnswers++;
    }




    @Override
    public void onStart() {
        super.onStart();
        //v.setBackgroundColor(colour);
        setTheme();
    }


    public void setTheme() {

        int colour;

        switch (colourString) {


            case ("Red"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundRed, null);
                v.setBackgroundColor(colour);

                answer1.setBackgroundResource(R.drawable.roundbuttonr);
                answer2.setBackgroundResource(R.drawable.roundbuttonr);
                answer3.setBackgroundResource(R.drawable.roundbuttonr);
                ima.setBackgroundResource(R.drawable.border_red);
                break;

            case ("Blue"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundBlue, null);
                v.setBackgroundColor(colour);

                answer1.setBackgroundResource(R.drawable.roundbutton);
                answer2.setBackgroundResource(R.drawable.roundbutton);
                answer3.setBackgroundResource(R.drawable.roundbutton);
                ima.setBackgroundResource(R.drawable.borders_blue);

                break;

            case ("Green"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundGreen, null);
                v.setBackgroundColor(colour);

                answer1.setBackgroundResource(R.drawable.roundbuttong);
                answer2.setBackgroundResource(R.drawable.roundbuttong);
                answer3.setBackgroundResource(R.drawable.roundbuttong);
                ima.setBackgroundResource(R.drawable.borders_green);
                break;

            case ("Purple"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundPurple, null);
                v.setBackgroundColor(colour);

                answer1.setBackgroundResource(R.drawable.roundbuttonp);
                answer2.setBackgroundResource(R.drawable.roundbuttonp);
                answer3.setBackgroundResource(R.drawable.roundbuttonp);
                ima.setBackgroundResource(R.drawable.borders_purple);

                break;


            default:
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundBlue, null);
                v.setBackgroundColor(colour);

                answer1.setBackgroundResource(R.drawable.roundbutton);
                answer2.setBackgroundResource(R.drawable.roundbutton);
                answer3.setBackgroundResource(R.drawable.roundbutton);
                ima.setBackgroundResource(R.drawable.borders_blue);
                break;

        }
    }

}
