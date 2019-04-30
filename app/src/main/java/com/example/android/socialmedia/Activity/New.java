package com.example.android.socialmedia.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.android.socialmedia.Fragment.DataFragment;
import com.example.android.socialmedia.Fragment.NotifactionFragment;
import com.example.android.socialmedia.R;

public class New extends AppCompatActivity {
    public String fi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        SharedPreferences shared = getSharedPreferences("A", Context.MODE_PRIVATE);   // get the sharedpreference set named "A"
        fi = shared.getString("FIELD", "");
        NotifactionFragment dataFragment = new NotifactionFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_data, dataFragment).commit();


    }

}
