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
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Operation(summary = "Get a list of all the Application", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Application found"),
            @ApiResponse(responseCode = "204", description = "no applications found"),
            @ApiResponse(responseCode = "500", description = "Unable to retrieve applications",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseFormat.class)))
    })

    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Application details by Id", required = true, useParameterTypeSchema = true)
    @GetMapping(path = "/{id}")
    public ResponseEntity<ApiResponseFormat<Applications>> getApplicationById(@PathVariable(name="id")String id)
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

    @Operation(summary = "Get a list of all the Jobs", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Jobs found"),
            @ApiResponse(responseCode = "204", description = "no jobs found"),
            @ApiResponse(responseCode = "500", description = "Unable to retrieve jobs",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseFormat.class)))
    })

    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Application details by job", required = true, useParameterTypeSchema = true)
    @GetMapping(path ="/job/{id}")
    public ResponseEntity<ApiResponseFormat<List<Applications>>> getApplicationbyJob(@PathVariable(name="id") String id)
    {
        try {
            List<Applications> applicationsList = applicationService.getApplicationsByJobId(id);
            return ResponseEntity.ok(new ApiResponseFormat<>(true, "Application returned successfully", applicationsList, null));
        } catch (InterruptedException | ExecutionException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Unsuccessful", null, e.getMessage()));
        }
    }

    //POST api/app/` - Create an application
    @Operation(summary = "Create an application", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Application successfully created"),
            @ApiResponse(responseCode = "500", description = "Application was not able to be created",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseFormat.class)))

    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Create Application", required = true, useParameterTypeSchema = true)
    @PostMapping(path="/")
    public ResponseEntity<ApiResponseFormat<String>> createApplication(@RequestBody Applications applications)
    {
        try
        {
            String id = applicationService.createApp(applications);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponseFormat<>(true, "Application successfully created", id, null));
        }
        catch(ExecutionException | InterruptedException e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error creating Application.", null, e));
        }
    }

    // - `DELETE api/app/{id}` - Delete an applicantion by ID
    @Operation(summary = "Delete an application", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Application successfully deleted"),
            @ApiResponse(responseCode = "500", description = "Application was not able to be deleted",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseFormat.class)))

    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Delete Application", required = true, useParameterTypeSchema = true)
    @PostMapping(path="/{id}")
    public ResponseEntity<ApiResponseFormat<WriteResult>> deleteApp(@PathVariable("id") String id)
    {
        try
        {
            WriteResult result = applicationService.deleteApp(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponseFormat<>(false, "App successfully deleted", result, null));

        }catch(ExecutionException | InterruptedException e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error deleting job", null, e));
        }
    }


}
