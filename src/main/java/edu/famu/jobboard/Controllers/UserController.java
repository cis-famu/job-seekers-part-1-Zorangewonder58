package edu.famu.jobboard.Controllers;

import com.google.cloud.firestore.WriteResult;
import edu.famu.jobboard.models.jobs.Jobs;
import edu.famu.jobboard.models.users.Users;
import edu.famu.jobboard.services.JobService;
import edu.famu.jobboard.services.UserService;
import edu.famu.jobboard.util.ApiResponseFormat;
import edu.famu.jobboard.util.Utility;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/users")
@Tag(name="Users", description = "Retrieves information related to user")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService)
    {
        this.userService = userService;
    }



    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseFormat<Users>> getUserbyId(@PathVariable(name="user_id")String id)
    {
        try {
            Users users = userService.getUserById(id);

            if(users != null) {
                return ResponseEntity.ok(new ApiResponseFormat<>(true, "Users found ", users, null));
            }
            else
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponseFormat<>(false, "User not found", null, null));
        } catch (ExecutionException  | InterruptedException e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error retrieving user", null, e));
        }

    }



}
