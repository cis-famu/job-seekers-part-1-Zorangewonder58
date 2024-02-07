package edu.famu.jobboard.models.users;

import com.google.cloud.firestore.DocumentReference;
import edu.famu.jobboard.models.applications.Applications;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class RestUsers extends AUsers {

    private List<DocumentReference> applications;

    public RestUsers(List<DocumentReference> applications) {
        this.applications = applications;
    }
}
