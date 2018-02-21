package com.example.dell.myapplication;

import java.util.List;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.support.annotation.NonNull;
import android.widget.Toast;
import android.support.v4.content.ContextCompat;

public class MainActivity extends Activity {

    TableLayout TabLayout_Show;
    final DatabaseHandler db = new DatabaseHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
        if(db.getContactsCount()<=0) {
            db.addContact(new Contact("Ravi", "9100000000"));
            db .addContact(new Contact("Srinivas", "9199999999"));
            db.addContact(new Contact("Tommy", "9522222222"));
            db.addContact(new Contact("Karthik", "9533333333"));
        }
        //addListenerOnButton();

    }

    public void displayData()
    {
        addHeaders();
        addData();
    }

    private TextView getTextView(int id, String title, int color, int typeface, int bgColor) {
        TextView tv = new TextView(this);
        tv.setId(id);
        tv.setText(title.toUpperCase());
        tv.setTextColor(color);
        tv.setPadding(40, 40, 40, 40);
        tv.setTypeface(Typeface.DEFAULT, typeface);
        tv.setBackgroundColor(bgColor);
        tv.setLayoutParams(getLayoutParams());
        return tv;
    }

    @NonNull
    private LayoutParams getLayoutParams() {
        LayoutParams params = new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        params.setMargins(2, 0, 0, 2);
        return params;
    }

    @NonNull
    private TableLayout.LayoutParams getTblLayoutParams() {
        return new TableLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
    }

    /**
     * This function add the headers to the table
     **/
    public void addHeaders() {
        TableLayout tl = findViewById(R.id.TableLayout);
        TableRow tr = new TableRow(this);
        tr.setLayoutParams(getLayoutParams());
        tr.addView(getTextView(0, "COMPANY", Color.WHITE, Typeface.BOLD, Color.BLUE));
        tr.addView(getTextView(0, "OS", Color.WHITE, Typeface.BOLD, Color.BLUE));
        tl.addView(tr, getTblLayoutParams());
    }

    /**
     * This function add the data to the table
     **/
    public void addData() {
        TableLayout tl = findViewById(R.id.TableLayout);
        List<Contact> contacts = db.getAllContacts();
        int numContacts = contacts.size();
        for (int i = 0; i < numContacts; i++) {
            TableRow tr = new TableRow(this);
            tr.setLayoutParams(getLayoutParams());
            tr.addView(getTextView(i + 1, contacts.get(i).getName(), Color.WHITE, Typeface.NORMAL, ContextCompat.getColor(this, R.color.colorAccent)));
            tr.addView(getTextView(i + numContacts, contacts.get(i).getPhoneNumber(), Color.WHITE, Typeface.NORMAL, ContextCompat.getColor(this, R.color.colorAccent)));
            tl.addView(tr, getTblLayoutParams());
        }
    }


    /*
    public void addListenerOnButton(){
        final Button button = findViewById(R.id.button_id);

        TabLayout_Show = (TableLayout) findViewById(R.id.TableLayout);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Reading all contacts
                Log.d("Reading: ", "Reading all contacts..");
                List<Contact> contacts = db.getAllContacts();

                for (Contact cn : contacts) {
                    String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " +
                            cn.getPhoneNumber();
                    // Writing Contacts to log
                    Log.d("Name: ", log);
                }

                for (int i = 0; i < numCompanies; i++) {
                    TableRow tr = new TableRow(this);
                    tr.setLayoutParams(getLayoutParams());
                    tr.addView(getTextView(i + 1, companies[i], Color.WHITE, Typeface.NORMAL, ContextCompat.getColor(this, R.color.colorAccent)));
                    tr.addView(getTextView(i + numCompanies, os[i], Color.WHITE, Typeface.NORMAL, ContextCompat.getColor(this, R.color.colorAccent)));
                    tl.addView(tr, getTblLayoutParams());
                }
            }
        });
    }
*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

}