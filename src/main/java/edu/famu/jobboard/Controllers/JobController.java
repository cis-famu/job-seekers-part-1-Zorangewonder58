package edu.famu.jobboard.Controllers;

import com.google.cloud.firestore.WriteResult;
import edu.famu.jobboard.models.jobs.Jobs;
import edu.famu.jobboard.models.savedjobs.SavedJobs;
import edu.famu.jobboard.services.JobService;
import edu.famu.jobboard.util.ApiResponseFormat;
import edu.famu.jobboard.util.Utility;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/jobs")//Base URL: http://localhost:8080/api/users
@Tag(name="Jobs", description = "Retrieves information related to jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService)
    {
        this.jobService = jobService;
    }

    @Operation(summary = "Get a list of all the Jobs", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Jobs found"),
            @ApiResponse(responseCode = "204", description = "no jobs found"),
            @ApiResponse(responseCode = "500", description = "Unable to retrieve jobs",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseFormat.class)))
    })

    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Job details", required = true, useParameterTypeSchema = true)
    @GetMapping(path = "/")
    public ResponseEntity<ApiResponseFormat<List<Jobs>>> getAllJobs(@RequestParam(required = false) Boolean isExpired)
    {
        try
        {
          List<Jobs> jobsList;


          if(isExpired != null && isExpired)
          {
              jobsList = jobService.getNonExpiredJobs();
          }else
              jobsList = jobService.getallJobs();

          return ResponseEntity.ok(new ApiResponseFormat<>(true, "Job retrieved successfully", jobsList, null));
        }catch(ExecutionException | InterruptedException e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Unsuccessful", null, e.getMessage()));
        }
    }

    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Job details by Id", required = true, useParameterTypeSchema = true)
    @GetMapping(path= "/{job_id}")
    public ResponseEntity<ApiResponseFormat<Jobs>> getJobById(@PathVariable(name="job_id")String id)
    {
        try {
            Jobs job = jobService.getJobById(id);

            if(job != null) {
                return ResponseEntity.ok(new ApiResponseFormat<>(true, "Job found ", job, null));
            }
            else
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponseFormat<>(false, "Jobs not found", null, null));
        } catch (ExecutionException  | InterruptedException e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error retrieving jobs", null, e));
        }

    }

    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Job details by company", required = true, useParameterTypeSchema = true)
    @GetMapping(path = "/company/{company}")
    public ResponseEntity<ApiResponseFormat<List<Jobs>>> getJobByCompany(@RequestParam(required = false) Boolean isExpired,@PathVariable(name="company") String company)
    {
        try {
            List<Jobs> jobsList;

            if(isExpired != null && isExpired)
            {
                jobsList = jobService.getNonExpiredJobsByCompany(company);
            }else
               jobsList = jobService.getJobsByCompany(company);


            return ResponseEntity.ok(new ApiResponseFormat<>(true, "Jobs returned successfully", jobsList, null));
        }catch (ExecutionException | InterruptedException e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Unsuccessful", null, e.getMessage()));
        }
    }




    @Operation(summary = "Create a new Job", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Jobs successfully created"),
            @ApiResponse(responseCode = "500", description = "Unable to create job",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseFormat.class)))
    })

    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Add Job details", required = true, useParameterTypeSchema = true)
    @PostMapping(path = "/", produces = Utility.DEFAULT_MEDIA_TYPE, consumes = Utility.DEFAULT_MEDIA_TYPE)
    public ResponseEntity<ApiResponseFormat<String>> addJobs(@RequestBody Jobs jobs)
    {
        try {
            String id = jobService.createJob(jobs);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponseFormat<>(true, "Job successfully created", id, null));
        }catch(ExecutionException | InterruptedException e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error creating Job.", null, e));
        }

    }

    @Operation(description = "Update job details", method="PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Jobs successfully updated"),
            @ApiResponse(responseCode = "500", description = "Unable to create job",
                    content = @Content(mediaType = Utility.DEFAULT_MEDIA_TYPE,
                            schema = @Schema(implementation = ApiResponseFormat.class)))
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Update Job details details", required = true, useParameterTypeSchema = true)
    @PutMapping(path = "/{job_id}", produces = Utility.DEFAULT_MEDIA_TYPE, consumes = Utility.DEFAULT_MEDIA_TYPE)
    public ResponseEntity<ApiResponseFormat<WriteResult>> updateJob(@PathVariable("job_id") String id, @RequestBody Map<String, Object> updateValues)
    {
        //Two different types of ways to pass a value in one method

        //Get id of the user from url
        try {
            WriteResult result = jobService.updateJob(id, updateValues);//function throws Exeception to get caught in this class to limit the try catches

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponseFormat<>(false, "Job successfully updated", result, null));
        }catch(ExecutionException | InterruptedException e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error updating job", null, e));

        }
    }

}
