package r6guidebackend.models.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GetAllNamesResponse {
    private List<String> names;
}
