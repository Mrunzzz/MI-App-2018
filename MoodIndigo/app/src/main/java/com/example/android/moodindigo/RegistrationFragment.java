package com.example.android.moodindigo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.moodindigo.data.RegistrationRequest;
import com.example.android.moodindigo.data.RegistrationResponse;

import java.util.List;

import javax.security.auth.callback.Callback;

import retrofit2.Call;
import retrofit2.Response;


public class RegistrationFragment extends Fragment {
    View view;

    RetrofitClass rcinitiate;
    RegistrationRequest registrationRequest;

    EditText et_name;
    EditText et_email;
    EditText et_mobile;
    EditText et_city;
    EditText et_college;
    EditText et_address;
    EditText et_zip;
    EditText et_year;

    long id;

    public RegistrationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view= inflater.inflate(R.layout.fragment_registration, container, false);

        registrationRequest.setEmail(et_email.getText().toString());
        registrationRequest.setName(et_name.getText().toString());
        registrationRequest.setMobile_number(Long.parseLong(et_mobile.getText().toString()));
        registrationRequest.setPresent_city(et_city.getText().toString());
        registrationRequest.setPresent_college(et_college.getText().toString());
        registrationRequest.setPostal_address(et_address.getText().toString());
        registrationRequest.setZip_code(Integer.parseInt(et_zip.getText().toString()));
        registrationRequest.setYear_of_study(et_year.getText().toString());



        rcinitiate=new RetrofitClass(getContext());

        SearchInterface client =rcinitiate.createBuilder().create(SearchInterface.class);
        rcinitiate.startLogging();

        Call<RegistrationResponse> call=client.fillRegistrationForm(registrationRequest);

        call.enqueue(new retrofit2.Callback<RegistrationResponse>() {
            @Override
            public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                //TODO(1) getResponse;
            }

            @Override
            public void onFailure(Call<RegistrationResponse> call, Throwable t) {
               //TODO(2) isme kya kare?
            }
        });


        return view;
    }


}
