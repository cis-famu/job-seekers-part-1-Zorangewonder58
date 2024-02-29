package edu.famu.jobboard.util;

public record ApiResponseFormat<T extends Object>(boolean success, String message, T data, Object error) {}
