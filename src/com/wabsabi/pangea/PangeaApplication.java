package com.wabsabi.pangea;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class PangeaApplication extends Application {
	
	@Override
	public void onCreate()
	{
		// Enable Local Datastore
		Parse.enableLocalDatastore(this); 
		Parse.initialize(this, "Wd1RU6QzmnDos8WQVjNY2qqzwLRhSYLMtVrsu0xF", "nkmaxYeGTsTHMyxnhPw1E42erA0DN2RBTzmpwKFo");
	}

}

