# Job Board Full Stack Site

**Objective:** Develop a comprehensive Job Board web application with features inspired by platforms like Indeed, providing a seamless experience for both employers and job seekers.

## Part 1 - Database and Models

**NoSQL Database Design:**
1. **Collections:**
    - **Jobs:**
        - Fields:
            - `jobId` (unique identifier)
            - `title` (job title)
            - `company` (employer/company name)
            - `location` (job location)
            - `description` (job description)
            - `requirements` (required skills/qualifications)
            - `postedAt` (date and time the job was posted)
            - `expiryDate` (optional, date the job listing expires)
            - `applications` (array of application objects)
            - `views` (number of views)

    - **Users:**
        - Fields:
            - `userId` (unique identifier)
            - `username` (user's username)
            - `email` (user's email)
            - `role` (either "employer" or "jobseeker")
            - `resume` (file location)
            - `applications` (array of application objects)

    - **Applications:**
        - Fields:
            - `applicationId` (unique identifier)
            - `jobId` (reference to the applied job)
            - `userId` (reference to the applicant)
            - `appliedAt` (date and time of application)

    - **SavedJobs:**
        - Fields:
            - `savedJobId` (unique identifier)
            - `jobId` (reference to the saved job)
            - `userId` (reference to the jobseeker)

    - **JobViews:**
        - Fields:
            - `jobViewId` (unique identifier)
            - `jobId` (reference to the viewed job)
            - `userId` (reference to the user who viewed the job)
            - `viewedAt` (date and time of the view)

2. **Relationships:**
    - Each job listing is associated with the employer's user account.
    - Users (employers and jobseekers) maintain an array of applications and saved jobs.
    - JobViews collection tracks the number of views for each job listing.


### Required Stories

- Create a Firebase database for your application.  ***(5 points)***
    - The names of class fields should match the names presented in the above (this includes casing)
    - Include sample data *(minimum of 5 records per collection)*
    - **Submission**: Include a recording of your database showing all collections and records.

- Create all the appropriate models ***(11 points)***
  - Include the appropriate abstract and REST models, as needed.

Here is a link to the [Markdown Cheatsheet](https://www.markdownguide.org/cheat-sheet/) to update your README file.
