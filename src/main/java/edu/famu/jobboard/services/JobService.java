package edu.famu.jobboard.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.annotations.Nullable;
import edu.famu.jobboard.models.jobs.Jobs;
import edu.famu.jobboard.util.Utility;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.sql.Time;
import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class JobService {

    private Firestore firestore;

    public JobService()
    {
        this.firestore = FirestoreClient.getFirestore();
    }

    public Jobs documentSnapshotToJob(DocumentSnapshot document)
    {
        if(document.exists())
            return document.toObject(Jobs.class);

        return null;
    }

    @Nullable
    private List<Jobs> getJobList(Query query) throws InterruptedException, ExecutionException {
        ApiFuture<QuerySnapshot> future = query.get();
        List<QueryDocumentSnapshot> documentSnapshots = future.get().getDocuments();
        List<Jobs> jobList = documentSnapshots.size() == 0 ? null : new ArrayList<>();

        for(DocumentSnapshot document : documentSnapshots)
        {
            jobList.add(documentSnapshotToJob(document));
        }

        return jobList;
    }

    public List<Jobs> getallJobs() throws ExecutionException, InterruptedException
    {
        CollectionReference jobCollection = firestore.collection("Jobs");
        ApiFuture<QuerySnapshot> future = jobCollection.get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();


        List<Jobs> jobsList = documents.isEmpty() ? null : new ArrayList<>();// if document size is zero, return null;

        for(DocumentSnapshot document: documents)//foreach
        {
            jobsList.add(documentSnapshotToJob(document));
        }
        return jobsList;
    }

    public List<Jobs> getNonExpiredJobs() throws ExecutionException, InterruptedException
    {
        Timestamp currentDate = Timestamp.now();

        Query query = firestore.collection("Jobs").whereGreaterThan("expiryDate", currentDate);
        return getJobList(query);
    }

    public Jobs getJobById(String JobId) throws ExecutionException, InterruptedException {
        DocumentReference JobRef = firestore.collection("Jobs").document(JobId);//Select * FROM Users Where id = 'userId'
        ApiFuture<DocumentSnapshot> future = JobRef.get();

        DocumentSnapshot documentSnapshot = future.get();

        return documentSnapshotToJob(documentSnapshot);
    }

    public List<Jobs> getJobsByCompany(String company) throws ExecutionException, InterruptedException
    {
        //DocumentReference JobRef = Utility.retrieveDocumentReference("Jobs", company);
        Query query = firestore.collection("Jobs").whereEqualTo("company", company);
        return getJobList(query);

    }

    public List<Jobs> getNonExpiredJobsByCompany(String company) throws ExecutionException, InterruptedException
    {
        Timestamp currentDate = Timestamp.now();

        Query query = firestore.collection("Jobs")
                .whereEqualTo("company", company)
                .whereGreaterThan("expiryDate", currentDate);

        return getJobList(query);
    }

    //`POST api/jobs/` - Create a job
    public String createJob(Jobs job) throws ExecutionException, InterruptedException
    {
        CollectionReference jobsCollection = firestore.collection(("Jobs"));

        ApiFuture<DocumentReference> future = jobsCollection.add(job);

        DocumentReference docRef = future.get();

        return docRef.getId();
    }

    //PUT api/jobs/{id}` - Update a job by ID
    public WriteResult updateJob(String id, Map<String, Object> updateFields) throws ExecutionException, InterruptedException {
        String[] allowed = {"applications", "company", "expiryDate", "jobDescription", "jobLocation", "jobRequirements", "jobTitle", "postedAt", "views"};

        List<String> allowedFields = Arrays.asList(allowed);

        Map<String, Object> formattedValues = new HashMap<>();


        for(Map.Entry<String, Object> entry : updateFields.entrySet())
        {
            String key = entry.getKey();

            if(allowedFields.contains(key))
                formattedValues.put(key, entry.getValue());
        }

        DocumentReference userDoc = firestore.collection("Jobs").document(id);
        ApiFuture<WriteResult> result = userDoc.update((formattedValues));

        return result.get();
    }

    //DELETE api/jobs/{id}` - Delete a job
    public WriteResult removeJob(String id) throws ExecutionException, InterruptedException {
        DocumentReference JobRef =firestore.collection("Jobs").document(id);




        ApiFuture<WriteResult> result = JobRef.delete(); //If no refrences, just need this line and refrence line
        return result.get();
    }


}
