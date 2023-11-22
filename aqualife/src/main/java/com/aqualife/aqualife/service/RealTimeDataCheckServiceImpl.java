package com.aqualife.aqualife.service;

import com.aqualife.aqualife.model.Co2;
import com.aqualife.aqualife.model.Fishbowl;
import com.aqualife.aqualife.model.Light;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class RealTimeDataCheckServiceImpl implements RealTimeDataCheckService{
    public static final String COLLECTION_NAME = "fishbowl";

    private List<Fishbowl> getAllUsersFishbowl() throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();

        List<Fishbowl> fishbowlList = null;

        if (!firestore.collection(COLLECTION_NAME).get().get().toObjects(Fishbowl.class).isEmpty()) {
            fishbowlList = firestore.collection(COLLECTION_NAME).get().get().toObjects(Fishbowl.class);
        }

        return fishbowlList;
    }

    @Override
    public List<String> checkCo2Data(List<String> co2FishbowlArr) throws Exception {
        List<Fishbowl> fishbowlList = getAllUsersFishbowl();
        List<String> keepTrueArr = new ArrayList<>();

        // co2 예약시간에 따라서 true
        for (Fishbowl fishbowl : fishbowlList){
            List<Co2> co2List = fishbowl.getCo2();
            String fishbowlName = fishbowl.getEmail() + "_" + fishbowl.getFishbowl();
            for (Co2 co2 : co2List) {
                String[] startArr = co2.getStartTime().split("_");
                String[] endArr = co2.getEndTime().split("_");

                if (LocalTime.of(Integer.parseInt(startArr[0]), Integer.parseInt(startArr[1])).isBefore(LocalTime.now())
                        && LocalTime.of(Integer.parseInt(endArr[0]), Integer.parseInt(endArr[1])).isAfter(LocalTime.now())
                        && co2.isState() == true) {
                    keepTrueArr = changeCo2Function(fishbowlName, true, keepTrueArr);
                    break;
                }
            }
        }
        // co2 예약시간 끝나면 false
        if (co2FishbowlArr != null && !co2FishbowlArr.isEmpty()) {
            for (Fishbowl fishbowl : fishbowlList) {
                for (String co2Str : co2FishbowlArr) {
                    if ((fishbowl.getEmail()+"_"+fishbowl.getFishbowl())
                            .equals(co2Str)) {
                        List<Co2> co2List = fishbowl.getCo2();
                        for (Co2 co2 :co2List){
                            String[] endArr = co2.getEndTime().split("_");
                            if (LocalTime.of(Integer.parseInt(endArr[0]), Integer.parseInt(endArr[1]))
                                    .isBefore(LocalTime.now())
                                    && co2.isState() == true) {
                                keepTrueArr = changeCo2Function(co2Str, false, keepTrueArr);
                            }
                        }
                    }
                }
            }
        }

        return keepTrueArr;
    }

    @Override
    public List<String> checkLightData(List<String> lightFishbowlArr) throws Exception {
        List<Fishbowl> fishbowlList = getAllUsersFishbowl();
        List<String> keepTrueArr = new ArrayList<>();

        // co2 예약시간에 따라서 true
        for (Fishbowl fishbowl : fishbowlList){
            List<Light> lightList = fishbowl.getLight();
            String fishbowlName = fishbowl.getEmail() + "_" + fishbowl.getFishbowl();
            for (Light light : lightList) {
                String[] startArr = light.getStartTime().split("_");
                String[] endArr = light.getEndTime().split("_");

                if (LocalTime.of(Integer.parseInt(startArr[0]), Integer.parseInt(startArr[1])).isBefore(LocalTime.now())
                        && LocalTime.of(Integer.parseInt(endArr[0]), Integer.parseInt(endArr[1])).isAfter(LocalTime.now())
                        && light.isState() == true) {
                    keepTrueArr = changeLightFunction(fishbowlName, true, keepTrueArr);
                    break;
                }
            }
        }
        // co2 예약시간 끝나면 false
        if (lightFishbowlArr != null && !lightFishbowlArr.isEmpty() ) {
            for (Fishbowl fishbowl : fishbowlList) {
                for (String lightStr : lightFishbowlArr) {
                    if ((fishbowl.getEmail()+"_"+fishbowl.getFishbowl())
                            .equals(lightStr)) {
                        List<Light> lightList = fishbowl.getLight();
                        for (Light light : lightList){
                            String[] endArr = light.getEndTime().split("_");
                            if (LocalTime.of(Integer.parseInt(endArr[0]), Integer.parseInt(endArr[1]))
                                    .isBefore(LocalTime.now())
                                    && light.isState() == true) {
                                keepTrueArr = changeLightFunction(lightStr, false, keepTrueArr);
                            }
                        }
                    }
                }
            }
        }

        return keepTrueArr;
    }

    @Override
    public boolean checkFilterData() throws ExecutionException, InterruptedException {
        getAllUsersFishbowl();
        return false;
    }

    @Override
    public boolean checkTemperatureData() throws ExecutionException, InterruptedException {
        getAllUsersFishbowl();
        return false;
    }

    @Override
    public boolean checkPhData() throws ExecutionException, InterruptedException {
        getAllUsersFishbowl();
        return false;
    }

    private List<String> changeCo2Function(String documentName, boolean states, List<String> keepTrueArr) throws Exception{
        Firestore firestore = FirestoreClient.getFirestore();

        DocumentReference documentReference =
                firestore.collection(COLLECTION_NAME).document(documentName);
        ApiFuture<DocumentSnapshot> apiFuture = documentReference.get();
        DocumentSnapshot documentSnapshot = apiFuture.get();
        Fishbowl fishbowl = documentSnapshot.toObject(Fishbowl.class);
        fishbowl.getState().set(0, states);
        documentReference.set(fishbowl);

        if (states) {
            keepTrueArr.add(documentName);
            System.out.println(documentSnapshot.toObject(Fishbowl.class).getState());
            System.out.println(keepTrueArr);
        } else {
            keepTrueArr.remove(documentName);
            System.out.println(documentSnapshot.toObject(Fishbowl.class).getState());
            System.out.println(keepTrueArr);
        }

        return keepTrueArr;
    }

    private List<String> changeLightFunction(String documentName, boolean states, List<String> keepTrueArr) throws Exception{
        Firestore firestore = FirestoreClient.getFirestore();

        DocumentReference documentReference =
                firestore.collection(COLLECTION_NAME).document(documentName);
        ApiFuture<DocumentSnapshot> apiFuture = documentReference.get();
        DocumentSnapshot documentSnapshot = apiFuture.get();
        Fishbowl fishbowl = documentSnapshot.toObject(Fishbowl.class);
        fishbowl.getState().set(1, states);
        documentReference.set(fishbowl);

        if (states) {
            keepTrueArr.add(documentName);
            System.out.println(documentSnapshot.toObject(Fishbowl.class).getState());
            System.out.println(keepTrueArr);
        } else {
            keepTrueArr.remove(documentName);
            System.out.println(documentSnapshot.toObject(Fishbowl.class).getState());
            System.out.println(keepTrueArr);
        }

        return keepTrueArr;
    }
}
