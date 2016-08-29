package com.example.android.justjava3;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
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
        displayUnitPriceExtraWhippedCream();
        displayUnitPriceExtraChocolate();
        //avoid show up automatically the editText (input box)
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    double priceUnitOfCoffee = 1.15;
    double priceOfWhippedCream = 0.5;
    double priceOfChocolate = 1.0;
    int numberOfCoffees = 0;
    boolean hasWhippedCream;
    boolean hasChocolate;
    String nameClient;

    /**
     * This method return the total price of the bill
     */

    public double calculatePrice() {
        double basePrice = priceUnitOfCoffee;
        if (hasWhippedCream){ basePrice = basePrice + priceOfWhippedCream; }
        if (hasChocolate) {basePrice = basePrice + priceOfChocolate;}
        return basePrice * numberOfCoffees;
    }

    /**
     * This method return the total price of the bill
     */

    public String createOrderSummary() {
        DecimalFormat df = new DecimalFormat("#.##");
        return getString(R.string.order_summary_name, nameClient)
                + "\n" + getString(R.string.number_of_coffes, numberOfCoffees)
                + "\n" + getString(R.string.has_whipped_cream, hasWhippedCream)
                + "\n" + getString(R.string.has_chocolate, hasChocolate)
                + "\n" + getString(R.string.order_summary_price, df.format(calculatePrice())) + "€"
                + "\n" + getString(R.string.thank_you);
    }

    /**
     * This method is called when the order button is clicked.
     */

    public void submitOrder(View view) {
        //createOrderSummary();
        //update the status of checkboxes
        CheckBox whippedCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        hasWhippedCream = whippedCheckBox.isChecked();
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        hasChocolate = chocolateCheckBox.isChecked();
        //get the name of inputText
        TextView nameClientView = (TextView) findViewById(R.id.input_name);
        nameClient = nameClientView.getText().toString();
        //Log.v("MainActivity", "Has whipped cream: " + hasWhippedCream);
        //displayOrderSummary(createOrderSummary());
        DecimalFormat df = new DecimalFormat("#.##");
        displayOrderSummary(String.valueOf(df.format(calculatePrice())) + "€");


//        example of maps intent
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse("geo:47.9, -122.3"));
//        if (intent.resolveActivity(getPackageManager()) != null ){
//            startActivity(intent);
//        }

//        send and email
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.order_email_name, nameClient));
        //Log.v("MainActivity", "Message: " + createOrderSummary());
        intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary());
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * This method is called when the - button is clicked.
     */

    public void decrement(View view) {
        if (numberOfCoffees > 0) {
            numberOfCoffees--;
            display(numberOfCoffees);
        }
        else {
            Toast.makeText(this,"You cant have negative coffes", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * This method is called when the + button is clicked.
     */

    public void increment(View view) {
        if (numberOfCoffees >= 0 && numberOfCoffees < 100) {
            numberOfCoffees++;
            display(numberOfCoffees);
        }
        else {
            Toast.makeText(this,"You cant have more than 100 coffes", Toast.LENGTH_SHORT).show();
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
     * This method displays the order summary on the screen.
     */

    private void displayOrderSummary(String orderSummary) {
        TextView orderTextView = (TextView) findViewById(R.id.price_text_view);
        orderTextView.setText(orderSummary);
    }

    /**
     * This method displays the unit price of coffe on the screen.
     */

    private void displayUnitPrice() {
        TextView priceUnitTextView = (TextView) findViewById(R.id.price_unit_text_view);
        priceUnitTextView.setText(getString(R.string.unit_price_coffee) + NumberFormat.getCurrencyInstance(Locale.FRANCE).format(priceUnitOfCoffee));

    }

    /**
     * This method displays the unit price of extra whipped cream on the screen.
     */

    private void displayUnitPriceExtraWhippedCream() {
        TextView priceUnitWhippedCreamCheckbox = (TextView) findViewById(R.id.whipped_cream_checkbox);
        priceUnitWhippedCreamCheckbox.setText(getString(R.string.whipped_cream_text)
                + " "
                + NumberFormat.getCurrencyInstance(Locale.FRANCE).format(priceOfWhippedCream)
        + " extra");
    }

    /**
     * This method displays the unit price of extra chocolate on the screen.
     */

    private void displayUnitPriceExtraChocolate() {
        TextView priceUnitWhippedChocolateCheckbox = (TextView) findViewById(R.id.chocolate_checkbox);
        priceUnitWhippedChocolateCheckbox.setText("Chocolate "
                + NumberFormat.getCurrencyInstance(Locale.FRANCE).format(priceOfChocolate)
                + " extra");
    }
}
