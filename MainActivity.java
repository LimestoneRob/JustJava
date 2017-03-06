/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 */

package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 0;
    String userName = "Name ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
//        userNameTextBox
        EditText userEnteredName = (EditText) findViewById(R.id.userNameTextBox);
        userName = userEnteredName.getText().toString();

//        Whipped Cream topping
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whippedCreamCheckBox);
        Boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

//        chocolateCheckBox
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolateCheckBox);
        Boolean hasChocolate = chocolateCheckBox.isChecked();
        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String priceMessage = createOrderSummary(userName, price, hasWhippedCream, hasChocolate);
//        displayMessage(priceMessage);
//    }

        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto:", "robert.riley65@gnmail.com", null));
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"robert.riley65@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java Order for " + userName);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * This method calculates the price of the total order
     * returns an INT
     *
     * @pram addWhippedCream is weather the user wants Whipped Cream topping
     * @pram addChocolate is weather the user wants Chocolate topping
     */
    private int calculatePrice(Boolean addWhippedCream, Boolean addChocolate) {
        //base price of 1 cup of coffee
        int basePrice = 5;

        // Add 1$ if whippedCream
        if (addWhippedCream) {
            basePrice = basePrice + 1;
        }

        // Add 2$ if Chocolate
        if (addChocolate) {
            basePrice = basePrice + 2;
        }

        //Calculate the total order price by muliplying by quantity
        return basePrice * quantity;
    }

    /**
     * This method creates the order summary
     *
     * @return text summary
     * @pram price of the order
     * @pram addWhippedCream is weather or not tht user wants whipped cream topping
     * @pram addChocolate is weather or not tht user wants whipped cream topping
     */
    private String createOrderSummary(String userName, int price, Boolean addWhippedCream, Boolean addChocolate) {
//        String priceMessage = getString(R.string.order_summary_name, userName);
        String priceMessage = "Name: " + userName;
//        String priceMessage = <xliff:g id="java_Name" example="Rob">%s</xliff:g></string>;
        priceMessage += "\n" + getString(R.string.add_whipped_cream) + addWhippedCream;
        priceMessage += "\n" + getString(R.string.add_chocolate) + addChocolate;
        priceMessage += "\n" + getString(R.string.java_quantiry) + quantity;
        priceMessage += "\n" + getString(R.string.java_total) + price;
        priceMessage += "\n" + getString(R.string.java_thank_you);
        return priceMessage;
    }

    /**
     * This method updates the quantity on the screen by one.
     */
    public void increment(View view) {
        if (quantity == 100) {
            // Show error message as a toast
            Toast.makeText(this, R.string.java_100_limit, Toast.LENGTH_SHORT).show();
            //Exit this method early
            return;
        }
        quantity = quantity + 1;
        display(quantity);
    }

    /**
     * This method updates the quantity on the screen by negative one.
     */
    public void decrement(View view) {
        if (quantity == 1) {
            // Show error message as a toast
            Toast.makeText(this, R.string.java_1_minimum, Toast.LENGTH_SHORT).show();
            //Exit this method early
            return;
        }
        quantity = quantity - 1;
        display(quantity);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
        /**
         *  priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
         */
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
//
//    /**
//     * ATTENTION: This was auto-generated to implement the App Indexing API.
//     * See https://g.co/AppIndexing/AndroidStudio for more information.
//     */
//    public Action getIndexApiAction() {
//        Thing object = new Thing.Builder()
//                .setName("Main Page") // TODO: Define a title for the content shown.
//                // TODO: Make sure this auto-generated URL is correct.
//                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
//                .build();
//        return new Action.Builder(Action.TYPE_VIEW)
//                .setObject(object)
//                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
//                .build();
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//
//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        client.connect();
//        AppIndex.AppIndexApi.start(client, getIndexApiAction());
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//
//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        AppIndex.AppIndexApi.end(client, getIndexApiAction());
//        client.disconnect();
//    }
}