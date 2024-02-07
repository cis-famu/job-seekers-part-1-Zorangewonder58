package edu.famu.jobboard.models.jobviews;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.protobuf.util.Timestamps;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AJobViews {
    @DocumentId
    private @Nullable String jobViewId;
    private LocalDateTime viewedAt;
}
