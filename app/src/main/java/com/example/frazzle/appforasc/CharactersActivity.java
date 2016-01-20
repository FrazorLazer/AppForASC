package com.example.frazzle.appforasc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class CharactersActivity extends AppCompatActivity {

    RelativeLayout layout;
    CharacterDBHandler dbHandler;
    ListView characterListView;
    ArrayAdapter arrayAdapter;
    Character [] characters;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters);

        dbHandler = new CharacterDBHandler(this, null, null, 1);
        characterListView = (ListView) findViewById(R.id.characterList);
        setBackgroundColour();


        String s = ((ExtendedApp) this.getApplication()).getStory();

    }

    @Override
    protected void onResume() {
        super.onResume();
        populateAdapter();
    }


    public void populateAdapter(){

        characters = dbHandler.returnCharacters();
        arrayAdapter = new characterRowAdapter(this, characters);
        characterListView.setAdapter(arrayAdapter);
        characterListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        openCharacterView(characters[position]);
                    }
                }
        );

    }

    public void openCharacterView(Character c){
        Intent i = new Intent(CharactersActivity.this, CharacterCreate.class);

        i.putExtra("name", c.get_name());
        i.putExtra("reward", c.get_reward());
        i.putExtra("id", c.get_id());
        i.putExtra("pathname", c.get_profileImagePath());
        startActivity(i);
    }


    public void createCharacter(View view){
        Intent i = new Intent(CharactersActivity.this, CharacterCreate.class);
        startActivity(i);
    }


    public void setBackgroundColour(){
        SharedPreferences sharedPref = getSharedPreferences("colourInfo", Context.MODE_PRIVATE);
        String backgroundColour = sharedPref.getString("backgroundC", "");
        layout = (RelativeLayout) findViewById(R.id.charactersAct);
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
