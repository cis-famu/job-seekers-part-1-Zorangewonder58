package edu.famu.jobboard.models.applications;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.firebase.database.annotations.Nullable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class RestApplications extends Aapplications {

    protected DocumentReference jobId;


    protected DocumentReference userId;

    public RestApplications(DocumentReference jobId, DocumentReference userId, String appId, LocalDateTime appliedAt) {
        super(appId, appliedAt);
        this.jobId = jobId;
        this.userId = userId;

    }
}
