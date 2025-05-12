package com.example.career_match;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
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

public class Custom_View_Vacancy extends BaseAdapter {
    String[]id,number,date,lastdate,category,details,status;

    private Context context;

    public Custom_View_Vacancy(Context applicationContext, String[] id, String[] number, String[] date, String[] lastdate, String[] category, String[] details, String[] status) {

        this.context=applicationContext;
        this.id=id;
        this.number = number;
        this.date = date;
        this.lastdate = lastdate;
        this.category = category;
        this.details = details;
        this.status = status;
    }


    @Override
    public int getCount() {
        return details.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(view==null)
        {
            gridView=new View(context);
            //gridView=inflator.inflate(R.layout.customview, null);
            gridView=inflator.inflate(R.layout.activity_custom_view_vacancy,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView t1=(TextView)gridView.findViewById(R.id.textView30);
        TextView t2=(TextView)gridView.findViewById(R.id.textView31);
        TextView t3=(TextView)gridView.findViewById(R.id.textView32);
        TextView t4=(TextView)gridView.findViewById(R.id.textView33);
        TextView t5=(TextView)gridView.findViewById(R.id.textView51);
        TextView t6=(TextView)gridView.findViewById(R.id.textView53);


        Button b1=(Button)gridView.findViewById(R.id.button15);
        if (status[i].equalsIgnoreCase("Applied")){
            b1.setVisibility(View.INVISIBLE);
        }
        b1.setTag(i);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sh=PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
                SharedPreferences.Editor ed=sh.edit();
                ed.putString("vid",id[i]);
                ed.commit();
                Intent i =new Intent(context.getApplicationContext(),applyjob.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int pos=(int)view.getTag();
//                SharedPreferences sh =PreferenceManager.getDefaultSharedPreferences(context);
//                String url=sh.getString("url","")+"/applyjob";
//                RequestQueue requestQueue = Volley.newRequestQueue(context);
//                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
//                        new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
//                                //  Toast.makeText(context, response, Toast.LENGTH_LONG).show();
//
//                                // response
//                                try {
//                                    JSONObject jsonObj = new JSONObject(response);
//                                    if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
//                                        Toast.makeText(context, "Applied successfully", Toast.LENGTH_SHORT).show();
//                                        Intent ij =new Intent(context, Home.class);
//                                        ij.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                        context.startActivity(ij);
//
//                                    }
//
//
//                                    // }
//                                    else {
//                                        Toast.makeText(context, "Already applied", Toast.LENGTH_LONG).show();
//                                    }
//
//                                }    catch (Exception e) {
//                                    Toast.makeText(context, "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                // error
//                                Toast.makeText(context, "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                ) {
//                    @Override
//                    protected Map<String, String> getParams() {
//                        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
//                        Map<String, String> params = new HashMap<String, String>();
//
//                        params.put("lid", sh.getString("lid", ""));
//                        params.put("vid", id[pos]);
//
//
//                        return params;
//                    }
//                };
//
//                int MY_SOCKET_TIMEOUT_MS=100000;
//
//                postRequest.setRetryPolicy(new DefaultRetryPolicy(
//                        MY_SOCKET_TIMEOUT_MS,
//                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//                requestQueue.add(postRequest);
//            }
//        });


        t1.setTextColor(Color.BLACK);
        t2.setTextColor(Color.BLACK);
        t3.setTextColor(Color.BLACK);
        t4.setTextColor(Color.BLACK);
        t5.setTextColor(Color.BLACK);


        t1.setText(number[i]);
        t2.setText(date[i]);
        t3.setText(lastdate[i]);
        t4.setText(category[i]);
        t5.setText(details[i]);
        t6.setText(status[i]);

        return gridView;
    }
}