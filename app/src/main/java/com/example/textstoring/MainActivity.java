package com.example.textstoring;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Button;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;




public class MainActivity extends AppCompatActivity {
    EditText mEditText;
    Button btn,btnLoad;

    private static final String FILE_NAME="example.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText=findViewById(R.id.editTextText);
        btn=findViewById(R.id.Save);
        btnLoad=findViewById(R.id.Load);

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
        btnLoad.setOnClickListener(view -> {

            FileInputStream fileInputStream=null;
            try{
                fileInputStream=openFileInput(FILE_NAME);

                InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                StringBuilder stringBuilder=new StringBuilder();
                String text;
                while ((text = bufferedReader.readLine()) != null) {
                    stringBuilder.append(text).append("\n");
                }
                mEditText.setText(stringBuilder.toString());
            }
            catch(IOException e){
                e.printStackTrace();
            }finally{
                if(fileInputStream !=null){
                    try{
                        fileInputStream.close();

                    }catch(IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}