# Job Board Full Stack Site

**Objective:** Develop a comprehensive Job Board web application with features inspired by platforms like Indeed, providing a seamless experience for both employers and job seekers.

## Part 2 - Services and Controllers I

In this submission we continue to build the backend API for our application.
### Required Stories

- Create the following endpoints:  ***(14 points)***
    - `GET api/jobs/` - Get all jobs
    - `GET api/jobs /{id}` - Get a job by ID
    - `GET api/jobs/company/{id}` - Get a job by the company
    - `GET api/users/{id}` - Get a user by ID
    - `GET api/app/{id}` - Get an application by ID
    - `GET api/app/job/{id}` - Get all applicantions by ID
    - `GET api/jobs/saved/{id}` - Get all the saved jobs by user
    - **Submission**: Include a recording of you performing each `GET` in Postman in README. ***Each student's sample data should be unique.***
- Document your endpoints using SwaggerUI. **(7 points)** 
   - **Submission**: Include a recording in README.
- **Bonus (5 points)**
  - Add a filter option (query parameter) to `GET api/jobs/` and `GET api/jobs/company/{id}` where the user can decide if the only want to see jobs that are not expired.
   - **Submission**: Include a recording of the call in Postman in README.

Here is a link to the [Markdown Cheatsheet](https://www.markdownguide.org/cheat-sheet/) to update your README file.
