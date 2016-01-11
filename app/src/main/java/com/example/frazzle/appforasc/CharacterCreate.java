package com.example.frazzle.appforasc;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CharacterCreate extends Activity {

    EditText nameInput;
    EditText rewardInput;
    CharacterDBHandler dbHandler;
    String name;
    String reward;
    Button deleteButton;
    int id;


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
        rewardInput = (EditText) findViewById(R.id.rewardInput);
        dbHandler = new CharacterDBHandler(this, null, null, 1);
        deleteButton = (Button) findViewById(R.id.deleteCharacter);

        Bundle charData = getIntent().getExtras();
        if (charData == null){
            deleteButton.setEnabled(false);
            return;
        }

        name = charData.getString("name");
        reward = charData.getString("reward");
        id = charData.getInt("id");

        nameInput.setText(name);
        rewardInput.setText(reward);

    }


    public void saveCharacter(View view) {

        String name = nameInput.getText().toString();
        String reward = rewardInput.getText().toString();
        Character character = new Character(name, reward);

        dbHandler.addCharacter(character);

        this.finish();


    }

    public void deleteCharacter(View view) {

        dbHandler.deleteCharacter(id);
        this.finish();

    }

}
