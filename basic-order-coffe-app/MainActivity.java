package com.example.android.justjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * This app displays an order form to order coffee.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayUnitPrice();
    }

    int priceUnitOfCoffee = 5;

    /**
     * This method is called when the order button is clicked.
     */

    int numberOfCoffees = 0;

    public void submitOrder(View view) {
        displayPrice( priceUnitOfCoffee * numberOfCoffees);

    }

    /**
     * This method is called when the - button is clicked.
     */

    public void decrement(View view) {
        if (numberOfCoffees > 0) {
            numberOfCoffees --;
            display(numberOfCoffees);
        }
    }

    /**
     * This method is called when the + button is clicked.
     */

    public void increment(View view) {
        if (numberOfCoffees >= 0) {
            numberOfCoffees ++;
            display(numberOfCoffees);
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */

    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance(Locale.FRANCE).format(number));
    }

    /**
     * This method displays the unit price of coffe on the screen.
     */

    private void displayUnitPrice() {
        TextView priceUnitTextView = (TextView) findViewById(R.id.price_unit_text_view);
        priceUnitTextView.setText("1 COFFEE = " + NumberFormat.getCurrencyInstance(Locale.FRANCE).format(priceUnitOfCoffee));

    }


}
