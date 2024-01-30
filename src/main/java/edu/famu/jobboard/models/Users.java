package edu.famu.jobboard.models;

import jakarta.annotation.Nullable;

public class Users {
    private @Nullable String userId;

    private String username;
    private String email;

    private String role;

    private String resumefileLocation;

    private List<Applications> applications;
}
