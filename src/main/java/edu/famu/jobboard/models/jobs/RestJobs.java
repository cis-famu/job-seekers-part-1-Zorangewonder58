package edu.famu.jobboard.models.jobs;

import com.google.cloud.firestore.DocumentReference;
import edu.famu.jobboard.models.applications.Applications;
import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class RestJobs extends AJobs{

    private List<DocumentReference> applications;

    public RestJobs(List<DocumentReference> applications) {
        this.applications = applications;
    }

    public RestJobs(@Nullable String jobId, String jobTitle, String company, String jobLocation, String jobDescription, String jobRequirements, LocalDateTime postedAt, Date expiryDate, int views, List<DocumentReference> applications) {
        super(jobId, jobTitle, company, jobLocation, jobDescription, jobRequirements, postedAt, expiryDate, views);
        this.applications = applications;
    }


}
