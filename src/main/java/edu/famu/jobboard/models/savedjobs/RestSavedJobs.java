package edu.famu.jobboard.models.savedjobs;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.annotation.DocumentId;
import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RestSavedJobs extends ASavedJobs {

    protected DocumentReference jobId;

    protected DocumentReference userId;

    public RestSavedJobs(DocumentReference jobId, DocumentReference userId) {
        this.jobId = jobId;
        this.userId = userId;
    }

    public RestSavedJobs(@Nullable String savedJobId, DocumentReference jobId, DocumentReference userId) {
        this.jobId = jobId;
        this.userId = userId;
    }
}
