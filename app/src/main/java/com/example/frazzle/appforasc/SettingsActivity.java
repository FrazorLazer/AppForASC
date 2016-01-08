package com.example.frazzle.appforasc;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    EditText backColour;
    ListView settingsView;
    ListAdapter arrayAdapter;
    RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        settingsView = (ListView) findViewById(R.id.settingsList);
        String settingsOptions[] = new String[] {"Background Colour", "Font Size", "Vibration"};
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, settingsOptions);
        settingsView.setAdapter(arrayAdapter);

        settingsView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        settingsView.showContextMenuForChild(view);
                    }
                }
        );

        registerForContextMenu(settingsView);

    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;
        if (info == null) {
            Toast.makeText(this, "Missing Menu", Toast.LENGTH_SHORT).show();
            return;
        }


        int selection = info.position;
        if (selection == 0) {
            menu.setHeaderTitle("Set Background Colour");
            menu.add("Blue");
            menu.add("White");
            menu.add("Red");
        }else{
            menu.add("Waiting for Implementation");
        }

    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {

        Toast.makeText(SettingsActivity.this, "Theme Colour Changed: " + item.getTitle() , Toast.LENGTH_SHORT).show();
        changeColour(item.getTitle().toString());
        return super.onContextItemSelected(item);

    }

    public void changeColour(String colour){
        SharedPreferences sharedPref = getSharedPreferences("colourInfo", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("backgroundC", colour);
        editor.apply();

        recreate();
    }



    @Override
    protected void onResume() {
        super.onResume();
        setBackgroundColour();
    }


    public void setBackgroundColour(){
        SharedPreferences sharedPref = getSharedPreferences("colourInfo", Context.MODE_PRIVATE);
        String backgroundColour = sharedPref.getString("backgroundC", "");
        layout = (RelativeLayout) findViewById(R.id.settingsAct);
        int colour;

        switch(backgroundColour) {
            case ("Red"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundRed, null);
                layout.setBackgroundColor(colour);
                //Toast.makeText(this, "RedSet", Toast.LENGTH_LONG).show();
                break;

            case ("Blue"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundBlue, null);
                layout.setBackgroundColor(colour);
                //Toast.makeText(this, "BlueSet", Toast.LENGTH_LONG).show();
                break;

            case ("White"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundWhite, null);
                layout.setBackgroundColor(colour);
                //Toast.makeText(this, "RedSet", Toast.LENGTH_LONG).show();
                break;

            default:
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundBlue, null);
                layout.setBackgroundColor(colour);
                break;

        }



    }


}
