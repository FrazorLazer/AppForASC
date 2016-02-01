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

/**
 * Created by Frazzle on 01/02/2016.
 */
public class storyListAdapter extends ArrayAdapter<String> {

    public storyListAdapter(Context context, String[] stories) {
        super(context, R.layout.story_list_layout, stories);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.story_list_layout, parent, false);

        String storyName = getItem(position);

        TextView buckyText = (TextView) customView.findViewById(R.id.deezStories);

        buckyText.setText(storyName);

        return customView;
    }

}

