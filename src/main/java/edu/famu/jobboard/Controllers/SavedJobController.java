package edu.famu.jobboard.Controllers;

import com.google.cloud.firestore.WriteResult;
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
            @ApiResponse(responseCode = "204", description = "no savedjobs found"),
            @ApiResponse(responseCode = "500", description = "Unable to retrieve jobs",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseFormat.class)))
    })
    @GetMapping("/users/{id}")
    public ResponseEntity<ApiResponseFormat<List<SavedJobs>>> getSavedJobByUser(@PathVariable(name="user_id") String id)
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






}
