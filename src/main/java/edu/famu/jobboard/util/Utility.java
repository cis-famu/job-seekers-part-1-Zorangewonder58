package edu.famu.jobboard.util;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

public class Utility {

    public static final String DEFAULT_MEDIA_TYPE = "application/json";

    // Method to retrieve DocumentReference based on the provided ID
    public static DocumentReference retrieveDocumentReference(String collection, String id) {
        Firestore db = FirestoreClient.getFirestore();

        //I am getting a document with this doc id from this collection
        return db.collection(collection).document(id);
    }
}
