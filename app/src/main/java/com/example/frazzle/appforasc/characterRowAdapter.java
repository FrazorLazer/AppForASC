package com.example.frazzle.appforasc;

/**
 * Created by Frazzle on 08/01/2016.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;

class characterRowAdapter extends ArrayAdapter<Character> {

    public characterRowAdapter(Context context, Character[] characters) {
        super(context, R.layout.character_list_row, characters);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.character_list_row, parent, false);

        String singleCharacterName = getItem(position).get_name();
        String singleCharacterID = getItem(position).get_id() + "";
        TextView buckyText = (TextView) customView.findViewById(R.id.nameText);
        ImageView buckyImage = (ImageView) customView.findViewById(R.id.nameImage);
        TextView buckyID = (TextView) customView.findViewById(R.id.nameID);

        buckyText.setText(singleCharacterName);
        buckyImage.setImageResource(R.drawable.bucky);
        buckyID.setText(singleCharacterID);

        return customView;
    }

}