/**
 * the log in form page
 */
package com.example.wallt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
 
public class LoginActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setting default screen to login.xml
        setContentView(R.layout.login_activity);
        final DataBaseManager db = new DataBaseManager(this);
        
        TextView login = (TextView) findViewById(R.id.btnLogin);
        
        final TextView userName = (TextView) findViewById(R.id.usernameField);
        final TextView passWord = (TextView) findViewById(R.id.passwordField);
        
        Intent extras = getIntent();
        Bundle b = extras.getExtras();
        if (b != null) {
        	userName.setText((String) b.get("registeredUserName"));
        }
        
        login.setOnClickListener(new View.OnClickListener() {
 
            public void onClick(View v) {
            	//this needs to be replaced with a SQL query
            	if (db.loginVerify(userName.getText().toString(), passWord.getText().toString())) {
	            	Intent i = new Intent(getApplicationContext(), MainHub.class);
	          	  	startActivity(i);
	          	  	finish();
            	} else {
            		passWord.setText("");
            		Intent i = new Intent(getApplicationContext(), LoginFail.class);
	          	  	startActivity(i);
            	}
            }
        });
 
        TextView back = (TextView) findViewById(R.id.backToMain);
 
        back.setOnClickListener(new View.OnClickListener() {
 
            public void onClick(View v) {
                // Switching back to login screen
                finish();
            }
        });
    }
    
    public void setUserName(String userName) {
    	final TextView userNameField = (TextView) findViewById(R.id.usernameField);
    	userNameField.setText(userName);
    }
    

}