package edu.famu.jobboard.Controllers;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.database.annotations.Nullable;
import edu.famu.jobboard.models.applications.Applications;
import edu.famu.jobboard.models.jobs.Jobs;
import edu.famu.jobboard.models.savedjobs.SavedJobs;
import edu.famu.jobboard.services.ApplicationService;
import edu.famu.jobboard.services.JobService;
import edu.famu.jobboard.util.ApiResponseFormat;
import edu.famu.jobboard.util.Utility;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/app")
@Tag(name="Applications", description = "Retrieves information related to application")
public class ApplicationController {

    private final ApplicationService applicationService;

    private Firestore firestore;

    public ApplicationController(ApplicationService applicationService)
    {
        this.applicationService = applicationService;
    }

    public Applications documentSnapshotToApplications(DocumentSnapshot document)
    {
        if(document.exists())
            return document.toObject(Applications.class);

        return null;
    }

    @Nullable
    private List<Applications> getApplicationsList(Query query) throws InterruptedException, ExecutionException {
        ApiFuture<QuerySnapshot> future = query.get();
        List<QueryDocumentSnapshot> documentSnapshots = future.get().getDocuments();
        List<Applications> applicationList = documentSnapshots.size() == 0 ? null : new ArrayList<>();

        for(DocumentSnapshot document : documentSnapshots)
        {
            applicationList.add(documentSnapshotToApplications(document));
        }

        return applicationList;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseFormat<Applications>> getApplicationById(@PathVariable(name="application_id")String id)
    {
        try {
            Applications applications = applicationService.getApplicationById(id);

            if(applications != null) {
                return ResponseEntity.ok(new ApiResponseFormat<>(true, "Application found ",applications, null));
            }
            else
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponseFormat<>(false, "Applications not found", null, null));
        } catch (ExecutionException  | InterruptedException e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error retrieving Applications", null, e));
        }

    }

    @GetMapping("/app/job/{id}")
    public ResponseEntity<ApiResponseFormat<List<Applications>>> getApplicationbyJob(@PathVariable(name="job_id") String id)
    {
        try {
            List<Applications> applicationsList = applicationService.getApplicationsByJobId(id);
            return ResponseEntity.ok(new ApiResponseFormat<>(true, "Application returned successfully", applicationsList, null));
        } catch (InterruptedException | ExecutionException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Unsuccessful", null, e.getMessage()));
        }
    }

}
