package r6guidebackend.models.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class GetMapOfGadgetsResponse {

    // with this class I return a Map of gadgets where 'key' is gadget 'name'
    // and 'value' is gadget 'type'

    // it is done so that I could filter gadgets by type on the client side

    private Map<String, String> nameAndTypes;

    public GetMapOfGadgetsResponse() {
        nameAndTypes = new HashMap<>();
    }
}
