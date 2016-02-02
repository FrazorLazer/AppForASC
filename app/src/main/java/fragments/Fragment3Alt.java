package fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

    int colour;
    Button hints;
    TextView hintText;
    ImageView ima;
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
        colour = args.getInt("colour");
        String hintPassage = args.getString("hint");
        String orient = args.getString("orientation");
        ima = (ImageView) v.findViewById(R.id.backButton2);
        moveButton(orient);

        TextView actText = (TextView) v.findViewById(R.id.actText);
        actText.setText(index);
        hints = (Button) v.findViewById(R.id.hints);
        hintText = (TextView) v.findViewById(R.id.hintText);
        hintText.setText(hintPassage);
        hintText.setVisibility(View.INVISIBLE);
        Button progress = (Button) v.findViewById(R.id.progressButtonAlt);

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
        v.setBackgroundColor(colour);
    }

    public void showHint(View view){
        hints.setVisibility(View.INVISIBLE);
        hintText.setVisibility(View.VISIBLE);
    }


}
