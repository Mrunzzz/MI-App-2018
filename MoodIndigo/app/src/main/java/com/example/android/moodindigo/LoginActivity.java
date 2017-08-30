package com.example.android.moodindigo;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.example.android.moodindigo.Fragments.MainFragment;
import com.example.android.moodindigo.data.RegistrationRequest;
import com.example.android.moodindigo.data.RegistrationResponse;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.login_button)
    LoginButton loginButton;
    private CallbackManager callbackManager;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;
    RetrofitClass rcinitiate;
    SearchInterface client;
    String fbid;
    RegistrationResponse registrationResponse=new RegistrationResponse();

    //Facebook login button
    private FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            Profile profile = Profile.getCurrentProfile();
            fbid=loginResult.getAccessToken().getUserId();
            nextActivity(profile);
        }
        @Override
        public void onCancel() {        }
        @Override
        public void onError(FacebookException e) {      }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(LoginActivity.this);




        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldToken, AccessToken newToken) {
            }
        };

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {
                nextActivity(newProfile);
            }
        };
        accessTokenTracker.startTracking();
        profileTracker.startTracking();


        callback = new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                AccessToken accessToken = loginResult.getAccessToken();
                Profile profile = Profile.getCurrentProfile();
                nextActivity(profile);
                fbid=loginResult.getAccessToken().getUserId();
                Toast.makeText(getApplicationContext(), "Logging in...", Toast.LENGTH_SHORT).show();    }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException e) {
            }
        };
        loginButton.setReadPermissions("user_friends");
        loginButton.setReadPermissions("user_email");
        loginButton.registerCallback(callbackManager, callback);


    }
    @Override
    protected void onResume() {
        super.onResume();
        //Facebook login
        Profile profile = Profile.getCurrentProfile();
        nextActivity(profile);
    }

    @Override
    protected void onPause() {

        super.onPause();
    }

    protected void onStop() {
        super.onStop();
        //Facebook login
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }




    private void nextActivity(final Profile profile){
        if(profile != null){


            rcinitiate=new RetrofitClass(LoginActivity.this);

            client =rcinitiate.createBuilder().create(SearchInterface.class);
            rcinitiate.startLogging();

            Call<RegistrationResponse> call=client.checkUserDetails(fbid);

            call.enqueue(new retrofit2.Callback<RegistrationResponse>() {
                @Override
                public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                    registrationResponse=response.body();

                    Intent main = new Intent(LoginActivity.this, MainActivity.class);
                    main.putExtra("name", registrationResponse.getName());
                    main.putExtra("email", registrationResponse.getEmail());
                    main.putExtra("imageUrl", profile.getProfilePictureUri(200,200).toString());
                    main.putExtra("mi number",registrationResponse.getMi_number());
                    main.putExtra("fbid",registrationResponse.getFb_id());
                    main.putExtra("college",registrationResponse.getPresent_college());
                    main.putExtra("city",registrationResponse.getPresent_city());
                    main.putExtra("address",registrationResponse.getPostal_address());
                    main.putExtra("zip",registrationResponse.getZip_code());
                    main.putExtra("mobile",registrationResponse.getMobile_number());
                    main.putExtra("year",registrationResponse.getYear_of_study());

                    startActivity(main);

                }

                @Override
                public void onFailure(Call<RegistrationResponse> call, Throwable t) {
                    Intent main = new Intent(LoginActivity.this, RegistrationActivity.class);
                    main.putExtra("name", profile.getFirstName()+profile.getLastName());
                    main.putExtra("imageUrl", profile.getProfilePictureUri(200,200).toString());

                }
            });
        }
    }

}
