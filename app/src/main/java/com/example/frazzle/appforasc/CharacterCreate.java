package com.example.frazzle.appforasc;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.preference.DialogPreference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CharacterCreate extends Activity {

    EditText nameInput;
    EditText rewardInput;
    CharacterDBHandler dbHandler;
    String name;
    String reward;
    Button deleteButton;
    int id;
    Boolean isNew = false;
    Spinner rewardSpinner;
    String newName;
    String newReward;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_create);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width*.8) , (int) (height*.7));

        nameInput = (EditText) findViewById(R.id.nameInput);


        dbHandler = new CharacterDBHandler(this, null, null, 1);
        deleteButton = (Button) findViewById(R.id.deleteCharacter);

        Bundle charData = getIntent().getExtras();
        if (charData == null){
            reward = "No Sound";
            deleteButton.setEnabled(false);
            isNew = true;
            setSpinner();
            return;
        }

        name = charData.getString("name");
        reward = charData.getString("reward");
        id = charData.getInt("id");

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
                newReward = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public void saveCharacter(View view) {

        newName = nameInput.getText().toString();

        if (isNew){
            Character character = new Character(newName, newReward);
            dbHandler.addCharacter(character);
        }else{
            dbHandler.updateCharacter(id, newName, newReward);
        }

        this.finish();

    }

    public void deleteCharacter(View view){

        AlertDialog alertDialog = createDialog();
        alertDialog.show();
        //closeWindow(view);

    }


    private AlertDialog createDialog() {


        AlertDialog alertdia = new AlertDialog.Builder(this)
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
        return alertdia;

    }


    public void closeWindow(View view){
        this.finish();
    }


    public int getSpinnerPos(){
        switch(reward){
            case "No Sound": return 0;
            case "Horn": return 1;
            case "Cheer": return 2;
            case "Drums": return 3;
            case "Song": return 4;
            case "Ping": return 5;
            default:return 0;
        }


    }

}
