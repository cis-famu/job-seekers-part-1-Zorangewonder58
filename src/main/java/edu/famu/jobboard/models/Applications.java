package edu.famu.jobboard.models;

import jakarta.annotation.Nullable;

import java.time.LocalDateTime;

public class Applications {

    private @Nullable String appId;

    private String jobId;

    private String userId;

    private LocalDateTime appliedAt;
}
