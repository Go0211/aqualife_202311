package com.aqualife.aqualife.service;

import com.aqualife.aqualife.Data.Times;
import com.aqualife.aqualife.model.Co2;
import com.aqualife.aqualife.model.Fishbowl;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.function.UnaryOperator;

@RequiredArgsConstructor
@Service
public class Co2ServiceImpl implements Co2Service{
    public static final String COLLECTION_NAME = "fishbowl";
    @Override
    public List<Co2> getAllCo2(Fishbowl fishbowlData) {
        List<Co2> co2List = fishbowlData.getCo2();

        return co2List;
    }

    @Override
    public void co2Change(String email, String fishbowl, int co2Stat, Times times) throws Exception {
        String fishbowlName = email +"_" + fishbowl;

        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference =
                firestore.collection(COLLECTION_NAME).document(fishbowlName);
        ApiFuture<DocumentSnapshot> apiFuture = documentReference.get();
        DocumentSnapshot documentSnapshot = apiFuture.get();

        Co2 changeCo2 = new Co2(times.getStartHour()+times.getStartMinute(),
                times.getEndHour()+times.getEndMinute(),
                true);

        Fishbowl fishbowl1 = null;
        if (documentSnapshot.exists()) {
            fishbowl1 = documentSnapshot.toObject(Fishbowl.class);
            fishbowl1.getCo2().set(co2Stat, changeCo2);
            firestore.collection(COLLECTION_NAME).document(fishbowlName).set(fishbowl1);
        }
    }
}
