package edu.famu.jobboard.models.users;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.protobuf.util.Timestamps;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public abstract class AUsers {
@DocumentId
    protected @Nullable String userId;

    protected String username;
    protected String email;

    protected String role;

    protected String resumefileLocation;


}
