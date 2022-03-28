package com.example.sqllite;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class ShowActivity extends AppCompatActivity {

    private EditText edtFind;
    private Button btnFind;

    private TextView txtEmail;
    private TextView txtUsername;
    private TextView txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        edtFind = (EditText) findViewById(R.id.edtFind);
        btnFind = (Button) findViewById(R.id.btnFind);

        txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtUsername = (TextView) findViewById(R.id.txtUsername);
        txtPassword = (TextView) findViewById(R.id.txtPassword);


        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtFind.getText().toString();
                DatabaseHandler handler = new DatabaseHandler(ShowActivity.this);
                User user = handler.getUser(email);
                if (user!=null){
                    System.out.println(user.getEmail());
                    txtEmail.setText(user.getEmail().toString());
                    txtUsername.setText(user.getUsername().toString());
                    txtPassword.setText(user.getPassword().toString());
                }
            }
        });

    }
}