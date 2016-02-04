package com.example.frazzle.appforasc;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Frazzle on 01/02/2016.
 */
public class characterGridAdapter extends ArrayAdapter<Character> {

    TextView buckyText;

    public characterGridAdapter(Context context, Character[] characters) {
        super(context, R.layout.character_gridview_layout, characters);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.character_gridview_layout, parent, false);

        String singleCharacterName = getItem(position).get_name();
        String singleCharacterImagePathname = getItem(position).get_profileImagePath();

        buckyText = (TextView) customView.findViewById(R.id.gridItem);
        setColors();
        buckyText.setText(singleCharacterName);

        Drawable song1 = BitmapDrawable.createFromPath(singleCharacterImagePathname);
        Drawable placeholder = new ResourcesCompat().getDrawable(getContext().getResources(), R.drawable.placeholder_profile_photo, null);
        if (song1 != null){
            song1.setBounds(0, 0, 200, 200);
            buckyText.setCompoundDrawables(null, song1, null, null);
        }else{
            placeholder.setBounds(0, 0, 200, 200);
            buckyText.setCompoundDrawables(null, placeholder, null, null);
        }

        return customView;
    }

    private void setColors(){

        SharedPreferences sharedPref = getContext().getSharedPreferences("colourInfo", Context.MODE_PRIVATE);
        String backgroundColour = sharedPref.getString("backgroundC", "");

        switch(backgroundColour) {
            case ("Red"):
                buckyText.setBackgroundResource(R.drawable.border_red);
                buckyText.setTextColor(new ResourcesCompat().getColor(getContext().getResources(), R.color.LightestRed, null));
                break;

            case ("Blue"):
                buckyText.setBackgroundResource(R.drawable.borders_blue);
                buckyText.setTextColor(new ResourcesCompat().getColor(getContext().getResources(), R.color.LightestBlue, null));
                break;

            case ("Green"):
                buckyText.setBackgroundResource(R.drawable.borders_green);
                buckyText.setTextColor(new ResourcesCompat().getColor(getContext().getResources(), R.color.LightestGreen, null));
                break;

            case ("Purple"):
                buckyText.setBackgroundResource(R.drawable.borders_purple);
                buckyText.setTextColor(new ResourcesCompat().getColor(getContext().getResources(), R.color.LightestPurple, null));

                break;


            default:
                buckyText.setBackgroundResource(R.drawable.borders_blue);
                buckyText.setTextColor(new ResourcesCompat().getColor(getContext().getResources(), R.color.LightestBlue, null));
                break;

        }

    }
}
