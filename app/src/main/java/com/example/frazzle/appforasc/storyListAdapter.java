package com.example.frazzle.appforasc;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Frazzle on 01/02/2016.
 */
public class storyListAdapter extends ArrayAdapter<String> {

    TextView buckyText;

    public storyListAdapter(Context context, String[] stories) {
        super(context, R.layout.story_list_layout, stories);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.story_list_layout, parent, false);

        String storyName = getItem(position);

        buckyText = (TextView) customView.findViewById(R.id.deezStories);
        buckyText.setText(storyName);
        setColors();

        return customView;
    }


    private void setColors(){

        SharedPreferences sharedPref = getContext().getSharedPreferences("colourInfo", Context.MODE_PRIVATE);
        String backgroundColour = sharedPref.getString("backgroundC", "");

        switch(backgroundColour) {
            case ("Red"):
                buckyText.setBackgroundResource(R.drawable.border_red);
                buckyText.setTextColor(new ResourcesCompat().getColor(getContext().getResources(), R.color.BackgroundWhite, null));
                break;

            case ("Blue"):
                buckyText.setBackgroundResource(R.drawable.borders_blue);
                buckyText.setTextColor(new ResourcesCompat().getColor(getContext().getResources(), R.color.BackgroundWhite, null));
                break;

            case ("Green"):
                buckyText.setBackgroundResource(R.drawable.borders_green);
                buckyText.setTextColor(new ResourcesCompat().getColor(getContext().getResources(), R.color.BackgroundWhite, null));
                break;

            case ("Purple"):
                buckyText.setBackgroundResource(R.drawable.borders_purple);
                buckyText.setTextColor(new ResourcesCompat().getColor(getContext().getResources(), R.color.BackgroundWhite, null));

                break;


            default:
                buckyText.setBackgroundResource(R.drawable.borders_blue);
                buckyText.setTextColor(new ResourcesCompat().getColor(getContext().getResources(), R.color.BackgroundWhite, null));
                break;

        }

    }

}

