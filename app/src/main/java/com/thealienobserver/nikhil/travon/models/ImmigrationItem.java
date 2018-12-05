package com.thealienobserver.nikhil.travon.models;

public class ImmigrationItem
{
    //API components
    private String name;
    private String description;

    public ImmigrationItem(String name,String description)
        {
            //Setting name and description
        this.name = name;
        this.description = description;

        }

    //Getting name and description
        public String getName()
    {
        return name;
    }

       public String getDescription()
    {
        return description;
    }
}



