package com.example.firebasetask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ReadActivity extends AppCompatActivity {
    FirebaseFirestore firebaseFirestore;
    ProgressDialog progressDialog;
    RecyclerView recyclerView;
    MyAdapter myadapter;

    List<MyModel> myModelList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read2);


        firebaseFirestore=FirebaseFirestore.getInstance();
        progressDialog=new ProgressDialog(this);
        progressDialog.setCancelable(false);

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        myadapter=new MyAdapter(getApplicationContext(),myModelList);
        recyclerView.setAdapter(myadapter);






    }

    public void loaddata(View view) {
        myModelList.clear();
        progressDialog.show();
        progressDialog.setMessage("Loading Data---");

        firebaseFirestore.collection("Hostel").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                progressDialog.dismiss();

                if (!task.isSuccessful())
                {
                    Toast.makeText(ReadActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    for (QueryDocumentSnapshot snapshot:task.getResult())
                    {
                        String name=snapshot.getString("Name");
                        String mobile=snapshot.getString("Mobile");
                        String mail =snapshot.getString("Mail");
                        String gender=snapshot.getString("Gender");
                        String state=snapshot.getString("State");


                        MyModel dataModel=new MyModel(name,mobile,mail,gender,state);
                        myModelList.add(dataModel);

                        myadapter.notifyDataSetChanged();




//                        dataModelList.add(""+name+"\n"+mail+"\n"+mobile+"\n"+gender+"\n"+state);
//                        dataAdapter.notifyDataSetChanged();



                    }
                }


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();

            }
        });
    }
}

