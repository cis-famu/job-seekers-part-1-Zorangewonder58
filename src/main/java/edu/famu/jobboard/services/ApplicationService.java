package edu.famu.jobboard.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.annotations.Nullable;
import edu.famu.jobboard.models.applications.Applications;
import edu.famu.jobboard.models.jobs.Jobs;
import edu.famu.jobboard.util.Utility;
import jdk.jshell.execution.Util;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service

public class ApplicationService {
    private Firestore firestore;

    public ApplicationService()
    {
        this.firestore = FirestoreClient.getFirestore();
    }

    @Nullable
    public Applications documentSnapshotToApplication(DocumentSnapshot document)
    {
        if(document.exists())
            return document.toObject(Applications.class);

        return null;
    }

    @Nullable
    private List<Applications> getApplicationList(Query query) throws InterruptedException, ExecutionException {
        ApiFuture<QuerySnapshot> future = query.get();
        List<QueryDocumentSnapshot> documentSnapshots = future.get().getDocuments();
        List<Applications> applicationList = documentSnapshots.size() == 0 ? null : new ArrayList<>();

        for(DocumentSnapshot document : documentSnapshots)
        {
            applicationList.add(documentSnapshotToApplication(document));
        }

        return applicationList;
    }

    public Applications getApplicationById(String ApplicationId) throws ExecutionException, InterruptedException {
        DocumentReference AppRef = firestore.collection("Applications").document(ApplicationId);//Select * FROM Users Where id = 'userId'
        ApiFuture<DocumentSnapshot> future = AppRef.get();

        DocumentSnapshot documentSnapshot = future.get();

        return documentSnapshotToApplication(documentSnapshot);
    }

    public List<Applications> getApplicationsByJobId(String jobId) throws  ExecutionException, InterruptedException
    {
        DocumentReference AppRef = Utility.retrieveDocumentReference("Applications", jobId);
        Query query = firestore.collection("Applications").whereEqualTo("JobId", jobId);
        return getApplicationList(query);

    }

}
