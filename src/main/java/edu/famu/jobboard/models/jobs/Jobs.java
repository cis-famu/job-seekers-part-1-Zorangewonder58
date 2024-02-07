package edu.famu.jobboard.models.jobs;

import edu.famu.jobboard.models.applications.Applications;
import jakarta.annotation.Nullable;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Jobs {
    private @Nullable String jobId;
    private String jobTitle;
    private String company;
    private String jobLocation;

    private String jobDescription;

    private String jobRequirements;

    private LocalDateTime postedAt;

    private Date expiryDate;


    private int views;

}
