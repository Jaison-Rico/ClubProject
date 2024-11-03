
package app.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CreationUserRequest {
    private String name;
    private String document;
    private String cellPhone;
    private String userName;
    private String password;
    private String amount;
    private long userSesion;
}
