package com.example.homepage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.style.StrikethroughSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ContactWithEmailActivity extends AppCompatActivity {

    EditText email;
    EditText subject;
    EditText message;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_with_email);
        getSupportActionBar().setTitle("Contact us");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        email = findViewById(R.id.email_email);
        subject = findViewById(R.id.email_subject);
        message = findViewById(R.id.email_message);
        submit = findViewById(R.id.button_submit);
        /*

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!email.getText().toString().isEmpty() && !subject.getText().toString().isEmpty() && !message.getText().toString().isEmpty()){
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email.getText().toString()});
                    intent.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());
                    intent.putExtra(Intent.EXTRA_TEXT, message.getText().toString());
                    intent.setType("message/rfc822");
                    if (intent.resolveActivity(getPackageManager()) != null){
                        startActivity(intent);
                    }else{
                        Toast.makeText(ContactWithEmailActivity.this, "There is no application that support this action", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(ContactWithEmailActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
            }

        });
         */
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });
    }
    private void sendMail(){
        String recipientsList = email.getText().toString();
        String [] recipients = recipientsList.split(",");
        String Subject = subject.getText().toString();
        String Message = message.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT,Subject);
        intent.putExtra(Intent.EXTRA_TEXT, Message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "choose an email client"));
    }

}