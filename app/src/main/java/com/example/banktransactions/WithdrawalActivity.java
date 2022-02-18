package com.example.banktransactions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class WithdrawalActivity extends AppCompatActivity {

    public void acceptwith(View view){

        EditText editText = findViewById(R.id.editTextW);

        int amount = 0;

        try{

            amount = Integer.parseInt(editText.getText().toString());

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Enter a valid amount", Toast.LENGTH_LONG).show();

            return;
        }

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("amount", amount);
        setResult(RESULT_OK, intent);
        finish();

    }

    public void exitwith(View view){

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawal);


    }
}
