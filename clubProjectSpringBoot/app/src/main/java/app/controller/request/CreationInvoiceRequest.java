
package app.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CreationInvoiceRequest {
    private String item;
    private String description;
    private String amount;
    private long userSesion;
}
