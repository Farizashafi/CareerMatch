package com.example.career_match;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class View_Companies extends AppCompatActivity {
    String[]id,name,email,phone,latitude,longitude,description,pin,post,place,image;
    ListView l1;
    String url;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_companies);

        l1=findViewById(R.id.comlistview);

        SharedPreferences sh =PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String url=sh.getString("url","")+"/view_companies_user";

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        // response
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {

                                JSONArray js= jsonObj.getJSONArray("data");

                                id=new String[js.length()];
                                name=new String[js.length()];
                                email=new String[js.length()];
                                phone=new String[js.length()];
                                image=new String[js.length()];
                                latitude=new String[js.length()];
                                longitude=new String[js.length()];
                                description=new String[js.length()];
                                pin=new String[js.length()];
                                post=new String[js.length()];
                                place=new String[js.length()];
//

                                for(int i=0;i<js.length();i++)
                                {
                                    JSONObject u=js.getJSONObject(i);
                                    id[i]=u.getString("id");
                                    name[i]=u.getString("name");
                                    email[i]=u.getString("email");
                                    phone[i]=u.getString("phone");
                                    image[i]=u.getString("image");
                                    latitude[i]=u.getString("latitude");
                                    longitude[i]=u.getString("longitude");
                                    description[i]=u.getString("description");
                                    place[i] = u.getString("place") ;
                                    post[i] = u.getString("post");
                                    pin[i] = u.getString("pin");
//

                                }


                                l1.setAdapter(new Custom_View_Companies(getApplicationContext(),id,name,email,phone,image,latitude,longitude,description,pin,post,place));
                            }


                            // }
                            else {
                                Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
                            }

                        }    catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                Map<String, String> params = new HashMap<String, String>();

                String id=sh.getString("uid","");
                params.put("uid",id);
//                params.put("mac",maclis);

                return params;
            }
        };

        int MY_SOCKET_TIMEOUT_MS=100000;

        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(postRequest);
    }
}