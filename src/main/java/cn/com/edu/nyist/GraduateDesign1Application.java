package cn.com.edu.nyist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class GraduateDesign1Application {

	public static void main(String[] args) {
		SpringApplication.run(GraduateDesign1Application.class, args);
	}
}
