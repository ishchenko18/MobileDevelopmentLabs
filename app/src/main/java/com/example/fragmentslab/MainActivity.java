package com.example.fragmentslab;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.fragmentslab.dto.OrderDTO;
import com.example.fragmentslab.fragments.OrderFragment;
import com.example.fragmentslab.fragments.OrderResultFragment;

public class MainActivity extends AppCompatActivity implements OrderFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(id){
            case R.id.action_search_order :
                startActivity(new Intent(this, OrdersActivity.class));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(OrderDTO order) {
        OrderResultFragment fragment = (OrderResultFragment) getSupportFragmentManager()
                .findFragmentById(R.id.orderResultFragment);

        if(fragment != null && fragment.isInLayout()) {
            fragment.fillData(order);
        }
    }
}
