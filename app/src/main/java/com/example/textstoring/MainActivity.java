package com.example.textstoring;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Button;
import java.io.FileOutputStream;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {
    EditText mEditText;
    Button btn;
    private static final String FILE_NAME="example.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText=findViewById(R.id.editTextText);
        btn=findViewById(R.id.Save);
        btn.setOnClickListener(view -> {
            String text=mEditText.getText().toString();
            FileOutputStream fileOutputStream=null;
            try{
                fileOutputStream=openFileOutput(FILE_NAME,MODE_PRIVATE);
                fileOutputStream.write(text.getBytes());
                mEditText.getText().clear();
                Toast.makeText(MainActivity.this,"Saved to"+getFilesDir()+"/"+FILE_NAME,Toast.LENGTH_LONG).show();
            }
            catch(IOException e){
                e.printStackTrace();
            }finally{
                if(fileOutputStream !=null){
                    try{
                        fileOutputStream.close();

                    }catch(IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}