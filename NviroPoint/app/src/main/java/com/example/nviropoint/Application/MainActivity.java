package com.example.nviropoint.Application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.nviropoint.Application.ForecastFragment;
import com.example.nviropoint.R;

public class MainActivity extends AppCompatActivity {
    private Button btn1,btn2,btn3,btn4,btn5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Learnt how to hide the notification bar at https://stackoverflow.com/questions/4222713/hide-notification-bar
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        /**
         * Most of this code is me practicing calling an API
         */

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.login);
        btn4 = findViewById(R.id.signupBtn);
        /**
         *  the parameter addToBackStack basically allows the programmer to add to the back stack so that the user is able to reverse
         *  the transaction and bring up the previous fragment by pressing the back button
         */
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(new MapFragment(), false, "one" );
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(new ForecastFragment(), false, "one");
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(new LoginFragment(), false, "one");
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(new SignupFragment(), false,"one");
            }
        });

        /*btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/

    }

    /**
     * Add fragment.
     *
     * @param fragment       the fragment
     * @param addToBackStack the add to back stack
     * @param tag            the tag                       This is a method that I have used to enlarge my fragments.                       I have put it into a method so that it is easier to call at a later date                       without having to constantly retype code.
     */
    public void addFragment(Fragment fragment, boolean addToBackStack, String tag){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();

        if(addToBackStack){
            ft.addToBackStack(tag);
        }
        ft.replace(R.id.container_frame_back,fragment,tag);
        ft.commitAllowingStateLoss();

    }
}
