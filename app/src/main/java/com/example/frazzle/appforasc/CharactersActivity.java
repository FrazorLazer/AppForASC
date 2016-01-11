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
    EditText charName;
    EditText charReward;
    EditText charID;
    CharacterDBHandler dbHandler;
    ListView characterList;
    ArrayAdapter arrayAdapter;
    EditText deleteNum;
    Character [] characters;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters);

        charName = (EditText) findViewById(R.id.charName);
        charReward = (EditText) findViewById(R.id.charReward);
        deleteNum = (EditText) findViewById(R.id.deleteNum);
        dbHandler = new CharacterDBHandler(this, null, null, 1);

        characterList = (ListView) findViewById(R.id.characterList);
        setBackgroundColour();
        //populateAdapter();
        /*
        characterList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        openCharacterView(characters[position]);
                    }
                }
        );

        */
    }

    public void populateAdapter(){

        characters = dbHandler.returnCharacters();
        arrayAdapter = new characterRowAdapter(this, characters);
        characterList.setAdapter(arrayAdapter);
        characterList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        openCharacterView(characters[position]);
                    }
                }
        );

    }

    public void createCharacter(View view){
        Intent i = new Intent(CharactersActivity.this, CharacterCreate.class);
        startActivity(i);
    }

    public void deleteChar(View view){
        int num = Integer.parseInt(deleteNum.getText().toString());
        dbHandler.deleteCharacter(num);
        populateAdapter();
    }

    public void addAndUpdate(View view){

        String name = charName.getText().toString();
        String reward = charReward.getText().toString();
        Character character = new Character(name, reward);

        charName.setText("");
        charReward.setText("");

        dbHandler.addCharacter(character);

        populateAdapter();

    }

    public void openCharacterView(Character c){

        Toast.makeText(CharactersActivity.this, "HERE", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(CharactersActivity.this, CharacterCreate.class);

        i.putExtra("name", c.get_name());
        i.putExtra("reward", c.get_reward());
        i.putExtra("id", c.get_id());
        startActivity(i);

    }


    @Override
    protected void onResume() {
        super.onResume();
        populateAdapter();


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
