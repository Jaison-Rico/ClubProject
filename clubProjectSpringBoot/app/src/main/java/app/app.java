package app;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@Getter
@Setter
@NoArgsConstructor
@SpringBootApplication

public class app{

    
    public static void main(String[] args) {
	SpringApplication.run(app.class, args);
    }


}
