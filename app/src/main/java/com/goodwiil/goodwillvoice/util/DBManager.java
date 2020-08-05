package com.goodwiil.goodwillvoice.util;

import android.util.Log;

import androidx.annotation.NonNull;

import com.goodwiil.goodwillvoice.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DBManager {
    public static FirebaseFirestore db;

    public DBManager() {
        db = FirebaseFirestore.getInstance();
    }

    public static void insertData(User data) {
        Map<String, Object> user = new HashMap<>();
        user.put("gender", data.getGender());
        user.put("year", data.getYear());
        user.put("nickname", data.getNickName());
        user.put("job", data.getCareer());

        // Add a new document with a generated ID
        db.collection("User")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
//                        Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
//                        Log.w("TAG", "Error adding document", e);
                    }
                });

    }
}