package edu.famu.jobboard.models.jobs;

import com.google.cloud.firestore.DocumentReference;
import edu.famu.jobboard.models.applications.Applications;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestJobs extends AJobs{

    private List<DocumentReference> applications;






}
