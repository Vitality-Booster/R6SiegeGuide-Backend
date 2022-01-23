package r6guidebackend.models.responses;

import lombok.Getter;
import lombok.Setter;
import r6guidebackend.models.previews.WeaponPreview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class GetListOfWeaponPreviewsResponse {
    private List<WeaponPreview> namesAndTypes;

    public GetListOfWeaponPreviewsResponse() {
        namesAndTypes = new ArrayList<>();
    }
}
