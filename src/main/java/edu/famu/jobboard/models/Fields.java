package edu.famu.jobboard.models;

import jakarta.annotation.Nullable;

import java.time.LocalDateTime;
import java.util.Date;

public class Fields {
    private @Nullable String jobId;
    private String jobTitle;
    private String company;
    private String jobLocation;

    private String jobDescription;

    private String jobRequirements;

    private LocalDateTime postedAt;

    private Date expiryDate;

    private List<Applications> applications;
    private int views;

}
