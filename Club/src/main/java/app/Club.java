
package app;

import app.controller.ControllerInterface;
import app.controller.LoginController;

public class Club {

    public static void main(String[] args) {
        ControllerInterface controller = new LoginController();
		try {
			controller.session();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }
}
