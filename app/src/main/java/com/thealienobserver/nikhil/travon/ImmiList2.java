package com.thealienobserver.nikhil.travon;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ImmiList2 extends ImmNav1Screen  {
    // Array of strings...
    String[] forms = {"Nova Scotia ID Form",
                      "PR Form",
                      "Express Entry Form"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immig2_screen);

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, forms);

        ListView listView = (ListView) findViewById(R.id.forms);
        listView.setAdapter(adapter);
    }
}
