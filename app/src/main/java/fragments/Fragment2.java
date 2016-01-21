package fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.frazzle.appforasc.R;
import com.example.frazzle.appforasc.StoryActivity;

public class Fragment2 extends Fragment {

    View v;
    int colour;
    Button toMiddle;
    Fragment2Listener activityCommander;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment2_layout, container, false);

        Bundle args = getArguments();
        String index = args.getString("act");
        colour = args.getInt("colour");

        toMiddle = (Button) v.findViewById(R.id.backButton);
        TextView storyText = (TextView) v.findViewById(R.id.actText);
        storyText.setText(index);

        String orient = args.getString("orientation");
        moveButton(orient);

        toMiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toMiddle(v);
            }
        });

        return v;

    }


    private void moveButton(String orient){
        ViewGroup.LayoutParams vg_lp = toMiddle.getLayoutParams();
        RelativeLayout.LayoutParams rl_lp = new RelativeLayout.LayoutParams(vg_lp);

        if (orient.equals("left")){
            rl_lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            rl_lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        }else{
            rl_lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            rl_lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        }

        toMiddle.setLayoutParams(rl_lp);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            activityCommander = (Fragment2Listener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString());
        }
    }




    public interface Fragment2Listener{
        public void backToMiddle();
    }

    public void toMiddle(View view){
        activityCommander.backToMiddle();
    }




    @Override
    public void onStart() {
        super.onStart();
        v.setBackgroundColor(colour);
    }
}
