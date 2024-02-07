package edu.famu.jobboard.models.applications;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.firebase.database.annotations.Nullable;
import com.google.protobuf.util.Timestamps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Aapplications {

    @DocumentId
    private @Nullable String appId;

    private LocalDateTime appliedAt;






}
