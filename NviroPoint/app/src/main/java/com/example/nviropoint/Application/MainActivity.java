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
    private Button btn1,btn2,btn3,btn4,btn5,btn6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.login);
        btn4 = findViewById(R.id.signup);
        btn5 = findViewById(R.id.settings);
        btn6 = findViewById(R.id.events);
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

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(new SettingsFragment(), false, "one");
            }
        });

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
        ft.replace(R.id.container,fragment,tag);
        ft.commitAllowingStateLoss();

    }
}
