package com.example.career_match;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class get_recommendation extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    EditText ed_comm_skill,ed_hours_per_day;
    Spinner sp;
    Button b, brec;
    RadioButton long_hour_y, long_hour_n, self_learn_y, self_learn_n, sal, work, teams_y, teams_n;

    String[] interested_careers={"System Developer", "Business Process Analyst", "Developers", "Testing", "Security", "Cloud computing"};
    String[] interested_careers_idx={"0", "1", "2", "3", "4", "5"};
    String sel_idx="";

    TextView tv_job;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_recommendation);

        ed_comm_skill=findViewById(R.id.editTextTextPersonName);
        ed_hours_per_day=findViewById(R.id.editTextTextPersonName2);
        long_hour_y=findViewById(R.id.radioButton);
        long_hour_n=findViewById(R.id.radioButton2);
        self_learn_y=findViewById(R.id.radioButton3);
        self_learn_n=findViewById(R.id.radioButton4);
        sal=findViewById(R.id.radioButton5);
        work=findViewById(R.id.radioButton6);
        teams_y=findViewById(R.id.radioButton7);
        teams_n=findViewById(R.id.radioButton8);
        tv_job=findViewById(R.id.textView3);
        tv_job.setVisibility(View.INVISIBLE);

        sp=findViewById(R.id.sp1);
        ArrayAdapter<String> ad=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, interested_careers);
        sp.setAdapter(ad);
        sp.setOnItemSelectedListener(this);

        b=findViewById(R.id.button);
        b.setOnClickListener(this);
        brec=findViewById(R.id.button8);
        brec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ij=new Intent(getApplicationContext(), View_Vacancy_rec.class);
                startActivity(ij);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        sel_idx = interested_careers_idx[i];
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        String comm=ed_comm_skill.getText().toString();
        String hours=ed_hours_per_day.getText().toString();

        String long_hours;
        if(long_hour_y.isChecked()){
            long_hours="1";
        } else {
            long_hours="0";
        }

        String self_learn;
        if(self_learn_y.isChecked()){
            self_learn="1";
        } else {
            self_learn="0";
        }

        String priority;
        if(work.isChecked()){
            priority="1";
        } else {
            priority="0";
        }

        String teams;
        if(teams_y.isChecked()){
            teams="1";
        } else {
            teams="0";
        }

        if(comm.equals("")){
            ed_comm_skill.setError("Required");
        } else if(hours.equals("")){
            ed_hours_per_day.setError("Required");
        } else {
            SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String hu = sh.getString("url", "");
            String url = hu + "/and_get_recommendation";

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            String finalLong_hours = long_hours;
            StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                            // response
                            try {
                                JSONObject jsonObj = new JSONObject(response);
                                if (jsonObj.getString("status").equalsIgnoreCase("ok")) {

                                    tv_job.setVisibility(View.VISIBLE);
                                    tv_job.setText(jsonObj.getString("data"));
                                    SharedPreferences sh=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                    SharedPreferences.Editor ed=sh.edit();
                                    ed.putString("job_role", jsonObj.getString("data"));
                                    ed.commit();
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

                    params.put("comm", comm);
                    params.put("hours", hours);
                    params.put("long_hours", long_hours);
                    params.put("self_learn", self_learn);
                    params.put("priority", priority);
                    params.put("teams", teams);
                    params.put("sel_idx", sel_idx);

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
}