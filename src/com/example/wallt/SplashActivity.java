/**
 * starting splash loading
 */
package com.example.wallt;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

public class SplashActivity extends Activity {
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.splash_activity);
     Thread splashThread = new Thread() {
        @Override
        public void run() {
           try {
              int waited = 0;
              while (waited < 5000) {
                 sleep(100);
                 waited += 100;
              }
           } catch (InterruptedException e) {
              // do nothing
           } finally {
              finish();
              Intent i = new Intent(SplashActivity.this,WelcomeActivity.class);
              i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
              startActivity(i);
           }
        }
     };
     splashThread.start();
  }
}