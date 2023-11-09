package com.aqualife.aqualife.service;

import com.aqualife.aqualife.model.Co2;
import com.aqualife.aqualife.model.Fishbowl;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class FishbowlServiceImpl implements FishbowlService {
    public static final String COLLECTION_NAME = "fishbowl";

    @Override
//  아이디 있으면 false, 각 제약조건에 안맞으면 false
    public boolean createFishbowl(String email, String fishbowl) throws Exception {
        String naming = email + "_"+ fishbowl;

        if (getFishbowl(naming, fishbowl) == null) {
            Fishbowl fishbowl1 = Fishbowl.builder()
                    .email(email)
                    .fishbowl(fishbowl)
                    .co2(new ArrayList())
                    .build();

            Firestore firestore = FirestoreClient.getFirestore();
            firestore.collection(COLLECTION_NAME).document(naming).set(fishbowl1);

            return true;
        } else {
            return false;
        }
    }

    @Override
    public Fishbowl getFishbowl(String email, String fishbowl) throws Exception {
        String fishbowlName = email + "_" + fishbowl;

        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference =
                firestore.collection(COLLECTION_NAME).document(fishbowlName);
        ApiFuture<DocumentSnapshot> apiFuture = documentReference.get();
        DocumentSnapshot documentSnapshot = apiFuture.get();

        Fishbowl fishbowl1 = null;
        if (documentSnapshot.exists()) {
            fishbowl1 = documentSnapshot.toObject(Fishbowl.class);
            return fishbowl1;
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<Fishbowl> getAllFishbowl(String email) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        ArrayList<Fishbowl> list = new ArrayList<>();
        ApiFuture<QuerySnapshot> apiFuture = firestore.collection(COLLECTION_NAME).get();
        List<QueryDocumentSnapshot> documents = apiFuture.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            if (document.toObject(Fishbowl.class).getEmail().equals(email)) {
                list.add(document.toObject(Fishbowl.class));
            }
        }
        return list;
    }

    @Override
    public boolean fishbowlDelete(String email, String fishbowl) {
        String fishbowlName = email + "_" + fishbowl;

        Firestore firestore = FirestoreClient.getFirestore();
        firestore.collection(COLLECTION_NAME).document(fishbowlName).delete();

        return true;
    }

    @Override
    public boolean changeFishbowlName
            (String email, String fishbowl_beforeName, String fishbowl_afterName) throws Exception {
        Fishbowl fishbowl = getFishbowl(email, fishbowl_beforeName);

        createFishbowl(email, fishbowl_afterName);
        fishbowlDelete(email, fishbowl_beforeName);

        return true;
    }
}
