package com.aqualife.aqualife.service;

import com.aqualife.aqualife.model.Filters;
import com.aqualife.aqualife.model.Fishbowl;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class FilterServiceImpl implements FilterService {
    public static final String COLLECTION_NAME = "fishbowl";

    @Override
    public void filterSetting(String email, String fishbowl, int dayDecimalCode, String time, int filterRange) throws ExecutionException, InterruptedException {
        String fishbowlName = email + "_" + fishbowl;

        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference =
                firestore.collection(COLLECTION_NAME).document(fishbowlName);
        ApiFuture<DocumentSnapshot> apiFuture = documentReference.get();
        DocumentSnapshot documentSnapshot = apiFuture.get();

        String localDate = Integer.toBinaryString(dayDecimalCode);
        int lengthCheck = localDate.length();

        if (lengthCheck != 7) {
            for (int i = 0; i < 7 - lengthCheck; i++) {
                localDate = "0" + localDate;
            }
        }

        Filters createFilter = new Filters(localDate, time, filterRange);

        Fishbowl fishbowl1 = null;
        if (documentSnapshot.exists()) {
            fishbowl1 = documentSnapshot.toObject(Fishbowl.class);
            fishbowl1.setFilter(createFilter);
            documentReference.set(fishbowl1);
        }
    }

    @Override
    public void filterUpdate(String email, String fishbowl, int dayDecimalCode,
                             String time, int filterRange)
            throws ExecutionException, InterruptedException {
        String fishbowlName = email + "_" + fishbowl;

        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference =
                firestore.collection(COLLECTION_NAME).document(fishbowlName);
        ApiFuture<DocumentSnapshot> apiFuture = documentReference.get();
        DocumentSnapshot documentSnapshot = apiFuture.get();

        String localDate = Integer.toBinaryString(dayDecimalCode);
        int lengthCheck = localDate.length();

        if (lengthCheck != 7) {
            for (int i = 0; i < 7 - lengthCheck; i++) {
                localDate = "0" + localDate;
            }
        }

        Fishbowl fishbowl1 = null;
        if (documentSnapshot.exists()) {
            fishbowl1 = documentSnapshot.toObject(Fishbowl.class);

            fishbowl1.setFilter(new Filters(
                    localDate,
                    time,
                    filterRange
            ));
            documentReference.set(fishbowl1);
        }
    }


    @Override
    public Filters getFilter(Fishbowl fishbowl) {
        return fishbowl.getFilter();
    }

    @Override
    public int nowFilterValue(String email, String fishbowlName) throws ExecutionException, InterruptedException {
        String fName = email +"_" + fishbowlName;

        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference =
                firestore.collection(COLLECTION_NAME).document(fName);
        ApiFuture<DocumentSnapshot> apiFuture = documentReference.get();
        DocumentSnapshot documentSnapshot = apiFuture.get();

        String filterValue = String.valueOf(documentSnapshot.toObject(Fishbowl.class).getState().get(2));

        return Integer.parseInt(filterValue);
    }
}
