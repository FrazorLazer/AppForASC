package com.example.frazzle.appforasc;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.lang.reflect.Field;


public class CharacterCreate extends Activity {

    CharacterDBHandler dbHandler;
    String name;
    String reward;
    int id;
    Boolean isNew = false;
    Button deleteButton;
    EditText nameInput;
    Spinner rewardSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_create);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width*.8) , (int) (height*.7));

        dbHandler = new CharacterDBHandler(this, null, null, 1);
        nameInput = (EditText) findViewById(R.id.nameInput);
        deleteButton = (Button) findViewById(R.id.deleteCharacter);

        Bundle characterData = getIntent().getExtras();
        if (characterData == null){
            reward = "No Sound";
            deleteButton.setEnabled(false);
            isNew = true;
            setSpinner();
            return;
        }
        name = characterData.getString("name");
        reward = characterData.getString("reward");
        id = characterData.getInt("id");
        nameInput.setText(name);
        setSpinner();

    }

    public void setSpinner(){

        rewardSpinner = (Spinner) findViewById(R.id.rewardSpinner);
        ArrayAdapter<CharSequence> rewardsList = ArrayAdapter.createFromResource(this,
                R.array.rewardSounds, android.R.layout.simple_spinner_item);
        rewardsList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rewardSpinner.setAdapter(rewardsList);
        rewardSpinner.setSelection(getSpinnerPos());

        rewardSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                reward = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


    }



    public void playAudio(View view){

        if (reward.equals("No Sound")){
            return;
        }

        String lowercaseReward = reward.toLowerCase();
        int x = ((ExtendedApp) this.getApplication()).getRawResourceId(lowercaseReward);
        MediaPlayer player;
        player = MediaPlayer.create(this, x);
        player.start();

    }

    public void saveCharacter(View view) {

        name = nameInput.getText().toString();

        if (isNew){
            Character character = new Character(name, reward);
            dbHandler.addCharacter(character);
        }else{
            dbHandler.updateCharacter(id, name, reward);
        }

        this.finish();

    }

    public void deleteCharacter(View view){
        AlertDialog deleteDialog = createDialog();
        deleteDialog.show();
    }


    private AlertDialog createDialog() {


        AlertDialog alertdialog = new AlertDialog.Builder(this)
        .setMessage("Are you sure you want to delete this character")
        .setCancelable(false)

        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dbHandler.deleteCharacter(id);
                dialog.dismiss();
                finish();
            }
        })

        .setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        })
        .create();
        return alertdialog;

    }


    public void closeWindow(View view){
        this.finish();
    }


    public int getSpinnerPos(){

        switch(reward){
            case "No Sound": return 0;
            case "Cheer": return 1;
            case "Guitar": return 2;
            case "Harp": return 3;
            case "Horn": return 4;
            case "Tropical": return 5;
            case "Bell": return 6;
            default:return 0;
        }

    }

}
