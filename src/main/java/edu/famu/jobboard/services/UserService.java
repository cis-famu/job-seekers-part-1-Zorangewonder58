package edu.famu.jobboard.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.jobboard.models.users.Users;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class UserService {

    private Firestore firestore;

    public UserService()
    {
        this.firestore = FirestoreClient.getFirestore();
    }

    public Users documentSnapshotToUser(DocumentSnapshot document)
    {
        if(document.exists())
            return document.toObject(Users.class);

        return null;
    }

    public Users getUserById(String Userid) throws ExecutionException, InterruptedException
    {
        DocumentReference userRef = firestore.collection("User").document(Userid);
        ApiFuture<DocumentSnapshot> future = userRef.get();

        DocumentSnapshot documentSnapshot = future.get();

        return documentSnapshotToUser(documentSnapshot);
    }
}
