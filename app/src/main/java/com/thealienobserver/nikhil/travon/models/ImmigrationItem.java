package com.thealienobserver.nikhil.travon.models;

import android.widget.ListAdapter;

import java.util.ArrayList;

public class ImmigrationItem
{
    private String name;
    private String description;

    public ImmigrationItem(String name,String description)
        {
        this.name = name;
        this.description = description;
        }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }
}



