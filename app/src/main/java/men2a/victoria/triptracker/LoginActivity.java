package men2a.victoria.triptracker;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.BackendlessCallback;
import com.backendless.exceptions.BackendlessFault;

import java.util.Date;

public class LoginActivity extends AppCompatActivity {
    public EditText menter_email;
    public EditText menter_password;
    public EditText menter_name;
    public Button mlogin_button;
    public TextView msign_up_text;
    public Button msign_up_button;
    public TextView mcancel_signup_text;

    public static final String TAG = "LoginActivity";

    public static final String APP_ID = "35769F00-5808-FF13-FFE2-7DB6A2B2E200";
    public static final String SECRET_KEY = "37524751-BC9E-B6A3-FS86-9778E4776600";
    //public static final String SECRET_KEY = "73FBF8B3-52AB-E076-FF22-883E73EDF900";
    public static final String VERSION = "v1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        menter_email = (EditText) findViewById(R.id.enter_email);
        menter_password = (EditText) findViewById(R.id.enter_password);
        menter_name = (EditText) findViewById(R.id.enter_name);
        msign_up_button = (Button) findViewById(R.id.sign_up_button);
        msign_up_text = (TextView) findViewById(R.id.sign_up_text);
        mlogin_button = (Button) findViewById(R.id.login_button);
        mcancel_signup_text = (TextView) findViewById(R.id.cancel_signup_text);

        Backendless.initApp(this, APP_ID, SECRET_KEY, VERSION);

        mcancel_signup_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menter_name.setVisibility(View.GONE);
                 msign_up_button.setVisibility(View.GONE);
                mlogin_button.setVisibility(View.VISIBLE);
                msign_up_text.setText(R.string.sign_up_text);
            }
        });

        msign_up_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menter_name.setVisibility(View.VISIBLE);
                msign_up_button.setVisibility(View.VISIBLE);
                mlogin_button.setVisibility(View.GONE);
                msign_up_text.setText(R.string.cancel_signup_text);
            }
        });

        SignMeUpOnClickListener signMeUpListener = new SignMeUpOnClickListener();
        msign_up_button.setOnClickListener(signMeUpListener);

        MyLoginOnClickListener loginListener = new MyLoginOnClickListener();
        mlogin_button.setOnClickListener(loginListener);
    }

    private class SignMeUpOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            String userEmail = menter_email.getText().toString();
            String password = menter_password.getText().toString();
            String name = menter_name.getText().toString();

            userEmail = userEmail.trim();
            password = password.trim();
            name = name.trim();

            BackendlessUser user = new BackendlessUser();

            if (!userEmail.isEmpty() && !password.isEmpty() && !name.isEmpty()) {

             /* register the user in Backendless */
                user.setEmail(userEmail);
                user.setPassword(password);
                user.setProperty("name", name);

                Backendless.UserService.register(user, new BackendlessCallback<BackendlessUser>() {
                    @Override
                    public void handleResponse(BackendlessUser backendlessUser) {
                        Log.i(TAG, "Registration successful for " + backendlessUser.getEmail());
                        final ProgressDialog pDialog = ProgressDialog.show(LoginActivity.this, "Please Wait!", "Creating new account...", true);

                        Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Log.i(TAG, "Registration failed: " + fault.getMessage());
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                        builder.setMessage(fault.getMessage());
                    }
                });

            } else {
            /* warn the user of the problem */
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setMessage(R.string.empty_field_signup_error);
                builder.setTitle(R.string.authentication_error_title);
                builder.setPositiveButton(android.R.string.ok, null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }
    }

    private class MyLoginOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String userEmail = menter_email.getText().toString();
            String password = menter_password.getText().toString();

            userEmail = userEmail.trim();
            password = password.trim();

            BackendlessUser user = new BackendlessUser();

            if (!userEmail.isEmpty() && !password.isEmpty()) {
             /* login the user in Backendless */
                Backendless.UserService.login(userEmail, password, new BackendlessCallback<BackendlessUser>() {
                    @Override
                    public void handleResponse(BackendlessUser backendlessUser) {
                        Log.i(TAG, "Login successful for" + backendlessUser.getEmail());
                        final ProgressDialog pDialog = ProgressDialog.show(LoginActivity.this, "Please Wait!", "Accessing account...", true);

                        Intent intent = new Intent(LoginActivity.this, TripListActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Log.i(TAG, "Login failed: " + fault.getMessage());
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                        builder.setMessage(fault.getMessage());

                    }

                });
            } else {
            /* warn the user of the problem */
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setMessage(R.string.empty_field_login_error);
                builder.setTitle(R.string.authentication_error_title);
                builder.setPositiveButton(android.R.string.ok, null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }
    }


}


