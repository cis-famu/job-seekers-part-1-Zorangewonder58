package edu.famu.jobboard.models;

import jakarta.annotation.Nullable;

import java.time.LocalDateTime;

public class JobViews {
    private @Nullable String jobViewId;

    private String jobId;

    private String userId;

    private LocalDateTime viewedAt;


}
