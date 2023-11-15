package com.aqualife.aqualife.service;

import com.aqualife.aqualife.dto.UsersDto;
import com.aqualife.aqualife.model.Users;
import com.aqualife.aqualife.service.UserService;
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
//  다 맞으면 true, 하나라도 틀리면 false, 아이디 없으면 null
    public String login(String email, String password) throws Exception {
        Users users = getUserDetail(email);
        UsersDto usersDto;

        if (users != null) {
            usersDto = getUsersDto(users);
        } else {
            return null;
        }

        if (usersDto.getEmail().equals(email) && usersDto.getPassword().equals(password)) {
            return "true";
        } else {
            return "false";
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
//  아이디 있으면 false, 각 제약조건에 안맞으면 false
    public boolean join(UsersDto usersDto) throws Exception {
        if (checkUserJoin(usersDto) && getUserDetail(usersDto.getEmail()) == null) {
            Users users = usersDto.toEntity();

            Firestore firestore = FirestoreClient.getFirestore();
            ApiFuture<com.google.cloud.firestore.WriteResult> apiFuture =
                    firestore.collection(COLLECTION_NAME).document(users.getEmail()).set(users);

            return true;
        } else {
            return false;
        }
    }

    @Override
    public Users getUserDetail(String email) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference =
                firestore.collection(COLLECTION_NAME).document(email);
        ApiFuture<DocumentSnapshot> apiFuture = documentReference.get();
        DocumentSnapshot documentSnapshot = apiFuture.get();
        Users users = null;
        if (documentSnapshot.exists()) {
            users = documentSnapshot.toObject(Users.class);
            return users;
        } else {
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
        return "Document id: " + email + " delete";
    }

    @Override
    public UsersDto checkUser(String email, String phone) throws Exception {
        Users users = getUserDetail(email);
        UsersDto usersDto = null;

        if (users != null && users.getPhone().equals(phone)) {
            usersDto = getUsersDto(users);
        }

        return usersDto;
    }

    @Override
    public boolean changePassword(String beforePassword, String newPassword, String email, String phone) throws Exception {
        UsersDto usersDto = checkUser(email, phone);

        if (beforePassword.equals(newPassword) || !(beforePassword.equals(usersDto.getPassword())) ||
            usersDto.equals(null)) {
            return false;
        } else {
            UsersDto usersDto1 = getUsersDto(getUserDetail(usersDto.getEmail()));

            usersDto1.setPassword(newPassword);

            updateUser(usersDto1);

            return true;
        }
    }

    public boolean checkUserJoin(UsersDto usersDto) {
        if (usersDto.getEmail() != null && usersDto.getEmail().length() != 0 &&
                usersDto.getPassword() != null && usersDto.getPassword().length() != 0 &&
                usersDto.getPhone() != null && usersDto.getPhone().length() == 11
        ) {
            return true;
        } else {
            return false;
        }
    }
}