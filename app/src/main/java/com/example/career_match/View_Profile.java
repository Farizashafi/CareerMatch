package com.example.career_match;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class View_Profile extends AppCompatActivity {

    TextView t1,t2,t3,t4,t5,t6;
    ImageView img;
    Button b1;

    @Override
    public void onBackPressed() {
        Intent ij=new Intent(getApplicationContext(), Home.class);
        startActivity(ij);
    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        t1=findViewById(R.id.textView2);
        t2=findViewById(R.id.textView4);
        t3=findViewById(R.id.textView6);
        t4=findViewById(R.id.textView8);
        t5=findViewById(R.id.textView10);
        t6=findViewById(R.id.textView12);

        img=findViewById(R.id.imageView3);


        SharedPreferences sh =PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String url=sh.getString("url","")+"/view_user_profile";



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

                                        t1.setText(jsonObj.getString("name"));
                                        t2.setText(jsonObj.getString("email"));
                                        t3.setText(jsonObj.getString("phone"));
                                        t4.setText(jsonObj.getString("place"));
                                        t5.setText(jsonObj.getString("post"));
                                        t6.setText(jsonObj.getString("pin"));
                                        String image=jsonObj.getString("img");

                                        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                        String ip=sh.getString("ip","");
//
//                                        String url="http://" + ip + ":8000/"+image;
                                        String url=sh.getString("url","")+image;


                                        Picasso.with(getApplicationContext()).load(url).transform(new CircleTransform()). into(img);



//                                type=new String[js.length()];
//                                description=new String[js.length()];
//                                image=new String[js.length()];
//                                status=new String[js.length()];
//
//

                                        // ArrayAdapter<String> adpt=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,name);
                                        // l1.setAdapter(new Custom(getApplicationContext(),gamecode,name,type,discription,image,status));
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

                        String id=sh.getString("lid","");
                        params.put("lid",id);

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