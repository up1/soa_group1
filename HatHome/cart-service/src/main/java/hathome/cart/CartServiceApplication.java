package hathome.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CartServiceApplication {

	private CartServiceApplication(){
		throw new IllegalAccessError("CartServiceApplication class");
	}

	public static void main(String[] args) {
		SpringApplication.run(CartServiceApplication.class, args);
	}
}
