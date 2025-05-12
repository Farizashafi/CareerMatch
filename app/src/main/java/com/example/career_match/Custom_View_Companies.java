package com.example.career_match;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Custom_View_Companies extends BaseAdapter {
    String[]id,name,email,phone,latitude,longitude,description,pin,post,place,image;
    private Context context;

    public Custom_View_Companies(Context applicationContext, String[] id, String[] name, String[] email, String[] phone, String[] image, String[] latitude, String[] longitude, String[] description, String[] pin, String[] post, String[] place) {
        this.context=applicationContext;
        this.id=id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.pin = pin;
        this.post = post;
        this.place = place;
        this.image = image;
    }


    @Override
    public int getCount() {
        return name.length;
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
            gridView=inflator.inflate(R.layout.activity_custom_view_companies,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView name1=(TextView)gridView.findViewById(R.id.textView14);
        TextView email1=(TextView)gridView.findViewById(R.id.textView35);
        TextView phone1=(TextView)gridView.findViewById(R.id.textView39);
        TextView address1=(TextView)gridView.findViewById(R.id.textView41);
        TextView des1=(TextView)gridView.findViewById(R.id.textView43);
        TextView loc1=(TextView)gridView.findViewById(R.id.textView45);
        ImageView im=(ImageView) gridView.findViewById(R.id.imageView4);
        Button b1=(Button) gridView.findViewById(R.id.button4);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sh =PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
                SharedPreferences.Editor ed=sh.edit();
                ed.putString("cid",id[i]);
                ed.commit();
                Intent i =new Intent(context.getApplicationContext(),View_Vacancy.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
        loc1.setTag(i);
        loc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int ik=(int)view.getTag();
                String url = "http://maps.google.com/?q=" + latitude[ik] + "," + longitude[ik];
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,  Uri.parse(url));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });

        name1.setTextColor(Color.BLACK);
        email1.setTextColor(Color.BLACK);
        phone1.setTextColor(Color.BLACK);
        address1.setTextColor(Color.BLACK);
        des1.setTextColor(Color.BLACK);
        loc1.setTextColor(Color.BLACK);

        name1.setText(name[i]);
        email1.setText(email[i]);
        phone1.setText(phone[i]);
        address1.setText(place[i]+"\n"+pin[i]+"\n"+post[i]);

        des1.setText(description[i]);


        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
        String ip=sh.getString("ip","");

        String url="http://" + ip + ":8001"+image[i];


        Picasso.with(context).load(url).transform(new CircleTransform()). into(im);

        return gridView;
    }
}
