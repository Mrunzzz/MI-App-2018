
package com.example.android.moodindigo;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;
import android.content.pm.Signature;

import com.example.android.moodindigo.Fragments.MainFragment;
import com.example.android.moodindigo.data.RegistrationResponse;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


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
                nextActivity(profile);
                Toast.makeText(getApplicationContext(), "Logging in...", Toast.LENGTH_SHORT).show();    }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException e) {
            }
        };

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
            ButterKnife.bind(com.example.android.moodindigo.LoginActivity.this);




            FacebookSdk.sdkInitialize(getApplicationContext());
            AppEventsLogger.activateApp(getApplication());
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



            loginButton.setReadPermissions("user_friends");
            loginButton.registerCallback(callbackManager, callback);
            try {
                PackageInfo info = getPackageManager().getPackageInfo(
                        "com.example.android.moodindigo",
                        PackageManager.GET_SIGNATURES);
                for (Signature signature : info.signatures) {
                    MessageDigest md = MessageDigest.getInstance("SHA");
                    md.update(signature.toByteArray());
                    Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
                }
            } catch (PackageManager.NameNotFoundException e) {

            } catch (NoSuchAlgorithmException e) {

            }


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
            fbid=Profile.getCurrentProfile().getId();
            Log.i("fbid3",fbid);
            Call<RegistrationResponse> call=client.checkUserDetails(fbid);

            call.enqueue(new retrofit2.Callback<RegistrationResponse>() {
                @Override
                public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                    registrationResponse=response.body();

                    if(response.code()==200) {
                        Intent main = new Intent(LoginActivity.this, MainActivity.class);
                        main.putExtra("name", registrationResponse.getName());
                        main.putExtra("email", registrationResponse.getEmail());
                        main.putExtra("imageUrl", profile.getProfilePictureUri(200, 200).toString());
                        main.putExtra("mi number", registrationResponse.getMi_number());
                        main.putExtra("fbid", registrationResponse.getFb_id());
                        main.putExtra("college", registrationResponse.getPresent_college());
                        main.putExtra("city", registrationResponse.getPresent_city());
                        main.putExtra("address", registrationResponse.getPostal_address());
                        main.putExtra("zip", registrationResponse.getZip_code());
                        main.putExtra("mobile", registrationResponse.getMobile_number());
                        main.putExtra("year", registrationResponse.getYear_of_study());

                        startActivity(main);
                    }
                    else
                    {
                        Intent main = new Intent(LoginActivity.this, RegistrationActivity.class);
                        Log.i("fbid3",fbid);
                        main.putExtra("name", profile.getFirstName()+profile.getLastName());
                        main.putExtra("imageUrl", profile.getProfilePictureUri(200,200).toString());
                        main.putExtra("fbid",fbid);
                        startActivity(main);
                    }
                }

                @Override
                public void onFailure(Call<RegistrationResponse> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, "Connection failed", Toast.LENGTH_SHORT).show();
                   }
            });
        }
    }

}
