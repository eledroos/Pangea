package com.wabsabi.pangea;

import com.parse.ParseUser;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity implements android.support.v7.app.ActionBar.TabListener{
    
    private ViewPager tabsviewPager;
    private ActionBar mActionBar;
    private TabsAdapter mTabsAdapter;
    
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //Navigate to LoginActivity if not logged in
		ParseUser currentUser = ParseUser.getCurrentUser();
		if(currentUser == null){		
			navigateToLogin();
		}
		else {
			Log.i(TAG, currentUser.getUsername());
		}

        
        tabsviewPager = (ViewPager) findViewById(R.id.tabspager);
        mTabsAdapter = new TabsAdapter(getSupportFragmentManager());
        
        tabsviewPager.setAdapter(mTabsAdapter);
        
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        
        Tab friendstab = getSupportActionBar().newTab().setText("Today").setTabListener(this);
        Tab publicprofiletab = getSupportActionBar().newTab().setText("Information").setTabListener(this);
        Tab bstab = getSupportActionBar().newTab().setText("Record").setTabListener(this);
        
        
        getSupportActionBar().addTab(friendstab);
        getSupportActionBar().addTab(publicprofiletab);
        getSupportActionBar().addTab(bstab);
        
        
        //This helps in providing swiping effect for v7 compat library 
        tabsviewPager.setOnPageChangeListener(new OnPageChangeListener() {
            
            @Override
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub
                  getSupportActionBar().setSelectedNavigationItem(position);
            }
            
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub
                
            }
        });
          
    }

    //Method for Logging in
	private void navigateToLogin() {
		Intent intent = new Intent(this, LoginActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //add flags so that LoginActivity is it's own parent activity
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK); //prevents going "back" to avoid login screen
		startActivity(intent);
	}
    
     @Override
    public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
        // TODO Auto-generated method stub
        
    }

     @Override
    public void onTabSelected(Tab selectedtab, FragmentTransaction arg1) {
        // TODO Auto-generated method stub
        tabsviewPager.setCurrentItem(selectedtab.getPosition()); //update tab position on tap
    }

     @Override
    public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
        // TODO Auto-generated method stub
        
    }
     

 	@Override
 	public boolean onCreateOptionsMenu(Menu menu) {
 		// Inflate the menu; this adds items to the action bar if it is present.
 		getMenuInflater().inflate(R.menu.main, menu);
 		return true;
 	}

 	@Override
 	public boolean onOptionsItemSelected(MenuItem item) {
 		// Handle action bar item clicks here. The action bar will
 		// automatically handle clicks on the Home/Up button, so long
 		// as you specify a parent activity in AndroidManifest.xml.
 		int id = item.getItemId();
 		if (id == R.id.action_logout) {
 			ParseUser.logOut();
 			navigateToLogin();
 		}
 		
 		
 		return super.onOptionsItemSelected(item);
 	}
}