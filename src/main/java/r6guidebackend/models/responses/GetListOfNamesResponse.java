package r6guidebackend.models.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GetListOfNamesResponse {
    private List<String> names;

    public GetListOfNamesResponse() {
        names = new ArrayList<>();
    }
}
