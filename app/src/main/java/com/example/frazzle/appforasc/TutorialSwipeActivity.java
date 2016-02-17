package com.example.frazzle.appforasc;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import adapters.MyFragmentPagerAdapter;
import fragments.Fragment1;
import fragments.Fragment1Alt;
import fragments.Fragment2;
import fragments.Fragment2Alt;
import fragments.Fragment3;
import fragments.Fragment3Alt;
import fragments.TutorialFragment;

public class TutorialSwipeActivity extends AppCompatActivity implements TutorialFragment.TutorialFragmentListener {

    MyFragmentPagerAdapter mDemoCollectionPagerAdapter;
    ViewPager mViewPager;
    List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_swipe);

        Bundle info = getIntent().getExtras();
        String type = info.getString("type");

        switch (type){

            case "char":
                setUpCharFragments();
                break;
            case "options":
                setUpOptionsFragments();
                break;
            case "noOptions":
                setUpNoOptionsFragments();
                break;
        }


        mDemoCollectionPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mDemoCollectionPagerAdapter);



    }

    public void setUpCharFragments(){

        int numberOfFragements = 5;
        TutorialFragment[] frags = new TutorialFragment[numberOfFragements];

        for (int i = 0; i<frags.length; i++){
            Bundle args = new Bundle();
            args.putString("colourString", getColourString());
            args.putString("slideFilename", "characterscreen" + (i + 1));
            args.putString("theText", findTheString("CharacterTut", i+1));
            args.putBoolean("butt", false);
            if (i == 0) {
                args.putBoolean("first", true);
            }else{
                args.putBoolean("first", false);
            }

            if (i+1 == numberOfFragements) {
                args.putBoolean("last", true);
            }else{
                args.putBoolean("last", false);
            }

            frags[i] = new TutorialFragment();
            frags[i].setArguments(args);
            fragmentList.add(frags[i]);
        }

    }

    @Override
    public void begin(View view){

        Character c1 = new Character("James", "bell", "", "Boy");
        Character c2 = new Character("Sarah", "tropical", "", "Girl");

        ((ExtendedApp) getApplication()).setInTutorial(true);
        ((ExtendedApp) getApplication()).setCharacterOne(c1);
        ((ExtendedApp) getApplication()).setCharacterTwo(c2);
        ((ExtendedApp) getApplication()).setStoryFormat("Options");
        ((ExtendedApp) getApplication()).setStory("Park");

        Intent i = new Intent(this, StoryActivity.class);
        startActivity(i);

    }

    public void exit(View view){
        Intent i = new Intent(this, HomeActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

    public void setUpOptionsFragments(){

        int numberOfFragements = 9;

        TutorialFragment[] frags = new TutorialFragment[numberOfFragements];

        for (int i = 0; i<frags.length; i++){
            Bundle args = new Bundle();
            args.putString("colourString", getColourString());
            args.putString("slideFilename", "optionsscreen" + (i + 1));
            args.putString("theText", findTheString("OptionsTut", i+1));

            if (i == 0) {
                args.putBoolean("first", true);
            }else{
                args.putBoolean("first", false);
            }
            if (i+1 == numberOfFragements) {
                args.putBoolean("last", true);
                args.putBoolean("butt", true);
            }else{
                args.putBoolean("last", false);
                args.putBoolean("butt", false);
            }

            frags[i] = new TutorialFragment();
            frags[i].setArguments(args);
            fragmentList.add(frags[i]);
        }

    }


    public void setUpNoOptionsFragments(){

        int numberOfFragements = 10;
        TutorialFragment[] frags = new TutorialFragment[numberOfFragements];

        for (int i = 0; i<frags.length; i++){
            Bundle args = new Bundle();
            args.putString("colourString", getColourString());
            //args.putString("theText", findTheString("Character", i+1));
            args.putString("slideFilename", "nooptionsscreen" + (i+1));
            args.putString("theText", findTheString("NoOptionsTut", i + 1));

            if (i == 0) {
                args.putBoolean("first", true);
            }else{
                args.putBoolean("first", false);
            }

            if (i+1 == numberOfFragements) {
                args.putBoolean("last", true);
                args.putBoolean("butt", true);
            }else{
                args.putBoolean("last", false);
                args.putBoolean("butt", false);
            }

            frags[i] = new TutorialFragment();
            frags[i].setArguments(args);
            fragmentList.add(frags[i]);
        }

    }



    public String findTheString(String context, int tutSlideNum){

        String keyword = "";
        keyword += context;
        keyword += "Text";
        keyword += Integer.toString(tutSlideNum);

        int id = (((ExtendedApp) getApplication()).getStringResourceId(keyword));
        String raw = getResources().getString(id);

        return raw;

    }
/*
    @Override
    public void moveLeft() {
        mViewPager.setCurrentItem(0);
    }

    @Override
    public void moveRight() {
        mViewPager.setCurrentItem(2);
    }

    @Override
    public void backToMiddle(){
        mViewPager.setCurrentItem(1);
    }


    @Override
    public void exitStory(View view){

        AlertDialog deleteDialog = createDialog();
        deleteDialog.show();
    }

    public void actuallyClose(){
        ((ExtendedApp) getApplication()).setStoryProgress(1);
        Intent i = new Intent(this, HomeActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);

    }


    private AlertDialog createDialog() {


        AlertDialog alertdialog = new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit the story? Your progress will be lost.")
                .setCancelable(false)

                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        actuallyClose();
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

    @Override
    public void progressStory(View view){

        String storyName = ((ExtendedApp) getApplication()).getStory();
        int progress = ((ExtendedApp) getApplication()).getStoryProgress();

        if(storyName.equals("Sleepover") && progress > 7){
            ((ExtendedApp) getApplication()).incrementStoryProgress();
            Intent i2 = new Intent(this, EndStoryActivity.class);
            i2.putExtra("lastLine", findTheString2("Story"));
            startActivity(i2);
            return;
        }

        if(storyName.equals("Park") && progress > 7){
            ((ExtendedApp) getApplication()).incrementStoryProgress();
            Intent i2 = new Intent(this, EndStoryActivity.class);
            i2.putExtra("lastLine", findTheString2("Story"));
            startActivity(i2);
            return;
        }

        if(storyName.equals("Grandparent") && progress > 3){
            ((ExtendedApp) getApplication()).incrementStoryProgress();
            Intent i2 = new Intent(this, EndStoryActivity.class);
            i2.putExtra("lastLine", findTheString2("Story"));
            startActivity(i2);
            return;
        }

        if(storyName.equals("Shopping") && progress > 5){
            ((ExtendedApp) getApplication()).incrementStoryProgress();
            Intent i2 = new Intent(this, EndStoryActivity.class);
            i2.putExtra("lastLine", findTheString2("Story"));
            startActivity(i2);
            return;
        }


        Intent i2 = new Intent(this, CongratulationsActivity.class);
        i2.putExtra("seq", whoseGuessing);
        if(((ExtendedApp) getApplication()).getStoryFormat().equals("Options")){
            i2.putExtra("options", findTheOptions());
        }

        ((ExtendedApp) getApplication()).incrementStoryProgress();
        Intent i = new Intent(this, StoryActivity.class);
        startActivity(i);
        this.finish();

        startActivity(i2);


    }


    public String findTheString2(String context){

        String keyword = "";
        keyword += ((ExtendedApp) getApplication()).getStory();
        keyword += context;
        keyword += Integer.toString(((ExtendedApp) getApplication()).getStoryProgress());

        Log.d("STRING SOUGHT: ", keyword + "\n\n");

        int id = (((ExtendedApp) getApplication()).getStringResourceId(keyword));
        String raw = getResources().getString(id);
        //String replace = raw.replaceAll("James", ((ExtendedApp) getApplication()).getCharacterOne().get_name() );
        //replace = replace.replaceAll("Sarah", ((ExtendedApp) getApplication()).getCharacterTwo().get_name() );

        String replace = personaliseString(raw);
        return replace;

    }


    public int findSequence(){

        String keyword = "";
        keyword += ((ExtendedApp) getApplication()).getStory();
        keyword += "Sequence";
        int prog = (((ExtendedApp) getApplication()).getStoryProgress());

        int id = (((ExtendedApp) getApplication()).getStringResourceId(keyword));
        String raw = getResources().getString(id);

        int seq = (raw.charAt(prog-1)) - '0';
        return seq;

    }

    public String[] findTheOptions(){

        String keyword = "";
        keyword += ((ExtendedApp) getApplication()).getStory();
        keyword += "Answers";
        int prog = ((ExtendedApp) getApplication()).getStoryProgress();
        String pro = Integer.toString(prog);
        keyword += pro;

        int id = (((ExtendedApp) getApplication()).getStringArrayResourceId(keyword));
        String [] raw = getResources().getStringArray(id);

        for (int i = 0; i<3; i++){
            raw[i] = personaliseString(raw[i]);
        }

        return raw;
    }

    public String[] findTheIdeas(){

        String keyword = "";
        keyword += ((ExtendedApp) getApplication()).getStory();
        keyword += "Ideas";
        int prog = ((ExtendedApp) getApplication()).getStoryProgress();
        String pro = Integer.toString(prog);
        keyword += pro;


        int id = (((ExtendedApp) getApplication()).getStringArrayResourceId(keyword));
        String [] raw = getResources().getStringArray(id);

        for (int i = 0; i<3; i++){
            raw[i] = personaliseString(raw[i]);
        }

        return raw;
    }

    public int findTheAnswer(){

        String keyword = "";
        keyword += ((ExtendedApp) getApplication()).getStory();
        keyword += "AnswerSequence";
        int prog = (((ExtendedApp) getApplication()).getStoryProgress());

        int id = (((ExtendedApp) getApplication()).getStringResourceId(keyword));
        String raw = getResources().getString(id);

        int seq = (raw.charAt(prog-1)) - '0';
        return seq;

    }




    public int getBackgroundColour() {
        SharedPreferences sharedPref = getSharedPreferences("colourInfo", Context.MODE_PRIVATE);
        String backgroundColour = "Background" + sharedPref.getString("backgroundC", "");
        //RelativeLayout layout = (RelativeLayout) findViewById(R.id.storyAct);
        int colourID = ((ExtendedApp) getApplication()).getColorResourceId(backgroundColour);
        int c = new ResourcesCompat().getColor(getResources(), colourID, null);
        return c;

    }

*/
    public String getColourString() {
        SharedPreferences sharedPref = getSharedPreferences("colourInfo", Context.MODE_PRIVATE);
        String backgroundColour = sharedPref.getString("backgroundC", "");
        return backgroundColour;

    }
/*


    public String personaliseString(String input){

        String output = "";

        String gender1 = c1.get_gender();
        String gender2 = c2.get_gender();
        String name1 = c1.get_name();
        String name2 = c2.get_name();

        output = input.replaceAll("NAME1", name1);
        output = output.replaceAll("NAME2", name2);

        if (gender1.equals("Boy") && gender2.equals("Boy")){
            output = output.replaceAll("ThirdPron1", "he");
            output = output.replaceAll("ThirdPron2", "he");
            output = output.replaceAll("ThirdPos1", "his");
            output = output.replaceAll("ThirdPos2", "his");
            output = output.replaceAll("ThirdXPos1", "him");
            output = output.replaceAll("ThirdXPos2", "him");
            output = output.replaceAll("ThirdYPos1", "himself");
            output = output.replaceAll("ThirdYPos2", "himself");

            return output;
        }

        if (gender1.equals("Boy") && gender2.equals("Girl")){
            output = output.replaceAll("ThirdPron1", "he");
            output = output.replaceAll("ThirdPron2", "she");
            output = output.replaceAll("ThirdPos1", "his");
            output = output.replaceAll("ThirdPos2", "her");
            output = output.replaceAll("ThirdXPos1", "him");
            output = output.replaceAll("ThirdXPos2", "her");
            output = output.replaceAll("ThirdYPos1", "himself");
            output = output.replaceAll("ThirdYPos2", "herself");

            return output;
        }

        if (gender1.equals("Girl") && gender2.equals("Boy")){
            output = output.replaceAll("ThirdPron2", "he");
            output = output.replaceAll("ThirdPron1", "she");
            output = output.replaceAll("ThirdPos2", "his");
            output = output.replaceAll("ThirdPos1", "her");
            output = output.replaceAll("ThirdXPos2", "him");
            output = output.replaceAll("ThirdXPos1", "her");
            output = output.replaceAll("ThirdYPos2", "himself");
            output = output.replaceAll("ThirdYPos1", "herself");

            return output;
        }

        if (gender1.equals("Girl") && gender2.equals("Girl")){
            output = output.replaceAll("ThirdPron2", "she");
            output = output.replaceAll("ThirdPron1", "she");
            output = output.replaceAll("ThirdPos2", "her");
            output = output.replaceAll("ThirdPos1", "her");
            output = output.replaceAll("ThirdXPos2", "her");
            output = output.replaceAll("ThirdXPos1", "her");
            output = output.replaceAll("ThirdYPos2", "herself");
            output = output.replaceAll("ThirdYPos1", "herself");

            return output;
        }

        if (gender1.equals("None") && gender2.equals("Boy")){
            output = output.replaceAll("ThirdPron2", "he");
            output = output.replaceAll("ThirdPron1", "he");
            output = output.replaceAll("ThirdPos2", "his");
            output = output.replaceAll("ThirdPos1", "his");
            output = output.replaceAll("ThirdXPos2", "him");
            output = output.replaceAll("ThirdXPos1", "her");
            output = output.replaceAll("ThirdYPos2", "himself");
            output = output.replaceAll("ThirdYPos1", "herself");
            return output;
        }

        if (gender1.equals("None") && gender2.equals("Girl")){
            output = output.replaceAll("ThirdPron1", "he");
            output = output.replaceAll("ThirdPron2", "she");
            output = output.replaceAll("ThirdPos1", "his");
            output = output.replaceAll("ThirdPos2", "her");
            output = output.replaceAll("ThirdXPos1", "him");
            output = output.replaceAll("ThirdXPos2", "her");
            output = output.replaceAll("ThirdYPos1", "himself");
            output = output.replaceAll("ThirdYPos2", "herself");

            return output;
        }

        if (gender1.equals("Boy") && gender2.equals("None")){
            output = output.replaceAll("ThirdPron1", "he");
            output = output.replaceAll("ThirdPron2", "she");
            output = output.replaceAll("ThirdPos1", "his");
            output = output.replaceAll("ThirdPos2", "her");
            output = output.replaceAll("ThirdXPos1", "him");
            output = output.replaceAll("ThirdXPos2", "her");
            output = output.replaceAll("ThirdYPos1", "himself");
            output = output.replaceAll("ThirdYPos2", "herself");

            return output;
        }


        if (gender1.equals("Girl") && gender2.equals("None")){
            output = output.replaceAll("ThirdPron2", "she");
            output = output.replaceAll("ThirdPron1", "she");
            output = output.replaceAll("ThirdPos2", "her");
            output = output.replaceAll("ThirdPos1", "her");
            output = output.replaceAll("ThirdXPos2", "her");
            output = output.replaceAll("ThirdXPos1", "her");
            output = output.replaceAll("ThirdYPos2", "herself");
            output = output.replaceAll("ThirdYPos1", "herself");

            return output;
        }


        if (gender1.equals("None") && gender2.equals("None")){
            output = output.replaceAll("ThirdPron2", "she");
            output = output.replaceAll("ThirdPron1", "he");
            output = output.replaceAll("ThirdPos2", "her");
            output = output.replaceAll("ThirdPos1", "his");
            output = output.replaceAll("ThirdXPos2", "her");
            output = output.replaceAll("ThirdXPos1", "him");
            output = output.replaceAll("ThirdYPos2", "herself");
            output = output.replaceAll("ThirdYPos1", "himself");

            return output;
        }

        return output;

    }
    */


}