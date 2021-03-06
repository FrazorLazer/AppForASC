package com.example.frazzle.appforasc;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adapters.MyFragmentPagerAdapter;
import fragments.Fragment1;
import fragments.Fragment1Alt;
import fragments.Fragment2;
import fragments.Fragment2Alt;
import fragments.Fragment3;
import fragments.Fragment3Alt;

public class StoryActivity extends AppCompatActivity implements
        Fragment1.Fragment1Listener,
        Fragment2.Fragment2Listener,
        Fragment3.Fragment3Listener,
        Fragment1Alt.Fragment1AltListener,
        Fragment2Alt.Fragment2AltListener,
        Fragment3Alt.Fragment3AltListener{

    MyFragmentPagerAdapter mDemoCollectionPagerAdapter;
    ViewPager mViewPager;
    List<Fragment> fragmentList = new ArrayList<>();
    Character c1;
    Character c2;
    int whoseGuessing;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        String storyFormat = ((ExtendedApp) getApplication()).getStoryFormat();
        c1 = ((ExtendedApp) getApplication()).getCharacterOne();
        c2 = ((ExtendedApp) getApplication()).getCharacterTwo();
        whoseGuessing = findSequence();

        if (storyFormat.equals("Options")){
            setUpFragments(whoseGuessing);
        }else{
            setUpAltFragments(whoseGuessing);
        }

        mDemoCollectionPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mDemoCollectionPagerAdapter);
        mViewPager.setCurrentItem(1);


    }

    public void setUpFragments(int sequence){

        String colourString = getColourString();

        Fragment1 frag1 = new Fragment1();
        Bundle args1 = new Bundle();
        args1.putString("story", findTheString2("Story"));
        args1.putString("colourString", colourString);
        args1.putString("pathname1", c1.get_profileImagePath());
        args1.putString("pathname2", c2.get_profileImagePath());
        args1.putString("name1", c1.get_name());
        args1.putString("name2", c2.get_name());
        args1.putString("progress", Integer.toString(((ExtendedApp) getApplication()).getStoryProgress()));
        args1.putInt("orientation", sequence);
        frag1.setArguments(args1);

        Fragment2 frag2 = new Fragment2();
        Bundle args2 = new Bundle();
        args2.putString("act", findTheString2("Act"));
        args2.putString("colourString", colourString);
        args2.putString("videoName", findTheString2("Video"));
        if (sequence == 1){
            args2.putString("orientation", "right");
        }else{
            args2.putString("orientation", "left");
        }
        frag2.setArguments(args2);

        Fragment3 frag3 = new Fragment3();
        Bundle args3 = new Bundle();
        args3.putString("guess", findTheString2("Guess"));
        args3.putString("colourString", colourString);
        args3.putInt("answer", findTheAnswer());
        args3.putStringArray("options", findTheOptions());
        if (sequence == 1){
            args3.putString("orientation", "left");
        }else{
            args3.putString("orientation", "right");
        }
        frag3.setArguments(args3);


        if (sequence == 1) {
            fragmentList.add(frag3);
            fragmentList.add(frag1);
            fragmentList.add(frag2);
        }else{
            fragmentList.add(frag2);
            fragmentList.add(frag1);
            fragmentList.add(frag3);
        }

    }

    public void setUpAltFragments(int sequence){

        String colourString = getColourString();

        Fragment1Alt frag1 = new Fragment1Alt();
        Bundle args1 = new Bundle();
        args1.putString("story", findTheString2("Story"));
        args1.putString("colourString", colourString);
        args1.putString("pathname1", c1.get_profileImagePath());
        args1.putString("pathname2", c2.get_profileImagePath());
        args1.putString("name1", c1.get_name());
        args1.putString("name2", c2.get_name());
        args1.putString("progress", Integer.toString(((ExtendedApp) getApplication()).getStoryProgress()));
        args1.putInt("orientation", sequence);
        frag1.setArguments(args1);

        Fragment2Alt frag2 = new Fragment2Alt();
        Bundle args2 = new Bundle();
        args2.putString("colourString", colourString);
        args2.putString("act", findTheString2("Act"));
        args2.putStringArray("ideas", findTheIdeas());
        if (sequence == 1){
            args2.putString("orientation", "right");
        }else{
            args2.putString("orientation", "left");
        }
        frag2.setArguments(args2);


        Fragment3Alt frag3 = new Fragment3Alt();
        Bundle args3 = new Bundle();
        args3.putString("colourString", colourString);
        args3.putString("guess", findTheString2("Guess"));
        args3.putString("hint", findTheString2("Hint"));
        if (sequence == 1){
            args3.putString("orientation", "left");
        }else{
            args3.putString("orientation", "right");
        }
        frag3.setArguments(args3);


        if (sequence == 1) {
            fragmentList.add(frag3);
            fragmentList.add(frag1);
            fragmentList.add(frag2);
        }else{
            fragmentList.add(frag2);
            fragmentList.add(frag1);
            fragmentList.add(frag3);
        }

    }


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
        ((ExtendedApp) getApplication()).setInTutorial(false);
        ((ExtendedApp) getApplication()).setStoryFormat("Options");
        ((ExtendedApp) getApplication()).setStory("Sleepover");


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
        boolean tut = ((ExtendedApp) getApplication()).isInTutorial();

        if (tut && progress > 3){
            ((ExtendedApp) getApplication()).incrementStoryProgress();
            Intent i2 = new Intent(this, EndStoryActivity.class);
            i2.putExtra("lastLine", findTheString2("Story"));
            startActivity(i2);
            return;
        }

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


/*
    public void setBackgroundColour() {
        SharedPreferences sharedPref = getSharedPreferences("colourInfo", Context.MODE_PRIVATE);
        String backgroundColour = "Background" + sharedPref.getString("backgroundC", "");
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.storyAct);
        int colourID = ((ExtendedApp) getApplication()).getColorResourceId(backgroundColour);
        int colour = new ResourcesCompat().getColor(getResources(), colourID, null);
        layout.setBackgroundColor(colour);
    }

    */

    public int getBackgroundColour() {
        SharedPreferences sharedPref = getSharedPreferences("colourInfo", Context.MODE_PRIVATE);
        String backgroundColour = "Background" + sharedPref.getString("backgroundC", "");
        //RelativeLayout layout = (RelativeLayout) findViewById(R.id.storyAct);
        int colourID = ((ExtendedApp) getApplication()).getColorResourceId(backgroundColour);
        int c = new ResourcesCompat().getColor(getResources(), colourID, null);
        return c;

    }


    public String getColourString() {
        SharedPreferences sharedPref = getSharedPreferences("colourInfo", Context.MODE_PRIVATE);
        String backgroundColour = sharedPref.getString("backgroundC", "");
        return backgroundColour;

    }



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
}
