package edu.famu.jobboard.Controllers;

import com.google.cloud.firestore.WriteResult;
import edu.famu.jobboard.models.applications.Applications;
import edu.famu.jobboard.models.jobs.Jobs;
import edu.famu.jobboard.models.savedjobs.SavedJobs;
import edu.famu.jobboard.services.JobService;
import edu.famu.jobboard.services.SavedJobService;
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

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/jobs/saved")
@Tag(name="SavedJob", description = "Retrieves information related to Savedjobs")
public class SavedJobController {

    private final SavedJobService savedJobService;

    public SavedJobController(SavedJobService savedJobService)
    {
        this.savedJobService = savedJobService;
    }


    @Operation(summary = "Get a list of all the SavedJobs", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SavedJobs found"),
            @ApiResponse(responseCode = "204", description = "no Savedjobs found"),
            @ApiResponse(responseCode = "500", description = "Unable to retrieve Savedjobs",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseFormat.class)))
    })

    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "SavedJob details by user", required = true, useParameterTypeSchema = true)
    @GetMapping(path = "/{id}")
    public ResponseEntity<ApiResponseFormat<List<SavedJobs>>> getSavedJobByUser(@PathVariable(name="id") String id)
    {
        try {
            List<SavedJobs> savedJobsList = savedJobService.getSavedJobsByUser(id);
            return ResponseEntity.ok(new ApiResponseFormat<>(true, "Saved Jobs returned successfully", savedJobsList, null));
        }catch (ExecutionException | InterruptedException e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Unsuccessful", null, e.getMessage()));
        }
    }

    // - `POST api/jobs/saved/` - Save a particular job for a particular user
    @Operation(summary = "Save a particular job for a particular user", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Job successfully saved"),
            @ApiResponse(responseCode = "500", description = "Job was not able to be saved",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseFormat.class)))

    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Delete Application", required = true, useParameterTypeSchema = true)
    @PostMapping(path="/")
    public ResponseEntity<ApiResponseFormat<String>> SaveJob(@RequestBody SavedJobs savedJobs)
    {
        try
        {
            String id = savedJobService.addSavedJob(savedJobs);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponseFormat<>(true, "Job successfully saved", id, null));

        }catch(ExecutionException | InterruptedException e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error deleting job", null, e));
        }
    }

    //- `DELETE api/jobs/saved/` - Delete a particular save job for a particular user
    @Operation(summary = "Delete a particular Saved job for a particular user", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Job successfully deleted"),
            @ApiResponse(responseCode = "500", description = "Job was not able to be deleted",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseFormat.class)))

    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Delete SavedJob", required = true, useParameterTypeSchema = true)
    @PostMapping(path="/{Jobid}/{Userid}")
    public ResponseEntity<ApiResponseFormat<WriteResult>> DeleteJob(@PathVariable(name = "Jobid")String Jobid, @PathVariable(name = "Userid")String Userid)
    {
        try
        {
            WriteResult result = savedJobService.removeSavedJob(Jobid, Userid);

            return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponseFormat<>(false, "Job successfully deleted", result, null));

        }catch(ExecutionException | InterruptedException e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error deleting job", null, e));
        }
    }






}
