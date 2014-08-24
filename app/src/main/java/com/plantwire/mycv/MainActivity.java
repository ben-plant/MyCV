package com.plantwire.mycv;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;


public class MainActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks,
        EmailFragment.EmailInterface {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    private FrameLayout mContainer;

    private FragmentTransaction emailTransaction;
    private EmailFragment mEmailFragment;
    private boolean showingEmail = false;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContainer = (FrameLayout)findViewById(R.id.container);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        mEmailFragment = new EmailFragment();
//        setUpEmailFragment();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

//    private void setUpEmailFragment() {
//        emailTransaction = getFragmentManager().beginTransaction();
//        emailTransaction.setCustomAnimations(R.anim.email_slide_in, R.anim.email_slide_out);
//        emailTransaction.replace(R.id.container, mEmailFragment);
//        //emailTransaction.detach(mEmailFragment);
//        emailTransaction.commit();
//    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_mail) {
            emailTransaction = getFragmentManager().beginTransaction();
            emailTransaction.setCustomAnimations(R.anim.email_slide_in, R.anim.email_slide_out);
            if (showingEmail)
            {
                emailTransaction.replace(R.id.container, new NullFragment());
                showingEmail = false;
            }
            else
            {
                emailTransaction.replace(R.id.container, mEmailFragment);
                showingEmail = true;
            }
            emailTransaction.commit();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {

    }

    @Override
    public void onEmailSend() {
        emailTransaction = getFragmentManager().beginTransaction();
        emailTransaction.setCustomAnimations(R.anim.email_slide_in, R.anim.email_slide_send);
        Toast.makeText(this, "Sending...", Toast.LENGTH_SHORT).show();
        emailTransaction.replace(R.id.container, new NullFragment());
        showingEmail = false;
        emailTransaction.commit();
        Toast.makeText(this, "Thanks!", Toast.LENGTH_SHORT).show();
    }
}
