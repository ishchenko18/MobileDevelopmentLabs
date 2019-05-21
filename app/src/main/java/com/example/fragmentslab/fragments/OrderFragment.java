package com.example.fragmentslab.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.fragmentslab.R;
import com.example.fragmentslab.dto.OrderDTO;

public class OrderFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);

        Spinner spinner = view.findViewById(R.id.pizzaList);
        EditText customerData = view.findViewById(R.id.personalDataField);
        EditText phoneData = view.findViewById(R.id.phoneField);
        CheckBox spicy = view.findViewById(R.id.isSpicy);
        CheckBox cocaCola = view.findViewById(R.id.addCola);

        String[] pizzaList = getResources().getStringArray(R.array.pizza_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, pizzaList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button button = view.findViewById(R.id.orderButton);
        button.setOnClickListener(view1 -> {
            String customer = customerData.getText().toString();
            String phoneNumber = phoneData.getText().toString();

            if (TextUtils.isEmpty(customer)) {
                customerData.setError("Дані замовника - обов'язкові!");
            } else if (TextUtils.isEmpty(phoneNumber)) {
                phoneData.setError("Номер замовника - обов'язковий!");
            } else {
                sendData(new OrderDTO(customer, phoneNumber, spinner.getSelectedItem().toString(), spicy.isChecked(), cocaCola.isChecked()));
            }

            customerData.setText("");
            phoneData.setText("");
            spicy.setChecked(false);
            cocaCola.setChecked(false);
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OrderFragment.OnFragmentInteractionListener) {
            mListener = (OrderFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public void sendData(OrderDTO order) {
        mListener.onFragmentInteraction(order);
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(OrderDTO order);
    }
}
