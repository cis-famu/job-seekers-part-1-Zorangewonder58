package edu.famu.jobboard.models.savedjobs;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.annotation.DocumentId;
import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RestSavedJobs extends ASavedJobs {

    private DocumentReference jobId;

    private DocumentReference userId;

    public RestSavedJobs(DocumentReference jobId, DocumentReference userId) {
        this.jobId = jobId;
        this.userId = userId;
    }
}
