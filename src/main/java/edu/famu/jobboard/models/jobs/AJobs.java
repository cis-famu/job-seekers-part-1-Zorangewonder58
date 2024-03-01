package edu.famu.jobboard.models.jobs;


import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AJobs {

    @DocumentId
    protected @Nullable String jobId;
    protected String jobTitle;
    protected String company;
    protected String jobLocation;

    protected String jobDescription;

    protected String jobRequirements;

    protected Timestamp postedAt;

    protected Timestamp expiryDate;

}
