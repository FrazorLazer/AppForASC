package com.example.frazzle.appforasc;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.service.wallpaper.WallpaperService;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {


    ListView settingsView;
    ListAdapter arrayAdapter;
    RelativeLayout layout;
    TextView title;
    Button closeButton;
    int whatMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        title = (TextView) findViewById(R.id.textView4);
        settingsView = (ListView) findViewById(R.id.settingsList);
        closeButton = (Button) findViewById(R.id.closeButton);
        String settingsOptions[] = new String[] {"Background Colour", "Font Size", "Vibration"};
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, settingsOptions);
        settingsView.setAdapter(arrayAdapter);

        settingsView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        settingsView.showContextMenuForChild(view);
                    }
                }
        );

        Typeface kristen = Typeface.createFromAsset(getAssets(), "ITCKRIST.TTF");
        title.setTypeface(kristen);

        registerForContextMenu(settingsView);
        setTextSize();

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
            menu.add("Green");
            menu.add("Red");
            menu.add("Purple");
            whatMenuItem = 0;
        }else if (selection == 1){
            menu.add("Small");
            menu.add("Medium");
            menu.add("Large");
            whatMenuItem = 1;
        }else{
            menu.add("Waiting for Implementation");
            whatMenuItem = 2;
        }

    }


    public void closeWindow(View view){
        onBackPressed();
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if(whatMenuItem == 0){
            Toast.makeText(SettingsActivity.this, "Theme Colour Changed: " + item.getTitle() , Toast.LENGTH_SHORT).show();
        }else if (whatMenuItem == 1){
            Toast.makeText(SettingsActivity.this, "Text Size Changed: " + item.getTitle() , Toast.LENGTH_SHORT).show();
        }else if (whatMenuItem == 2){
            Toast.makeText(SettingsActivity.this, "Voice Over Changed: " + item.getTitle() , Toast.LENGTH_SHORT).show();
        }


        changeSettings(item.getTitle().toString());
        return super.onContextItemSelected(item);

    }

    public void changeSettings(String item){
        SharedPreferences sharedPref = getSharedPreferences("colourInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        if(whatMenuItem == 0){
            editor.putString("backgroundC", item);
        }else if (whatMenuItem == 1){
            editor.putString("textSize", item);
        }else if (whatMenuItem == 2){
            editor.putString("voiceOver", item);
        }

        editor.apply();

        recreate();
    }


    @Override
    protected void onResume() {
        super.onResume();
        setBackgroundColour();
        setThemes();
    }

    public void setTextSize(){
        SharedPreferences sharedPref = getSharedPreferences("colourInfo", Context.MODE_PRIVATE);
        String textSize = sharedPref.getString("textSize", "");

        switch(textSize){
            case ("Small"):
                title.setTextSize(40);
                break;

            case ("Medium"):

                title.setTextSize(50);
                break;

            case ("Large"):
                title.setTextSize(60);
                break;


            default:
                title.setTextSize(50);
                break;
        }
    }

    public void setThemes(){

        SharedPreferences sharedPref = getSharedPreferences("colourInfo", Context.MODE_PRIVATE);
        String backgroundColour = sharedPref.getString("backgroundC", "");

        switch(backgroundColour){
            case ("Red"):

                title.setBackgroundResource(R.drawable.border_red);
                closeButton.setBackgroundResource(R.drawable.roundbuttonr);
                //setTheme(R.style.AppTheme_RedTheme);
                break;

            case ("Blue"):

                title.setBackgroundResource(R.drawable.borders_blue);
                closeButton.setBackgroundResource(R.drawable.roundbutton);
                //setTheme(R.style.AppTheme_BlueTheme);
                break;

            case ("Green"):
                title.setBackgroundResource(R.drawable.borders_green);
                closeButton.setBackgroundResource(R.drawable.roundbuttong);
                //setTheme(R.style.AppTheme_BlueTheme);
                break;

            case ("Purple"):
                title.setBackgroundResource(R.drawable.borders_purple);
                closeButton.setBackgroundResource(R.drawable.roundbuttonp);
                //setTheme(R.style.AppTheme_BlueTheme);
                break;


            default:
                title.setBackgroundResource(R.drawable.borders_blue);
                closeButton.setBackgroundResource(R.drawable.roundbutton);
                //setTheme(R.style.AppTheme_BlueTheme);
                break;
        }



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

            case ("Green"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundGreen, null);
                layout.setBackgroundColor(colour);
                //Toast.makeText(this, "RedSet", Toast.LENGTH_LONG).show();
                break;

            case ("Purple"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundPurple, null);
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
