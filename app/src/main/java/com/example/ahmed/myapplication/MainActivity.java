package com.example.ahmed.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Contact> list = new ArrayList<Contact>();

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                View myView = listView.getChildAt(position);

                TextView t1 = (TextView) myView.findViewById(R.id.textView2);

                String number = t1.getText().toString();

                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + number));
                startActivity(intent);

            }
        });

        list.add( new Contact("ahmed","010011",R.drawable.ic_launcher));
        list.add( new Contact("mohamed","012011",R.drawable.ic_launcher));
        list.add( new Contact("kamal","011011",R.drawable.ic_launcher));
        list.add( new Contact("eslam","010033",R.drawable.ic_launcher));

        MyAdapter adapter = new MyAdapter(list);

        listView.setAdapter(adapter);

    }


    public class MyAdapter extends BaseAdapter{

        ArrayList<Contact> Data = new ArrayList<Contact>();


        MyAdapter( ArrayList<Contact> list){

            Data =list;
        }

        @Override
        public int getCount() {

            return Data.size();
        }

        @Override
        public Contact getItem(int i) {

            return Data.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup parent) {

            LayoutInflater inflater =getLayoutInflater();

           View view = inflater.inflate(R.layout.listview_layout,parent,false);

            TextView t1 =(TextView) view.findViewById(R.id.textView);
            TextView t2 =(TextView) view.findViewById(R.id.textView2);

            ImageView image =(ImageView) view.findViewById(R.id.imageView);

            t1.setText( Data.get(i).name );
            t2.setText( Data.get(i).number );

            image.setImageResource(Data.get(i).imageID);

            return  view ;
        }
    }

}
