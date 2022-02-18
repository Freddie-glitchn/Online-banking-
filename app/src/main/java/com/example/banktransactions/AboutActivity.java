package com.example.banktransactions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        setTitle("About");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.mainDeposit){
            Toast.makeText(this, "Deposit menu selected", Toast.LENGTH_LONG).show();

        }else if (item.getItemId() == R.id.mainWithdraw){
            Toast.makeText(getApplicationContext(), "WIthdrawal menu selected", Toast.LENGTH_LONG).show();

        }else if (item.getItemId()== R.id.mainClearTransaction) {
            Toast.makeText(this, "Clear Transactiond menu selected", Toast.LENGTH_LONG).show();

        } else if (item.getItemId()== R.id.homePage) {
            //Toast.makeText(this, "Clear Transactiond menu selected", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);


        }

                return super.onOptionsItemSelected(item);
    }
}
