package com.example.fragmentslab;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.example.fragmentslab.db.DatabaseHelper;
import com.example.fragmentslab.dto.OrderDTO;

public class OrdersActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private EditText orderIdField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_orders);

        databaseHelper = new DatabaseHelper(this);

        orderIdField = findViewById(R.id.orderIdField);
        Button searchButton = findViewById(R.id.searchOrderButton);

        searchButton.setOnClickListener(view -> {
            String orderId = orderIdField.getText().toString();

            if (TextUtils.isEmpty(orderId)) {
                orderIdField.setError("Введіть номер вашого замовлення!");
            }

            searchOrderById(orderId);
        });
    }

    @SuppressLint("Recycle")
    private void searchOrderById(String orderId) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor orderCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE + " where " +
                DatabaseHelper.COLUMN_ID + "=?", new String[]{String.valueOf(orderId)});

        if (orderCursor.moveToNext()) {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setCustomer(orderCursor.getString(1));
            orderDTO.setPhoneNumber(orderCursor.getString(2));
            orderDTO.setPizzaName(orderCursor.getString(3));
            orderDTO.setSpicy(orderCursor.getString(4).equalsIgnoreCase("Так"));
            orderDTO.setWithCocaCola(orderCursor.getString(5).equalsIgnoreCase("Додати колу"));

            showFoundAlert(orderId, orderDTO);
        } else {
            showNotFoundAlert();
        }
    }

    public void showNotFoundAlert() {
        new AlertDialog.Builder(this).setTitle("Результат пошуку")
                .setMessage("Замовлення з таким номером не існує!")
                .setNegativeButton("Ок", (dialog, id) -> dialog.cancel())
                .create()
                .show();
    }

    public void showFoundAlert(String orderId, OrderDTO order) {
        new AlertDialog.Builder(this).setTitle("Результат пошуку")
                .setMessage(String.format("Замовлення №%s\n" +
                        "Замовник: %s\n" +
                        "Піцца: %s\n" +
                        "Гостра: %s\n" +
                        "Кола: %s", orderId, order.getCustomer(), order.getPizzaName(),
                        order.getSpicyText(), order.getWithColaText()))
                .setNegativeButton("Ок", (dialog, id) -> dialog.cancel())
                .create()
                .show();
    }
}
