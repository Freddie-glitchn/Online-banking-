package com.example.banktransactions;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Storing all transactions in listView
    private int currentBal;
    private ArrayList<String> arrayListTrans;
    private ArrayAdapter<String> arrayAdapterTrans;
    private DatabaseHandler databaseHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setting title of an activity
        setTitle("Bank Transactions");

        databaseHandler = new DatabaseHandler(this);

        currentBal = 2000;

        arrayListTrans = new ArrayList<String>();
        arrayAdapterTrans = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayListTrans);

        ListView listView = findViewById(R.id.listViewTransactions);
        listView.setAdapter(arrayAdapterTrans);

        //Loading database table to user interface
        List<String> transactions = databaseHandler.getTransactions();

        for (String transaction: transactions){
            arrayAdapterTrans.add(transaction);
        }

    }


    //get the menu it uses and load options created from main_menu.xml
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    //action performed when an option is selected on the menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.mainDeposit){
            //Toast.makeText(this, "Deposit menu selected", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), DepositActivity.class);
            //For actiity to retrieve a value when it closes
            startActivityForResult(intent, 1);

        }else if (item.getItemId() == R.id.mainWithdraw){
            //Toast.makeText(getApplicationContext(), "WIthdrawal menu selected", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(getApplicationContext(), WithdrawalActivity.class);
            startActivityForResult(intent, 2);
            //Resultcode is used to notify which activity returns a result


        }else if (item.getItemId()== R.id.mainClearTransaction){
            //Toast.makeText(this, "Clear Transactiond menu selected", Toast.LENGTH_LONG).show();
            databaseHandler.deleteTransactions();

        }else if (item.getItemId()== R.id.mainAbout){
            //Toast.makeText(this, "About menu selected", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
            startActivity(intent);
            
        }

        return super.onOptionsItemSelected(item);

    }

    //this method is called automatically when expecting results from an activity that has been shown
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        TextView textViewCurrentBal = findViewById(R.id.textViewCurrentBal);

        int amount = data.getExtras().getInt("amount");
        if (requestCode == 1){

            currentBal += amount;
            arrayAdapterTrans.add("Deposited: KShs " + amount);
            textViewCurrentBal.setText("Current Balance : KShs " + currentBal);
            Toast.makeText(getApplicationContext(), "Depositing Kshs: " + amount, Toast.LENGTH_LONG).show();
            databaseHandler.addTransaction("Depsited: KShs: " + amount);
        }else if (requestCode == 2){

            currentBal -= amount;
            arrayAdapterTrans.add("Withdrew: KShs " + amount);
            textViewCurrentBal.setText("Current Balance : KShs " + currentBal);
            Toast.makeText(getApplicationContext(), "You have withdrawn KShs: " + amount, Toast.LENGTH_LONG).show();
            databaseHandler.addTransaction("Withdrawn: KShs: " + amount);

        }




    }
}
