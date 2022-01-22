package r6guidebackend.models.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class GetMapOfWeaponsResponse {
    private Map<String, String> nameAndType;

    public GetMapOfWeaponsResponse() {
        nameAndType = new HashMap<>();
    }
}
