package verby.batchserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EntityScan(basePackages = { "verby.core.*" })
@SpringBootApplication
public class BatchServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatchServerApplication.class, args);
	}

}
