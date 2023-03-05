package com.example.coursework_mindplex;

import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class AdviceAsyncTask extends AsyncTask<FirebaseFirestore,Void,String> {
public String data;

    @Override
    protected String doInBackground(FirebaseFirestore... firebaseFirestores) {
        firebaseFirestores[0].collection("Advice").document("Stress").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        data = document.getString("Text");
                    } else {
                        Log.d("MainActivity", "No such document");
                    }
                } else {
                    Log.d("MainActivity", "get failed with ",
                            task.getException());
                }
            }
        });
    return data;
    }

    @Override
    public void onPostExecute(String displayData){

    }
}
