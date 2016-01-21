package fragments;



import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.frazzle.appforasc.ExtendedApp;
import com.example.frazzle.appforasc.R;
import com.example.frazzle.appforasc.StoryActivity;

public class Fragment1 extends Fragment {

    Button goLeft;
    Button goRight;
    Button progressButton;
    int colour;
    Fragment1Listener activityCommander;
    View v;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment1_layout, container, false);

        goLeft = (Button) v.findViewById(R.id.goLeft);
        goRight = (Button) v.findViewById(R.id.goRight);
        progressButton = (Button) v.findViewById(R.id.progressButton);
        Button exitButton = (Button) v.findViewById(R.id.exitButton);

        Bundle args = getArguments();
        String index = args.getString("story");
        colour = args.getInt("colour");
        String pathname1 = args.getString("pathname1");
        String pathname2 = args.getString("pathname2");
        String charName1 = args.getString("name1");
        String charName2 = args.getString("name2");

        TextView storyText = (TextView) v.findViewById(R.id.storyText);
        storyText.setText(index);

        Drawable song1 = BitmapDrawable.createFromPath(pathname1);
        song1.setBounds(0, 0, 120, 120);
        goLeft.setCompoundDrawables(null, null, song1, null);
        goLeft.setText("< " + charName1);

        Drawable song2 = BitmapDrawable.createFromPath(pathname2);
        song2.setBounds(0,0,120,120);
        goRight.setCompoundDrawables(song2, null, null, null);
        goRight.setText(charName2 + " >");

        //v.setBackgroundColor(colour);
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
        v.setBackgroundColor(colour);
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

}
