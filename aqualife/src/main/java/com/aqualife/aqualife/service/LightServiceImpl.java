package com.aqualife.aqualife.service;

import com.aqualife.aqualife.Data.Times;
import com.aqualife.aqualife.model.Co2;
import com.aqualife.aqualife.model.Fishbowl;
import com.aqualife.aqualife.model.Light;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RequiredArgsConstructor
@Service
public class LightServiceImpl implements LightService{
    public static final String COLLECTION_NAME = "fishbowl";

    @Override
    public List<Light> getAllLight(Fishbowl fishbowlData) {
        List<Light> lightList = fishbowlData.getLight();
        List<Light> firstExceptLightList = new ArrayList<>();

        for (int i = 1; i < lightList.size(); i++) {
            firstExceptLightList.add(lightList.get(i));
        }

        return firstExceptLightList;
    }

    public Light getLight(String email, String fishbowl, int lightStat) throws Exception {
        String fishbowlName = email +"_" + fishbowl;

        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference =
                firestore.collection(COLLECTION_NAME).document(fishbowlName);
        ApiFuture<DocumentSnapshot> apiFuture = documentReference.get();
        DocumentSnapshot documentSnapshot = apiFuture.get();

        Light light = null;

        if (documentSnapshot.exists()) {
            light = documentSnapshot.toObject(Fishbowl.class).getLight().get(lightStat);
        }

        return light;
    }

    @Override
    public void lightChange(String email, String fishbowl, int lightStat, Times times) throws Exception {
        String fishbowlName = email +"_" + fishbowl;

        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference =
                firestore.collection(COLLECTION_NAME).document(fishbowlName);
        ApiFuture<DocumentSnapshot> apiFuture = documentReference.get();
        DocumentSnapshot documentSnapshot = apiFuture.get();

        Light changeLight = new Light(times.getStartTime(), times.getEndTime(), true);

        Fishbowl fishbowl1 = null;
        if (documentSnapshot.exists()) {
            fishbowl1 = documentSnapshot.toObject(Fishbowl.class);
            fishbowl1.getLight().set(lightStat, changeLight);
            documentReference.set(fishbowl1);
        }
    }

    @Override
    public void lightDelete(String email, String fishbowl, int lightIndex) throws Exception {
        String fishbowlName = email +"_" + fishbowl;

        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference =
                firestore.collection(COLLECTION_NAME).document(fishbowlName);
        ApiFuture<DocumentSnapshot> apiFuture = documentReference.get();
        DocumentSnapshot documentSnapshot = apiFuture.get();

        Fishbowl fishbowl1 = null;
        if (documentSnapshot.exists()) {
            fishbowl1 = documentSnapshot.toObject(Fishbowl.class);
            fishbowl1.getLight().remove(lightIndex);
            documentReference.set(fishbowl1);
        }
    }

    @Override
    public void lightCreate(String email, String fishbowl, Times times) throws Exception {
        String fishbowlName = email +"_" + fishbowl;

        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference =
                firestore.collection(COLLECTION_NAME).document(fishbowlName);
        ApiFuture<DocumentSnapshot> apiFuture = documentReference.get();
        DocumentSnapshot documentSnapshot = apiFuture.get();

        Light createLight = new Light(times.getStartTime(), times.getEndTime(), true);

        Fishbowl fishbowl1 = null;
        if (documentSnapshot.exists()) {
            fishbowl1 = documentSnapshot.toObject(Fishbowl.class);
            fishbowl1.getLight().add(createLight);
            documentReference.set(fishbowl1);
        }
    }

    @Override
    public void lightStateChange(String email, String fishbowl, int lightIndex) throws Exception {
        String fishbowlName = email +"_" + fishbowl;

        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference =
                firestore.collection(COLLECTION_NAME).document(fishbowlName);
        ApiFuture<DocumentSnapshot> apiFuture = documentReference.get();
        DocumentSnapshot documentSnapshot = apiFuture.get();

        Fishbowl fishbowl1 = null;
        if (documentSnapshot.exists()) {
            fishbowl1 = documentSnapshot.toObject(Fishbowl.class);

            Light changeLight = new Light(fishbowl1.getLight().get(lightIndex).getStartTime(),
                    fishbowl1.getLight().get(lightIndex).getEndTime(),
                    !fishbowl1.getLight().get(lightIndex).isState());

            fishbowl1.getLight().set(lightIndex, changeLight);
            documentReference.set(fishbowl1);
        }
    }

    @Override
    public void lightListStateChange(String email, String fishbowl, int lightIndex) throws ExecutionException, InterruptedException {
        String fishbowlName = email +"_" + fishbowl;
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference =
                firestore.collection(COLLECTION_NAME).document(fishbowlName);
        ApiFuture<DocumentSnapshot> apiFuture = documentReference.get();
        DocumentSnapshot documentSnapshot = apiFuture.get();

        Fishbowl fishbowl1 = null;
        if (documentSnapshot.exists()) {
            fishbowl1 = documentSnapshot.toObject(Fishbowl.class);

            Light changeLight = new Light(fishbowl1.getLight().get(lightIndex).getStartTime(),
                    fishbowl1.getLight().get(lightIndex).getEndTime(),
                    !fishbowl1.getLight().get(lightIndex).isState());

            fishbowl1.getLight().set(lightIndex, changeLight);
            documentReference.set(fishbowl1);
        }
    }

    @Override
    public void lightStateChange(String email, String fishbowl, boolean state) throws Exception {
        String fishbowlName = email + "_" + fishbowl;

        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference =
                firestore.collection(COLLECTION_NAME).document(fishbowlName);
        ApiFuture<DocumentSnapshot> apiFuture = documentReference.get();
        DocumentSnapshot documentSnapshot = apiFuture.get();

        Fishbowl fishbowl1;
        if (documentSnapshot.exists()) {
            fishbowl1 = documentSnapshot.toObject(Fishbowl.class);
            List<Object> stateList = fishbowl1.getState();
            stateList.set(1, state);
            documentReference.set(fishbowl1);
        }
    }
}

