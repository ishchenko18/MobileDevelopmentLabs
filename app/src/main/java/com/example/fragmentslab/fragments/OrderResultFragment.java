package com.example.fragmentslab.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.fragmentslab.OrdersActivity;
import com.example.fragmentslab.R;
import com.example.fragmentslab.db.DatabaseHelper;
import com.example.fragmentslab.dto.OrderDTO;

public class OrderResultFragment extends Fragment {

    private TextView phone;
    private TextView customer;
    private TextView pizzaName;
    private TextView isSpicy;
    private TextView addCola;
    private Button acceptButton;
    private Context myContext;
    private DatabaseHelper dbHelper;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        myContext = context;
        dbHelper = new DatabaseHelper(context);
    }

    @SuppressLint("CutPasteId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_result, container, false);
        phone = view.findViewById(R.id.phoneData);
        customer = view.findViewById(R.id.customerData);
        pizzaName = view.findViewById(R.id.pizzaTypeData);
        isSpicy = view.findViewById(R.id.spicyData);
        addCola = view.findViewById(R.id.colaData);

        acceptButton = view.findViewById(R.id.submitButton);
        acceptButton.setOnClickListener(view1 -> {
            long orderId = save();

            showAlert(orderId);

            acceptButton.setEnabled(false);
            phone.setText("");
            customer.setText("");
            pizzaName.setText("");
            isSpicy.setText("");
            addCola.setText("");
        });

        return view;
    }

    private long save() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_CUSTOMER, customer.getText().toString());
        cv.put(DatabaseHelper.COLUMN_PHONE_NUMBER, phone.getText().toString());
        cv.put(DatabaseHelper.COLUMN_PIZZA_NAME, pizzaName.getText().toString());
        cv.put(DatabaseHelper.COLUMN_SPICY, isSpicy.getText().toString());
        cv.put(DatabaseHelper.COLUMN_ADD_COLA, addCola.getText().toString());

        long id = db.insert(DatabaseHelper.TABLE, null, cv);
        db.close();

        return id;
    }

    private void showAlert(long orderId) {
        AlertDialog alertDialog = new AlertDialog.Builder(myContext)
                .setTitle("Замовлення створено")
                .setMessage(String.format("Номер вашого замовлення: %s", orderId))
                .setPositiveButton("Переглянути", (dialog, id) -> startActivity(new Intent(myContext, OrdersActivity.class)))
                .setNegativeButton("Дякую", (dialog, id) -> dialog.cancel())
                .create();

        alertDialog.show();
    }

    public void fillData(OrderDTO orderDTO) {
        customer.setText(orderDTO.getCustomer());
        phone.setText(orderDTO.getPhoneNumber());
        pizzaName.setText(orderDTO.getPizzaName());
        isSpicy.setText(orderDTO.getSpicyText());
        addCola.setText(orderDTO.getWithColaText());
        acceptButton.setEnabled(true);
    }
}
