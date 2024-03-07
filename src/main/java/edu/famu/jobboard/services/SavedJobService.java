package edu.famu.jobboard.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.annotations.Nullable;
import edu.famu.jobboard.models.jobs.Jobs;
import edu.famu.jobboard.models.savedjobs.SavedJobs;
import edu.famu.jobboard.util.Utility;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class SavedJobService {

    private Firestore firestore;

    public SavedJobService()
    {
        this.firestore = FirestoreClient.getFirestore();
    }

    public SavedJobs documentSnapshotToSavedJob(DocumentSnapshot document)
    {
        if(document.exists())
            return document.toObject(SavedJobs.class);

        return null;
    }

    @Nullable
    private List<SavedJobs> getSavedJobsList(Query query) throws InterruptedException, ExecutionException {
        ApiFuture<QuerySnapshot> future = query.get();
        List<QueryDocumentSnapshot> documentSnapshots = future.get().getDocuments();
        List<SavedJobs> savedJobsList = documentSnapshots.size() == 0 ? null : new ArrayList<>();

        for(DocumentSnapshot document : documentSnapshots)
        {
            savedJobsList.add(documentSnapshotToSavedJob(document));
        }

        return savedJobsList;
    }

    public List<SavedJobs> getSavedJobsByUser(String UserId) throws ExecutionException, InterruptedException
    {
        DocumentReference SavedJobRef = Utility.retrieveDocumentReference("Users", UserId);
        Query query = firestore.collection("SavedJobs").whereEqualTo("userId", SavedJobRef);
        return getSavedJobsList(query);

    }


    //Save a particular job for a particular user
    /*public String addSavedJob(String JobId, String UserId) throws ExecutionException,InterruptedException
    {
        Query query = firestore.collection("Jobviews").whereEqualTo("JobId", JobId).whereEqualTo("UserId", UserId);

        CollectionReference savedJobsCollection = firestore.collection("SavedJobs");

        List<SavedJobs> NewJob = getSavedJobsList(query);

        ApiFuture<DocumentReference> future = savedJobsCollection.add(NewJob);

        DocumentReference docRef = future.get();

        return docRef.getId();
    }*/

    public String addSavedJob(SavedJobs savedJobs) throws ExecutionException,InterruptedException
    {
        //Query query = firestore.collection("Jobviews").whereEqualTo("JobId", JobId).whereEqualTo("UserId", UserId);

        CollectionReference savedJobsCollection = firestore.collection("SavedJobs");

        ApiFuture<DocumentReference> future = savedJobsCollection.add(savedJobs);

        DocumentReference docRef = future.get();

        return docRef.getId();
    }

    //Delete a particular save job for a particular user
    public WriteResult removeSavedJob(String id, String UserId) throws ExecutionException, InterruptedException {

        DocumentReference jobRef =firestore.collection("SavedJobs").document(id);

        DocumentReference userRef = firestore.collection("Users").document(UserId);

        Query query = firestore.collection("SavedJobs").whereEqualTo("userId", userRef).whereEqualTo("SavedJobId", jobRef);

        DocumentReference savedJobRef = query.get().get().getDocuments().get(0).getReference();

        ApiFuture<WriteResult> result = savedJobRef.delete();

        return result.get();
    }
}
