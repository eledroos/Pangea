package com.wabsabi.pangea;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ProgressCallback;
import com.parse.SaveCallback;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;


public class VideoLoop extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loop);

        VideoView videoView = (VideoView)findViewById(R.id.videoViewLoop);
        //MediaController mediaController = new MediaController(this);
        // mediaController.setAnchorView(videoView);
        //videoView.setMediaController(mediaController);

        videoView.setVideoPath("/sdcard/myvideo.mp4");
        videoView.setOnPreparedListener (new OnPreparedListener() {  
          @Override
         public void onPrepared(MediaPlayer mp) {
               mp.setLooping(true);
            }
       });
       videoView.start();  
    }
    
    public void submit(View view){
   
    	ParseQuery<ParseObject> query = ParseQuery.getQuery("VideoObject");

		// Retrieve the object by id
		query.getInBackground("tx3hExzNxb", new GetCallback<ParseObject>() {
		  public void done(ParseObject video, ParseException e) {
		    if (e == null) {
		      // Now let's update it with some new data. In this case, only cheatMode and score
		      // will get sent to the Parse Cloud. playerName hasn't changed.
		    	File inputFile = new File("/sdcard/myvideo.mp4");
				FileInputStream fis;
				try {
					fis = new FileInputStream(inputFile);
				
				ByteArrayOutputStream bos= new ByteArrayOutputStream();
				byte[] buf = new byte[(int)inputFile.length()];
				try{
				      for (int readNum; (readNum=fis.read(buf)) != -1;){
				      bos.write(buf,0,readNum);
				      }
				}
				catch (IOException ex) {
				       Toast.makeText(VideoLoop.this,
				       "Error conerting into byte: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
				}
				byte[] bytes = bos.toByteArray();
				
				
				/*upload into Parse*/
			  ParseFile videoFile = new ParseFile ("video.mp4", bytes);
			  videoFile.saveInBackground();
		      video.put("videoFile", videoFile);
		      video.saveInBackground();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    }
		  }
		});
		
		 new CountDownTimer(17000, 2000) {

		     public void onTick(long millisUntilFinished) {
		    	 Toast.makeText(VideoLoop.this, "Video is Uploading",
		 				Toast.LENGTH_SHORT).show();
		     }

		     public void onFinish() {
		    	 Toast.makeText(VideoLoop.this, "Upload Complete",
		 				Toast.LENGTH_LONG).show();
		    	 Intent intent = new Intent(VideoLoop.this, MainActivity.class);
		 		startActivity(intent);
		 		finish();
		 		
		     }
		  }.start();
		  
	}
    
    public void exit(View view){
    	Intent intent = new Intent(VideoLoop.this, RecordVideo.class);
		startActivity(intent);
    }
}