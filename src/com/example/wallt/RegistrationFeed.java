package com.example.wallt;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

public class RegistrationFeed extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registrationfeed_activity);
		DataBaseManager db = new DataBaseManager(this);
		ArrayList<ArrayList<Object>> list = db.getAllRowsAsArrays();
		StringBuilder str = new StringBuilder();
		for (ArrayList<Object> a : list) {
			str.append(a.toString());
		}
		final TextView teamName = (TextView) findViewById(R.id.databaseFeed);
		teamName.setText(str.toString());
	}

}
