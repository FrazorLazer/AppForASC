package com.example.frazzle.appforasc;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CharacterCreate extends Activity {

    CharacterDBHandler dbHandler;
    String name;
    String reward;
    String pathname;
    int id;
    String gender;
    Boolean isNew = false;
    Button deleteButton;
    EditText nameInput;
    Spinner rewardSpinner;
    Spinner genderSpinner;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView profilePhoto;
    Bitmap photo;
    Boolean newPhoto = false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_create);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width*.8) , (int) (height*.7));

        dbHandler = new CharacterDBHandler(this, null, null, 1);
        nameInput = (EditText) findViewById(R.id.nameInput);
        deleteButton = (Button) findViewById(R.id.deleteCharacter);
        Button takePhotoButton = (Button) findViewById(R.id.takePhotoButton);
        profilePhoto = (ImageView) findViewById(R.id.profilePhotoView);

        Bundle characterData = getIntent().getExtras();
        if (characterData == null){
            reward = "No Sound";
            gender = "None";
            deleteButton.setEnabled(false);
            isNew = true;
            setSpinners();
            return;
        }
        name = characterData.getString("name");
        reward = characterData.getString("reward");
        pathname = characterData.getString("pathname");
        gender = characterData.getString("gender");
        id = characterData.getInt("id");
        nameInput.setText(name);
        setSpinners();

        photo = BitmapFactory.decodeFile(pathname);
        if (profilePhoto != null){
            profilePhoto.setImageBitmap(photo);
        }


        if (!hasCamera()){
            takePhotoButton.setEnabled(false);
        }

    }


    private boolean hasCamera(){
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    public void setSpinners(){

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


        genderSpinner = (Spinner) findViewById(R.id.genderSpinner);
        ArrayAdapter<CharSequence> genderList = ArrayAdapter.createFromResource(this,
                R.array.genderOptions, android.R.layout.simple_spinner_item);
        genderList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(genderList);
        genderSpinner.setSelection(getGenderSpinnerPos());

        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gender = parent.getItemAtPosition(position).toString();
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

        if (newPhoto){
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String filename = name + "_" + timeStamp;
            File sd = Environment.getExternalStorageDirectory();
            File dest = new File(sd, filename);

            try {
                FileOutputStream out = new FileOutputStream(dest);
                photo.compress(Bitmap.CompressFormat.PNG, 90, out);
                out.flush();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }


            pathname = dest.getAbsolutePath();
            if (isNew){
                Character character = new Character(name, reward, pathname, gender);
                dbHandler.addCharacter(character);
            }else{
                dbHandler.updateCharacter(id, name, reward, pathname, gender);
            }

        }else{

            if (isNew){
                Character character = new Character(name, reward, pathname, gender);
                dbHandler.addCharacter(character);
            }else{
                dbHandler.updateCharacter(id, name, reward, pathname, gender);
            }
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

    public int getGenderSpinnerPos(){
        switch(gender){
            case "None": return 0;
            case "Girl": return 1;
            case "Boy": return 2;
            default:return 0;
        }
    }


    public void launchCamera(View view){

        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i, REQUEST_IMAGE_CAPTURE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        newPhoto = true;

        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){

            Bundle extras = data.getExtras();
            Bitmap temp = (Bitmap) extras.get("data");

            int height = temp.getHeight();
            int width = temp.getWidth();

            if (width > height){
                int topStart = (width - height) / 2;
                photo = Bitmap.createBitmap(temp, topStart, 0, height, height);
                profilePhoto.setImageBitmap(photo);
                return;
            }

            if (height > width){
                int topStart = (height - width) / 2;
                photo = Bitmap.createBitmap(temp, 0, topStart, width, width);
                profilePhoto.setImageBitmap(photo);
                return;
            }

            photo = Bitmap.createBitmap(temp);

        }

    }
}
