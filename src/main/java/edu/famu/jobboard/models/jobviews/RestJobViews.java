package edu.famu.jobboard.models.jobviews;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.annotation.DocumentId;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RestJobViews extends AJobViews{

    private DocumentReference jobId;

    private DocumentReference userId;

    public RestJobViews(DocumentReference jobId, DocumentReference userId) {
        this.jobId = jobId;
        this.userId = userId;
    }
}
