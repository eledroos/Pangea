package com.wabsabi.pangea;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;


public class BSFragment extends Fragment {
	Button pw;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bs_layout, container, false);
        
        pw = (Button) view.findViewById(R.id.passwordButton);
        pw.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
            	Intent intent = new Intent(getActivity(), RecordVideo.class);
            	startActivity(intent);


            }
        });
         
        return view;
    }	
    
}