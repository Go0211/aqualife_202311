package com.aqualife.aqualife.service;

import com.aqualife.aqualife.model.Fishbowl;
import com.aqualife.aqualife.model.Ph;
import com.aqualife.aqualife.model.Temperature;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TemperatureServiceImpl implements TemperatureService{

    public static final String COLLECTION_NAME = "fishbowl";
    @Override
    public Temperature getTemperature(String email, String fishbowl) throws Exception{
        String fishbowlName = email +"_" + fishbowl;

        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference =
                firestore.collection(COLLECTION_NAME).document(fishbowlName);
        ApiFuture<DocumentSnapshot> apiFuture = documentReference.get();
        DocumentSnapshot documentSnapshot = apiFuture.get();

        Temperature temperature = null;
        if (documentSnapshot.exists()) {
            temperature = documentSnapshot.toObject(Fishbowl.class).getTemperature();
        }
        return temperature;
    }

    @Override
    public void temperatureSetting(String email, String fishbowl, Temperature temperature) throws Exception{
        String fishbowlName = email +"_" + fishbowl;

        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference =
                firestore.collection(COLLECTION_NAME).document(fishbowlName);
        ApiFuture<DocumentSnapshot> apiFuture = documentReference.get();
        DocumentSnapshot documentSnapshot = apiFuture.get();


        Fishbowl fishbowl1 = null;
        if (documentSnapshot.exists()) {
            fishbowl1 = documentSnapshot.toObject(Fishbowl.class);
            temperature.setTempState(fishbowl1.getTemperature().getTempState());
            fishbowl1.setTemperature(temperature);
            documentReference.set(fishbowl1);
        }
    }
}
