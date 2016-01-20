package fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.frazzle.appforasc.CongratulationsActivity;
import com.example.frazzle.appforasc.R;

public class Fragment3 extends Fragment {

    View v;
    int colour;
    int correctAnswer;

    Fragment3Listener activityCommander;

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
        correctAnswer = args.getInt("answer");
        String [] options = args.getStringArray("options");

        TextView storyText = (TextView) v.findViewById(R.id.guessText);
        storyText.setText(index);

        Button toMiddle = (Button) v.findViewById(R.id.backButton2);
        Button answer1 = (Button) v.findViewById(R.id.answer1Button);
        Button answer2 = (Button) v.findViewById(R.id.answer2Button);
        Button answer3 = (Button) v.findViewById(R.id.answer3Button);

        answer1.setText(options[0]);
        answer2.setText(options[1]);
        answer3.setText(options[2]);


        toMiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toMiddle(v);
            }
        });

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

        Toast.makeText(getActivity(), "Incorrect Answer", Toast.LENGTH_SHORT).show();
    }

    public void answer2Clicked(View view){

        if (correctAnswer == 2){
            progress(view);


            return;
        }

        Toast.makeText(getActivity(), "Incorrect Answer", Toast.LENGTH_SHORT).show();
    }

    public void answer3Clicked(View view){

        if (correctAnswer == 3){
            progress(view);


            return;
        }

        Toast.makeText(getActivity(), "Incorrect Answer", Toast.LENGTH_SHORT).show();
    }




    @Override
    public void onStart() {
        super.onStart();
        v.setBackgroundColor(colour);
    }
}
