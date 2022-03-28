package com.example.sqllite;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private EditText edtEmail;
    private EditText edtUsername;
    private EditText edtPassword;
    private EditText edtConfirmPassword;

    private Button btnRegister;
    private Button btnEdit;
    private Button btnFindActivity;
    private Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtConfirmPassword = (EditText) findViewById(R.id.edtConfirmPassword);

        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnFindActivity = (Button) findViewById(R.id.btnFindActivity);
        btnDelete = (Button) findViewById(R.id.btnDelete);

        DatabaseHandler handler = new DatabaseHandler(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check()){
                    String password = edtPassword.getText().toString();
                    String confirm = edtConfirmPassword.getText().toString();
                    if(password.equals(confirm)){
                        User user = new User();
                        user.setEmail(edtEmail.getText().toString());
                        user.setUsername(edtUsername.getText().toString());
                        user.setPassword(edtPassword.getText().toString());

                        handler.insert(user);

                        Toast.makeText(getApplicationContext(), "Đã thêm mới thành công", Toast.LENGTH_SHORT).show();
                        clear();
                    } else {
                        Toast.makeText(getApplicationContext(), "Xác nhận mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Bạn chưa nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check()){
                    String password = edtPassword.getText().toString();
                    String confirm = edtConfirmPassword.getText().toString();
                    if(password.equals(confirm)){
                        User user = new User();
                        user.setEmail(edtEmail.getText().toString());
                        user.setUsername(edtUsername.getText().toString());
                        user.setPassword(edtPassword.getText().toString());

                        handler.updateUser(user);

                        Toast.makeText(getApplicationContext(), "Đã chỉnh sửa thành công", Toast.LENGTH_SHORT).show();
                        clear();
                    } else {
                        Toast.makeText(getApplicationContext(), "Xác nhận mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Bạn chưa nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnFindActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                if (!email.isEmpty()){
                    handler.deleteUser(email);
                    Toast.makeText(getApplicationContext(), "Đã xóa", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Bạn chưa nhập email", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private boolean check(){
        String email = edtEmail.getText().toString();
        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();
        String confirmPassword = edtConfirmPassword.getText().toString();

        if (email.isEmpty()||username.isEmpty()||password.isEmpty()||confirmPassword.isEmpty()){
            return false;
        } else {
            return true;
        }
    }

    private void clear(){
        edtEmail.setText("");
        edtUsername.setText("");
        edtPassword.setText("");
        edtConfirmPassword.setText("");
    }
}