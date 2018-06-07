package com.sharpinfo.sir.gestfly.action.user;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sharpinfo.sir.gestfly.R;
import com.sharpinfo.sir.gestfly.action.menu.MenuTechnicienActivity;
import com.sharpinfo.sir.gestfly.bean.Projet;
import com.sharpinfo.sir.gestfly.bean.Tache;
import com.sharpinfo.sir.gestfly.bean.Technicien_tache;
import com.sharpinfo.sir.gestfly.bean.User;
import com.sharpinfo.sir.gestfly.helper.Dispacher;
import com.sharpinfo.sir.gestfly.helper.Session;
import com.sharpinfo.sir.gestfly.reftroFitApi.ApiClient;
import com.sharpinfo.sir.gestfly.reftroFitApi.ApiInterface;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    EditText edtUsername;
    EditText edtPassword;
    TextView error;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "btn");

                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();
                Log.d(TAG, username + ";" + password);
                if (validateLogin(username, password)) {
                    //do login
                    executeApiCall(username, password);
                }

//                executeApiCall();
//                //DO THE FUCKING LOGIN SHIT HERE (username, password) .......
//                Dispacher.forward(LoginActivity.this, MenuTechnicienActivity.class);
//                finish();
            }
        });
    }

    private boolean validateLogin(String username, String password) {
        error = findViewById(R.id.errorMsgLogin);
        if (username == null || username.trim().length() == 0) {
            Toast.makeText(this, "Username is required", Toast.LENGTH_SHORT).show();
            error.setText(R.string.username_required);
            return false;
        }
        if (password == null || password.trim().length() == 0) {
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
            error.setText(R.string.password_required);
            return false;
        }
        return true;
    }

//    private void executeApiCall() {
//        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
//        Call<List<Projet>> call = apiInterface.getAllProjets();
//
//        call.enqueue(new Callback<List<Projet>>() {
//            @Override
//            public void onResponse(Call<List<Projet>> call, Response<List<Projet>> response) {
//                List<Projet> projets = response.body();
//                assert projets != null;
//                for (Projet projet : projets) {
//                    Log.d(TAG, projet.getNom());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Projet>> call, Throwable t) {
//
//            }
//        });
//    }

    private void executeApiCall(String username, String password) {
        Log.d(TAG, "executeApiCall");
        error = findViewById(R.id.errorMsgLogin);
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        retrofit2.Call<User> call = apiInterface.loginUser(username, password);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(retrofit2.Call<User> call, Response<User> response) {
                Log.d(TAG, "onresponse");
                if (response.body() == null) {
                    error.setText(R.string.passwor_or_username_incorrect);
                    Toast.makeText(LoginActivity.this, "Veuillez verifier vos identifiants",
                            Toast.LENGTH_SHORT).show();

                } else if (response.body() != null) {
//                    Toast.makeText(LoginActivity.this, response.body().getId() + ":" + response.body().getUsername() + ":" + response.body().getPassword(),
//                            Toast.LENGTH_SHORT).show();
                    Session.setAttribute(response.body(), "connectedUser");
                    Dispacher.forward(LoginActivity.this, MenuTechnicienActivity.class);
                    finish();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<User> call, Throwable t) {
                Log.d(TAG, "failure");
                t.printStackTrace();
                Log.d(TAG, t.toString());
                Toast.makeText(LoginActivity.this, "Verifiez votre connexion !",
                        Toast.LENGTH_SHORT).show();
//                if (t instanceof IOException) {
//                    Toast.makeText(LoginActivity.this, "this is an actual network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
//                    // logging probably not necessary
//                }
//                else {
//                    Toast.makeText(LoginActivity.this, "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
//                    // todo log to some central bug tracking service
//                }
            }
        });

    }

}
