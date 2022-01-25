package r6guidebackend.models.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateSingleGadgetRequest {
    private String type;
    private String description;
}
