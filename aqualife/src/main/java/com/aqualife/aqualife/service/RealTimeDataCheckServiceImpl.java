package com.aqualife.aqualife.service;

import com.aqualife.aqualife.model.*;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class RealTimeDataCheckServiceImpl implements RealTimeDataCheckService {
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
    public void checkCo2Data() throws Exception {
        List<Fishbowl> fishbowlList = getAllUsersFishbowl();
        for (Fishbowl fishbowl : fishbowlList) {
            String fishbowlName = fishbowl.getEmail() + "_" + fishbowl.getFishbowl();
            List<Co2> co2List = fishbowl.getCo2();
            for (Co2 co2 : co2List) {
                // 예약시간 시작하면 true
                String[] startArr = co2.getStartTime().split(":");
                if (Integer.parseInt(startArr[0]) == LocalTime.now().getHour()
                        && Integer.parseInt(startArr[1]) == LocalTime.now().getMinute()
//                        && Integer.parseInt("00") == LocalTime.now().getSecond()
                        && co2.isState()) {
                    System.out.println("c_t");
                    changeCo2Function(fishbowlName, true);
                }

                // co2 예약시간 끝나면 false
                String[] endArr = co2.getEndTime().split(":");
                if (Integer.parseInt(endArr[0]) == LocalTime.now().getHour()
                        && Integer.parseInt(endArr[1]) == LocalTime.now().getMinute()
//                        && Integer.parseInt("00") == LocalTime.now().getSecond()
                        && co2.isState()) {
                    System.out.println("c_f");
                    changeCo2Function(fishbowlName, false);
                }
            }
        }
    }

    private void changeCo2Function(String documentName, boolean states) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();

        DocumentReference documentReference =
                firestore.collection(COLLECTION_NAME).document(documentName);
        ApiFuture<DocumentSnapshot> apiFuture = documentReference.get();
        DocumentSnapshot documentSnapshot = apiFuture.get();
        Fishbowl fishbowl = documentSnapshot.toObject(Fishbowl.class);
        fishbowl.getState().set(0, states);
        documentReference.set(fishbowl);

        System.out.println(documentSnapshot.toObject(Fishbowl.class).getState());
    }

    @Override
    public void checkLightData() throws Exception {
        List<Fishbowl> fishbowlList = getAllUsersFishbowl();
        List<String> keepTrueArr = new ArrayList<>();

        // light 예약시간에 따라서 true
        for (Fishbowl fishbowl : fishbowlList) {
            String fishbowlName = fishbowl.getEmail() + "_" + fishbowl.getFishbowl();
            List<Light> lightList = fishbowl.getLight();
            for (Light light : lightList) {
                // light 예약시간 시작하면 true
                String[] startArr = light.getStartTime().split(":");
                if (Integer.parseInt(startArr[0]) == LocalTime.now().getHour()
                        && Integer.parseInt(startArr[1]) == LocalTime.now().getMinute()
//                        && Integer.parseInt("00") == LocalTime.now().getSecond()
                        && light.isState()) {
                    System.out.println("l_t");
                   changeLightFunction(fishbowlName, true);
                }

                // light 예약시간 끝나면 false
                String[] endArr = light.getEndTime().split(":");
                if (Integer.parseInt(endArr[0]) == LocalTime.now().getHour()
                        && Integer.parseInt(endArr[1]) == LocalTime.now().getMinute()
//                        && Integer.parseInt("00") == LocalTime.now().getSecond()
                        && light.isState()) {
                    System.out.println("l_f");
                    changeLightFunction(fishbowlName, false);
                }
            }
        }
    }

    private void changeLightFunction(String documentName, boolean states) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();

        DocumentReference documentReference =
                firestore.collection(COLLECTION_NAME).document(documentName);
        ApiFuture<DocumentSnapshot> apiFuture = documentReference.get();
        DocumentSnapshot documentSnapshot = apiFuture.get();
        Fishbowl fishbowl = documentSnapshot.toObject(Fishbowl.class);
        fishbowl.getState().set(1, states);
        documentReference.set(fishbowl);
        System.out.println(documentSnapshot.toObject(Fishbowl.class).getState());
    }

    @Override
    public boolean checkFilterData() throws ExecutionException, InterruptedException {
        return false;
    }

    @Override
    public boolean checkTemperatureData() throws ExecutionException, InterruptedException {
        List<Fishbowl> fishbowlList = getAllUsersFishbowl();
        for (Fishbowl fishbowl : fishbowlList) {
            String fishbowlName = fishbowl.getEmail() + "_" + fishbowl.getFishbowl();
            changeTemperatureFunction(fishbowlName);
        }

        return true;
    }

    private String changeTemperatureFunction(String fishbowlName) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();

        DocumentReference documentReference =
                firestore.collection(COLLECTION_NAME).document(fishbowlName);
        ApiFuture<DocumentSnapshot> apiFuture = documentReference.get();
        DocumentSnapshot documentSnapshot = apiFuture.get();
        Fishbowl fishbowl = documentSnapshot.toObject(Fishbowl.class);

        String temp = String.valueOf(fishbowl.getState().get(3));

        Temperature tempSet = fishbowl.getTemperature();
        tempSet.setTempState(Integer.parseInt(temp));
        fishbowl.setTemperature(tempSet);
        documentReference.set(fishbowl);

        return temp;
    }

    @Override
    public boolean checkPhData() throws ExecutionException, InterruptedException {
        List<Fishbowl> fishbowlList = getAllUsersFishbowl();
        for (Fishbowl fishbowl : fishbowlList) {
            String fishbowlName = fishbowl.getEmail() + "_" + fishbowl.getFishbowl();
            changePhFunction(fishbowlName);
        }
        return false;
    }

    private String changePhFunction(String fishbowlName) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();

        DocumentReference documentReference =
                firestore.collection(COLLECTION_NAME).document(fishbowlName);
        ApiFuture<DocumentSnapshot> apiFuture = documentReference.get();
        DocumentSnapshot documentSnapshot = apiFuture.get();
        Fishbowl fishbowl = documentSnapshot.toObject(Fishbowl.class);

        String ph = String.valueOf(fishbowl.getState().get(4));

        Ph phSet = fishbowl.getPh();
        phSet.setPhState(Double.parseDouble(ph));
        fishbowl.setPh(phSet);
        documentReference.set(fishbowl);

        return ph;
    }
}