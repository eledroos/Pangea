package com.wabsabi.pangea;


import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.VideoView;


public class VideoFragment extends Fragment {
	VideoView videoView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

    	View view = inflater.inflate(R.layout.video_layout, container, false);
    	videoView = (VideoView)view.findViewById(R.id.videoViewLoop);
        
    	ParseQuery<ParseObject> query = ParseQuery.getQuery("VideoObject");

		// Retrieve the object by id
		query.getInBackground("tx3hExzNxb", new GetCallback<ParseObject>() {
		  public void done(ParseObject video, ParseException e) {
		    if (e == null) {
		    	ParseFile videoFile = video.getParseFile("videoFile"); 
                String videoFileURL = videoFile.getUrl();
                videoView.setVideoPath(videoFileURL);
                videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                    @Override
                    public void onCompletion(MediaPlayer mp) {

                        videoView.start();  

                    }
                });
               videoView.start();  
            }
		
		    else{
		    	Toast.makeText(getActivity(), "Can't stream video",
						Toast.LENGTH_SHORT).show();
		    }
		  }
		});
       
       return view;
    }
}