package edu.famu.jobboard.models.jobs;

import com.google.cloud.firestore.DocumentReference;
import edu.famu.jobboard.models.applications.Applications;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class RestJobs extends AJobs{

    private List<DocumentReference> applications;

    public RestJobs(List<DocumentReference> applications) {
        this.applications = applications;
    }
}
