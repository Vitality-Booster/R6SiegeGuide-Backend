package r6guidebackend.models.responses;

import lombok.Getter;
import lombok.Setter;
import r6guidebackend.models.previews.GadgetPreview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class GetListOfGadgetPreviewsResponse {

    // with this class I return a Map of gadgets where 'key' is gadget 'name'
    // and 'value' is gadget 'type'

    // it is done so that I could filter gadgets by type on the client side

    private List<GadgetPreview> namesAndTypes;

    public GetListOfGadgetPreviewsResponse() {
        namesAndTypes = new ArrayList<>();
    }
}
