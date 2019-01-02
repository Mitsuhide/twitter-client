package com.example.ttop.twitterclient;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;

import static com.example.ttop.twitterclient.Helper.addTimelineToListView;

public class MainActivity extends AppCompatActivity {

    public static final String USER = "DrChevre";

    ListView listView;
    Button searchButton;
    EditText searchEditText;
    ImageButton logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Context context = getApplicationContext();
        searchButton = findViewById(R.id.search_button);
        searchEditText = findViewById(R.id.search_edit_text);
        //final ConstraintLayout mainLayout = findViewById(R.id.main_constraint_layout);
        listView = findViewById(R.id.timeline_listview);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Editable user = searchEditText.getText();
                addTimelineToListView(listView, context, user.toString());
            }
        });

        logoutButton = findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        addTimelineToListView(listView, context, USER);
    }

    public void logout() {
        TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
        if (session != null) {
            TwitterCore.getInstance().getSessionManager().clearActiveSession();
        }
    }

}
