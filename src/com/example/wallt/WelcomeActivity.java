/**
 * welcome screen with choice of login or register
 */
package com.example.wallt;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
 
public class WelcomeActivity extends Activity {
	
   /** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.welcome_activity);
      
      TextView loginScreen = (TextView) findViewById(R.id.link_login);
      
      // Listener for Login button
      loginScreen.setOnClickListener(new View.OnClickListener() {

          public void onClick(View v) {
        	  Intent i = new Intent(getApplicationContext(), LoginActivity.class);
        	  startActivity(i);
          }
      });
      
      TextView registerScreen = (TextView) findViewById(R.id.link_Register);
      
      // Listener for Register button
      registerScreen.setOnClickListener(new View.OnClickListener() {

          public void onClick(View v) {
        	  Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
        	  startActivity(i);
          }
      });
     }
}
