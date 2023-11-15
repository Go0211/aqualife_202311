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
    public void filterSetting(String email, String fishbowl, String localDate, String localTime, int filterRange) throws ExecutionException, InterruptedException {
        String fishbowlName = email +"_" + fishbowl;

        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference =
                firestore.collection(COLLECTION_NAME).document(fishbowlName);
        ApiFuture<DocumentSnapshot> apiFuture = documentReference.get();
        DocumentSnapshot documentSnapshot = apiFuture.get();

        Filters createFilter = new Filters(localDate, localTime, filterRange);

        Fishbowl fishbowl1 = null;
        if (documentSnapshot.exists()) {
            fishbowl1 = documentSnapshot.toObject(Fishbowl.class);
            fishbowl1.getFilter().add(createFilter);
            documentReference.set(fishbowl1);
        }
    }

    @Override
    public void filterUpdate(String email, String fishbowl, String dayId,
                             String startHour, String startMinute, int filterRange)
            throws ExecutionException, InterruptedException {
        String fishbowlName = email +"_" + fishbowl;

        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference =
                firestore.collection(COLLECTION_NAME).document(fishbowlName);
        ApiFuture<DocumentSnapshot> apiFuture = documentReference.get();
        DocumentSnapshot documentSnapshot = apiFuture.get();

        Fishbowl fishbowl1 = null;
        if (documentSnapshot.exists()) {
            fishbowl1 = documentSnapshot.toObject(Fishbowl.class);

            for (int i = 0; i < fishbowl1.getFilter().size(); i++) {
                Filters filters = fishbowl1.getFilter().get(i);

                System.out.println(filters);
                if (filters.getDate().equals(dayId)) {
                    Filters filter = new Filters(filters.getDate(), startHour+"_"+startMinute, filterRange);

                    fishbowl1.getFilter().set(i, filter);
                    documentReference.set(fishbowl1);
                    break;
                }
            }
        }
    }

    @Override
    public Filters getFilter(Fishbowl fishbowl, String dayId) {
        for (Filters list : getAllFilter(fishbowl)) {
            if (list.getDate() != null && list.getDate().equals(dayId)) {
                return list;
            }
        }

        return null;
    }
    @Override
    public List<Filters> getAllFilter(Fishbowl fishbowlData) {
        List<Filters> filterList = fishbowlData.getFilter();

        return filterList;
    }


}
