package app;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import app.controller.LoginController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@Getter
@Setter
@NoArgsConstructor
@SpringBootApplication

public class app implements CommandLineRunner{
    @Autowired
    LoginController controller;
    
    
    public static void main(String[] args) {
	SpringApplication.run(app.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            controller.session();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
