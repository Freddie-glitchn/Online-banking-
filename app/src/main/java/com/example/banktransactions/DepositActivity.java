package com.example.banktransactions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DepositActivity extends AppCompatActivity {

    public void acceptDep(View view){

        EditText editText = findViewById(R.id.editTextDeposit);
        int depositAmount = 0;


        try{

            depositAmount = Integer.parseInt(editText.getText().toString());

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Please Enter a Valid Amount", Toast.LENGTH_LONG).show();
        }

        Intent intent = getIntent();
        intent.putExtra("amount", depositAmount);
        setResult(RESULT_OK, intent);
        finish();

    }

    public void exitDep(View view){

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);
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
            //Toast.makeText(this, "Deposit menu selected", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), DepositActivity.class);
            startActivity(intent);

        }else if (item.getItemId() == R.id.mainWithdraw){
            Toast.makeText(getApplicationContext(), "WIthdrawal menu selected", Toast.LENGTH_LONG).show();

        }else if (item.getItemId()== R.id.mainClearTransaction){
            Toast.makeText(this, "Clear Transactiond menu selected", Toast.LENGTH_LONG).show();

        }else if (item.getItemId()== R.id.mainAbout){
            //Toast.makeText(this, "About menu selected", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
            startActivity(intent);

        }else if (item.getItemId() == R.id.homePage){

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }
}
