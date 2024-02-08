package edu.famu.jobboard.models.jobs;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.protobuf.util.Timestamps;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AJobs {

    private @Nullable String jobId;
    private String jobTitle;
    private String company;
    private String jobLocation;

    private String jobDescription;

    private String jobRequirements;

    private LocalDateTime postedAt;

    private Date expiryDate;


    private int views;

    public AJobs(@Nullable String jobId, String jobTitle, String company, String jobLocation, String jobDescription, String jobRequirements, LocalDateTime postedAt, Date expiryDate, int views) {
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.company = company;
        this.jobLocation = jobLocation;
        this.jobDescription = jobDescription;
        this.jobRequirements = jobRequirements;
        this.postedAt = postedAt;
        this.expiryDate = expiryDate;
        this.views = views;
    }
}
