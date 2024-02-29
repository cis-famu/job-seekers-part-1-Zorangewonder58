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

public class AUsers {
@DocumentId
    private @Nullable String userId;

    private String username;
    private String email;

    private String role;

    private String resumefileLocation;


}
