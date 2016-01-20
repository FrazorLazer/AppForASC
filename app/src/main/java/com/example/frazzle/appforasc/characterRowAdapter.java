package com.example.frazzle.appforasc;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


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
        String singleCharacterImagePathname = getItem(position).get_profileImagePath();

        TextView buckyText = (TextView) customView.findViewById(R.id.nameText);
        ImageView buckyImage = (ImageView) customView.findViewById(R.id.nameImage);
        TextView buckyID = (TextView) customView.findViewById(R.id.nameID);

        buckyText.setText(singleCharacterName);
        buckyID.setText(singleCharacterID);

        Bitmap profilePhoto = BitmapFactory.decodeFile(singleCharacterImagePathname);
        if (profilePhoto != null){
            buckyImage.setImageBitmap(profilePhoto);
        }



        return customView;
    }

}
