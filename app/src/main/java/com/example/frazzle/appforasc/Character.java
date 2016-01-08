package com.example.frazzle.appforasc;

/**
 * Created by Frazzle on 07/01/2016.
 */
public class Character {

    private int _id;
    private String _name;
    private String _reward;

    public Character(String name, String reward){
        this._name = name;
        this._reward = reward;
    }

    public Character(String name, String reward, int id){
        this._name = name;
        this._reward = reward;
        this._id = id;
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
