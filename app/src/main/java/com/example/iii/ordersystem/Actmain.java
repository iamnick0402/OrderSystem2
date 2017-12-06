package com.example.iii.ordersystem;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Actmain extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    private View.OnClickListener btnorder_click=new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            String previous=lbldrinks.getText()+""+sweetsSpinner.getSelectedItem()+"甜"+iceSpinner.getSelectedItem()+"冰"+editqua.getText()+"杯";
            result.setText(previous);

            SharedPreferences table = getSharedPreferences("T1", 0);
            SharedPreferences.Editor row = table.edit();
            row.putString("KK",previous).commit();


        }
    };
    private View.OnClickListener btnadd_click=new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            int qua=Integer.parseInt(editqua.getText().toString());
            editqua.setText(String.valueOf(qua+1));
        }
    };
    private View.OnClickListener btnless_click=new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            int qua=Integer.parseInt(editqua.getText().toString());
            editqua.setText(String.valueOf(qua-1));
        }
    };
    private View.OnClickListener btnenter_click=new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            int cash=Integer.parseInt(editcash.getText().toString());
            lblchange.setText(String.valueOf(cash-40*Integer.parseInt(editqua.getText().toString())));
        }
    };
    private View.OnClickListener btndetail_click=new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            SharedPreferences table = getSharedPreferences("T1", 0);
            String data = table.getString("KK","NODATA");
            result.setText(data);
        }
    };

    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        //設定選單內容
        super.onCreateContextMenu(contextMenu, view, menuInfo);
        contextMenu.add(0, 0, 0, "鮮奶茶");
        contextMenu.add(0, 1, 0, "多多");
        contextMenu.add(0, 2, 0, "冰淇淋紅茶");
        contextMenu.add(0, 3, 0, "阿華田");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        lbldrinks.setText(item.getTitle());
        return super.onContextItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actmain);
        InitialComponent();
        registerForContextMenu(drinks);
        sweetsSpinner.setOnItemSelectedListener(this); // 設定監聽選取事件
        iceSpinner.setOnItemSelectedListener(this); // 設定監聽選取事件
        order.setOnClickListener(btnorder_click);
        add.setOnClickListener(btnadd_click);
        less.setOnClickListener(btnless_click);
        enter.setOnClickListener(btnenter_click);
        detail.setOnClickListener(btndetail_click);

        final String[] sweets = {"正常", "八分", "半糖", "三分","一分","跟你一樣"};
        ArrayAdapter<String> sweet = new ArrayAdapter<>(Actmain.this,
                android.R.layout.simple_spinner_dropdown_item,
                sweets);
        sweetsSpinner.setAdapter(sweet);
        final String[] ice = {"正常", "八分", "五分", "三分","一分"};
        ArrayAdapter<String> temp = new ArrayAdapter<>(Actmain.this,
                android.R.layout.simple_spinner_dropdown_item,
                ice);
        iceSpinner.setAdapter(temp);
    }
    private void InitialComponent() {
        drinks=(TextView)findViewById(R.id.drinks);
        lbldrinks=(TextView)findViewById(R.id.lbldrinks);
        sweets=(TextView)findViewById(R.id.sweets);
        ice=(TextView)findViewById(R.id.ice);
        qua=(TextView)findViewById(R.id.qua);
        editqua=(EditText) findViewById(R.id.editqua);
        cash=(TextView)findViewById(R.id.cash);
        editcash=(EditText)findViewById(R.id.editcash);
        change=(TextView)findViewById(R.id.change);
        lblchange=(TextView)findViewById(R.id.lblchange);
        result=(TextView)findViewById(R.id.result);
        detail=(Button)findViewById(R.id.detail);
        enter=(Button)findViewById(R.id.enter);
        order=(Button)findViewById(R.id.order);
        sweetsSpinner=(Spinner)findViewById(R.id.sweetsSpinner);
        iceSpinner=(Spinner)findViewById(R.id.iceSpinner);
        add=(Button)findViewById(R.id.add);
        less=(Button)findViewById(R.id.less);
    }
    TextView drinks;
    TextView lbldrinks;
    TextView sweets;
    TextView ice;
    TextView qua;
    EditText editqua;
    TextView cash;
    EditText editcash;
    TextView change;
    TextView lblchange;
    TextView result;
    Button detail;
    Button enter;
    Button order;
    Spinner sweetsSpinner;
    Spinner iceSpinner;
    Button add;
    Button less;

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}