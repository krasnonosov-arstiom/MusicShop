package com.example.musicshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    int i = 0;
    Spinner spinner;
    ArrayList spinnerArrayList;
    ArrayAdapter spinnerAdapter;
    HashMap goodsmap;
    String goodsItem;
    double price;
    EditText userNameEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameEditText = findViewById(R.id.user_name);

        createSpinner();

        createMap();
    }

    void createSpinner() {
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        spinnerArrayList = new ArrayList();
        spinnerArrayList.add("Guitar");
        spinnerArrayList.add("Drums");
        spinnerArrayList.add("Keyboard");

        spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }

    void createMap() {
        goodsmap = new HashMap();
        goodsmap.put("Guitar", 500.0);
        goodsmap.put("Drums", 1500.0);
        goodsmap.put("Keyboard", 1000.0);
    }

    public void increaseQuantity(View view) {
        TextView quantity = findViewById(R.id.quantity_choose);
        quantity.setText("" + ++i);
        TextView totalPrice = findViewById(R.id.totalPrice);
        totalPrice.setText("$" + i * price);
    }

    public void decreaseQuantity(View view) {
        TextView quantity = findViewById(R.id.quantity_choose);
        if (i > 0)
            quantity.setText("" + --i);
        TextView totalPrice = findViewById(R.id.totalPrice);
        totalPrice.setText("$" + i * price);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        goodsItem = spinner.getSelectedItem().toString();
        price = (double) goodsmap.get(goodsItem);
        i = 0;
        TextView quantity = findViewById(R.id.quantity_choose);
        quantity.setText("0");

        TextView totalPrice = findViewById(R.id.totalPrice);
        totalPrice.setText("$0.0");

        ImageView item = findViewById(R.id.item);

        switch (goodsItem) {
            case "Guitar":
                item.setImageResource(R.drawable.jackson);
                break;
            case "Drums":
                item.setImageResource(R.drawable.drums);
                break;
            case "Keyboard":
                item.setImageResource(R.drawable.keyboard);
                break;
            default:
                item.setImageResource(R.drawable.jackson);
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void addToCart(View view) {

        Order order = new Order();

        order.userName = userNameEditText.getText().toString();
        order.goodsName = goodsItem;
        order.quantity = i;
        order.price = i*price;

        Intent orderActivity = new Intent(MainActivity.this, OrderActivity.class);
        orderActivity.putExtra("userName", order.userName);
        orderActivity.putExtra("goodsName", order.goodsName);
        orderActivity.putExtra("quantity", order.quantity);
        orderActivity.putExtra("price", order.price);

        startActivity (orderActivity);
    }
}