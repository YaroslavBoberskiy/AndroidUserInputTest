package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    int coffeePrice = 5;
    int creamPrice = 2;
    int chocolatePrice = 2;
    String customerName = "Your Name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayQtt(quantity);
        displayOrderInfo(coffeePrice*quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        displayOrderInfo(coffeePrice*quantity);
    }

    public void increment(View view) {
        if (quantity > 100) {
            Toast.makeText(this, "Please, order less than 100 cups.", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity++;
        displayQtt(quantity);
    }

    public void decrement(View view) {
        if (quantity <= 0) {
            Toast.makeText(this, "Please, order more than 0 cups.", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity--;
        displayQtt(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQtt(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.qttTv);
        quantityTextView.setText("" + number);
    }

    private boolean checkWhippedCream () {
        CheckBox whippedCreamTextBox = (CheckBox) findViewById(R.id.whippedCreamChBox);
        if (whippedCreamTextBox.isChecked()) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkChocolate () {
        CheckBox chocolateTextBox = (CheckBox) findViewById(R.id.chocolateChBox);
        if (chocolateTextBox.isChecked()) {
            return true;
        } else {
            return false;
        }
    }

    private String getCustomerName () {
        EditText customerNameEditText = (EditText) findViewById(R.id.nameET);
        customerName = customerNameEditText.getText().toString();
        return customerName;
    }

    private int getFinalPrice () {
        int finalPrice = 0;

        if (checkWhippedCream() == true) {
            finalPrice += creamPrice;
        }
        if (checkChocolate() == true) {
            finalPrice += chocolatePrice;
        }
        finalPrice = coffeePrice + finalPrice;
        return finalPrice*quantity;
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayOrderInfo(int number) {
        TextView orderInfoTextView = (TextView) findViewById(R.id.priceTv);
        String priceMessage = "Name: " + getCustomerName() + "\n" +
                "Add whiped cream ? " + checkWhippedCream() + "\n" +
                "Add chocolate ? " + checkChocolate() + "\n" +
                "Quantity: " + quantity + "\n" +
                "Total: " + NumberFormat.getCurrencyInstance().format(getFinalPrice()) + "\n" +
                "Thank You!";
        orderInfoTextView.setText(priceMessage);
    }

}