package com.nullcognition.rxandroidexamples.RXAndroidExamples;
/**
 * Created by ersin on 04/02/15 at 4:51 PM
 */


import android.app.Application;
import android.os.StrictMode;

public class SamplesApplication extends Application {

   @Override
   public void onCreate(){
	  super.onCreate();
	  StrictMode.enableDefaults();
   }
}
