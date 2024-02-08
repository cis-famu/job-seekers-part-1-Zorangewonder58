package edu.famu.jobboard.models.jobviews;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.annotation.DocumentId;
import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class RestJobViews extends AJobViews{

    private DocumentReference jobId;

    private DocumentReference userId;

    public RestJobViews(DocumentReference jobId, DocumentReference userId) {
        this.jobId = jobId;
        this.userId = userId;
    }

    public RestJobViews(@Nullable String jobViewId, LocalDateTime viewedAt, DocumentReference jobId, DocumentReference userId) {
        super(jobViewId, viewedAt);
        this.jobId = jobId;
        this.userId = userId;
    }
}
