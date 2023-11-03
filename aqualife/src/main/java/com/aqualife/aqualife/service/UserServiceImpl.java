package com.aqualife.aqualife.service;

import com.aqualife.aqualife.dao.UserDao;
import com.aqualife.aqualife.model.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.internal.FirebaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    public static final String COLLECTION_NAME = "Member";

    @Override
    public String insertUser(User user) throws Exception{
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<com.google.cloud.firestore.WriteResult> apiFuture =
                firestore.collection(COLLECTION_NAME).document(user.getId()).set(user);
        return apiFuture.get().getUpdateTime().toString();
    }

    @Override
    public User getUserDetail(String id) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference =
                firestore.collection(COLLECTION_NAME).document(id);
        ApiFuture<DocumentSnapshot> apiFuture = documentReference.get();
        DocumentSnapshot documentSnapshot = apiFuture.get();
        User user = null;
        if(documentSnapshot.exists()){
            user = documentSnapshot.toObject(User.class);
            return user;
        }
        else{
            return null;
        }
    }

    @Override
    public String updateUser(User user) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> apiFuture
                = firestore.collection(COLLECTION_NAME).document(user.getId()).set(user);
        return apiFuture.get().getUpdateTime().toString();
    }

    @Override
    public String deleteUser(String id) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> apiFuture
                = firestore.collection(COLLECTION_NAME).document(id).delete();
        return "Document id: "+id+" delete";
    }
}