package com.example.frazzle.appforasc;

/**
 * Created by Frazzle on 07/01/2016.
 */
public class Character {

    private int _id;
    private String _name;
    private String _reward;
    private String _profileImagePath;
    private String _gender;

    public String get_gender() {
        return _gender;
    }

    public void set_gender(String _gender) {
        this._gender = _gender;
    }

    public String get_profileImagePath() {
        return _profileImagePath;
    }

    public void set_profileImagePath(String _profileImagePath) {
        this._profileImagePath = _profileImagePath;
    }

    public Character(String name, String reward, String path, String gender){
        this._name = name;
        this._reward = reward;
        this._profileImagePath = path;
        this._gender = gender;
    }

    public Character(String name, String reward, int id, String path, String gender){
        this._name = name;
        this._reward = reward;
        this._id = id;
        this._profileImagePath = path;
        this._gender = gender;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_reward() {
        return _reward;
    }

    public void set_reward(String _reward) {
        this._reward = _reward;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
