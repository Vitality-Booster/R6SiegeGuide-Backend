package r6guidebackend.models.previews;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import r6guidebackend.models.Gadget;

@Getter
@Setter
@NoArgsConstructor
public class GadgetPreview {
    private String name;
    private String type;

    public GadgetPreview(Gadget gadget) {
        this.name = gadget.getName();
        this.type =  gadget.getType();
    }
}
