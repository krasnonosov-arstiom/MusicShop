package com.example.musicshop;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    String subject = "Order from music shop";
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        setTitle("Your order");

        Intent recivedIntent = getIntent();
        String userName = recivedIntent.getStringExtra("userName");
        String goodsName = recivedIntent.getStringExtra("goodsName");
        int quantity = recivedIntent.getIntExtra("quantity", 0);
        double price = recivedIntent.getDoubleExtra("price", 0);

        TextView orderTextView = findViewById(R.id.orderTextView);

        message = "User name: " + userName + "\n"
                + "Goods name: " + goodsName + "\n"
                + "Quantity: " + quantity + "\n"
                + "Price: " + price;

        orderTextView.setText(message);
    }

    public void sendingOrder(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
