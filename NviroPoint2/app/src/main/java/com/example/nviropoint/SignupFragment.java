package com.example.nviropoint;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.nviropoint.R;



public class SignupFragment extends Fragment {



    private EditText email;
    private EditText confirmEmail;
    private EditText password;
    private EditText confirmPassword;
    private Boolean validEmail;

    public SignupFragment(){

    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_signup, container,false);
        email = (EditText) rootView.findViewById(R.id.emailin);
        confirmEmail = (EditText) rootView.findViewById(R.id.conEmail);
        password = (EditText) rootView.findViewById(R.id.pswrd);
        confirmPassword = (EditText) rootView.findViewById(R.id.conPswrd);
        return rootView;
    }

    public boolean isValidLoginCredentials(String email, String confirmEmail, String password, String confirmPassword){


        if(email == confirmEmail & password == confirmPassword){
            if (email.indexOf(".com")> -1 & email.indexOf("@") > -1){
                validEmail = true;
                return true;
            }else if (email.indexOf(".co.uk") > -1& email.indexOf("@") > -1){
                validEmail = true;
                return true;
            }else if(email.indexOf(".gov.uk") > -1& email.indexOf("@") > -1){
                validEmail = true;
                return true;
            }else{
                validEmail = false;
                return false;
            }
        }else if (email == confirmEmail & password != confirmPassword){
            return false;
        }else if(email != confirmEmail & password == confirmPassword){
            return false;
        }else{
            return false;
        }
    }


}
