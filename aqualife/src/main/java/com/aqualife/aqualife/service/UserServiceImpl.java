package com.aqualife.aqualife.service;

import com.aqualife.aqualife.dto.UsersDto;
import com.aqualife.aqualife.model.Users;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    public static final String COLLECTION_NAME = "users";

    @Override
    public boolean login(String email, String password) throws Exception {
        Users users = getUserDetail(email);
        UsersDto usersDto = getUsersDto(users);

        if (usersDto.getEmail().equals(email) && usersDto.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public UsersDto getUsersDto(Users users) throws Exception {
        return UsersDto.builder()
                .email(users.getEmail())
                .password(users.getPassword())
                .phone(users.getPhone())
                .build();
    }

    @Override
    public String insertUser(UsersDto usersDto) throws Exception{
        Users users = usersDto.toEntity();

        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<com.google.cloud.firestore.WriteResult> apiFuture =
                firestore.collection(COLLECTION_NAME).document(users.getEmail()).set(users);
        return apiFuture.get().getUpdateTime().toString();
    }

    @Override
    public Users getUserDetail(String email) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference =
                firestore.collection(COLLECTION_NAME).document(email);
        ApiFuture<DocumentSnapshot> apiFuture = documentReference.get();
        DocumentSnapshot documentSnapshot = apiFuture.get();
        Users users = null;
        if(documentSnapshot.exists()){
            users = documentSnapshot.toObject(Users.class);
            return users;
        }
        else{
            return null;
        }
    }

    @Override
    public String updateUser(UsersDto usersDto) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> apiFuture
                = firestore.collection(COLLECTION_NAME).document(usersDto.getEmail()).set(usersDto);
        return apiFuture.get().getUpdateTime().toString();
    }

    @Override
    public String deleteUser(String email) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> apiFuture
                = firestore.collection(COLLECTION_NAME).document(email).delete();
        return "Document id: "+email+" delete";
    }
}