# Job Board Full Stack Site

**Objective:** Develop a comprehensive Job Board web application with features inspired by platforms like Indeed, providing a seamless experience for both employers and job seekers.

## Part 1 - Database and Models

**Submission Instructions**:

Once you've completed the required user stories for your project, take the following steps to get your project ready to submit.

1. Push Code to Github Repository
    - If this week's assignment is starting a new project. However, if the week's assignment is a continuation of the last week's project, you will NOT create a new project. Copy the project files into the new repository folder and make an initial commit.

2. Create a README Template
    - Every submission must be accompanied by a README.md using the readme template provided with the assignment to demonstrate which required and optional tasks you've completed.
    - In your project repository, add a README.md file in the root directory that contains the contents similar to the [README template](https://hackmd.io/@vacoote89/H17PTNecT/edit).
    - If the assignment is a continuation of the same project from a previous week, add the new template to the end of the previous README instead of creating a new file.
    - Make sure to check off the user stories you've completed.

3. GIF Walkthrough
    - Your README.md must contain a GIF walkthrough using a recording program of your choice. It should demonstrate how it works with the user stories completed.
    - I recommend [Kap](https://getkap.co/) for macOS, [ScreenToGif](https://www.screentogif.com/) for Windows, and [peek](https://github.com/phw/peek) for Linux.
    - [Imgur](https://imgur.com/upload) is a great service for hosting the GIF walkthrough and then linking to it from
      the README.
    - When using Imgur, you can right-click on the gif and choose "Copy Image Address" to get the correct address. Make sure the address has a `.gif` extension. If you end up with a url that has a `.gifv` extension, removing the `v` and changing this to `.gif` will ensure the gif renders on GitHub.

4. Make sure you've pushed all your latest code up to GitHub
    - To check this, you can browse your repository on GitHub on your favorite browser to make sure some of your latest changes are present there.


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