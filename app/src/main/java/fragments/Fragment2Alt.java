package fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.frazzle.appforasc.ExtendedApp;
import com.example.frazzle.appforasc.PopUpVideo;
import com.example.frazzle.appforasc.R;
import com.example.frazzle.appforasc.storyListAdapter;

/**
 * Created by Frazzle on 18/01/2016.
 */
public class Fragment2Alt extends Fragment{

    String colourString;
    View v;
    ListView ideasList;
    TextView actText;
    Button hints;
    ImageView toMiddle;
    Fragment2AltListener activityCommander;

    public interface Fragment2AltListener{
        public void backToMiddle();
    }

    public void toMiddle(View view){
        activityCommander.backToMiddle();
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment2_alt_layout, container, false);

        hints = (Button) v.findViewById(R.id.hints);
        toMiddle = (ImageView) v.findViewById(R.id.backButton);


        Bundle args = getArguments();
        String index = args.getString("act");
        colourString = args.getString("colourString");
        String [] theIdeas = args.getStringArray("ideas");
        String orient = args.getString("orientation");
        moveButton(orient);
        actText = (TextView) v.findViewById(R.id.actText);
        actText.setText(index);

        ideasList = (ListView) v.findViewById(R.id.ideasList);
        ArrayAdapter aa = new storyListAdapter(getActivity(), theIdeas);
        ideasList.setAdapter(aa);

        ideasList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String videoName = parent.getItemAtPosition(position).toString();
                showVideo(videoName);
            }
        });



        ideasList.setVisibility(View.INVISIBLE);

        hints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showIdeas(v);
            }
        });

        toMiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toMiddle(v);
            }
        });

        return v;
    }

    public void showVideo(String x){
        Intent i = new Intent(getActivity(), PopUpVideo.class);
        i.putExtra("videoName", x);
        startActivity(i);
    }


    private void moveButton(String orient){
        ViewGroup.LayoutParams vg_lp = toMiddle.getLayoutParams();
        RelativeLayout.LayoutParams rl_lp = new RelativeLayout.LayoutParams(vg_lp);

        if (orient.equals("left")){
            rl_lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            rl_lp.addRule(RelativeLayout.CENTER_VERTICAL);
            toMiddle.setRotation(180);

        }else{
            rl_lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            rl_lp.addRule(RelativeLayout.CENTER_VERTICAL);
        }

        toMiddle.setLayoutParams(rl_lp);
    }

    @Override
    public void onStart() {
        super.onStart();
        //v.setBackgroundColor(colour);
        setTheme();
    }

    public void showIdeas(View view){
        ideasList.setVisibility(View.VISIBLE);
        hints.setVisibility(View.INVISIBLE);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            activityCommander = (Fragment2AltListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString());
        }
    }


    public void setTheme() {

        int colour;

        switch (colourString) {


            case ("Red"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundRed, null);
                v.setBackgroundColor(colour);

                toMiddle.setBackgroundResource(R.drawable.border_red);
                hints.setBackgroundResource(R.drawable.roundbuttonr);
                break;

            case ("Blue"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundBlue, null);
                v.setBackgroundColor(colour);

                toMiddle.setBackgroundResource(R.drawable.borders_blue);
                hints.setBackgroundResource(R.drawable.roundbutton);

                break;

            case ("Green"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundGreen, null);
                v.setBackgroundColor(colour);

                toMiddle.setBackgroundResource(R.drawable.borders_green);
                hints.setBackgroundResource(R.drawable.roundbuttong);
                break;

            case ("Purple"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundPurple, null);
                v.setBackgroundColor(colour);

                toMiddle.setBackgroundResource(R.drawable.borders_purple);
                hints.setBackgroundResource(R.drawable.roundbuttonp);

                break;


            default:
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundBlue, null);
                v.setBackgroundColor(colour);

                toMiddle.setBackgroundResource(R.drawable.borders_blue);
                hints.setBackgroundResource(R.drawable.roundbutton);
                break;

        }
    }

    public void setTextSize(){
        SharedPreferences sharedPref = getContext().getSharedPreferences("colourInfo", Context.MODE_PRIVATE);
        String textSize = sharedPref.getString("textSize", "");

        switch(textSize){
            case ("Small"):
                actText.setTextSize(25);
                hints.setTextSize(15);


                break;

            case ("Medium"):

                actText.setTextSize(30);
                hints.setTextSize(20);

                break;

            case ("Large"):
                actText.setTextSize(35);
                hints.setTextSize(25);

                break;


            default:
                actText.setTextSize(30);
                hints.setTextSize(20);

                break;
        }
    }
}



