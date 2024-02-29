package edu.famu.jobboard.models.jobs;


import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AJobs {

    private @Nullable String jobId;
    private String jobTitle;
    private String company;
    private String jobLocation;

    private String jobDescription;

    private String jobRequirements;

    private LocalDateTime postedAt;

    private Date expiryDate;

}
