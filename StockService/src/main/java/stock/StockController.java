package stock;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("stock")
public class StockController {

	@GetMapping("/{productId}")
	public int getName(@PathVariable("productId") String productId) throws InterruptedException {
		Thread.sleep(500);
		switch(productId){
			case "1":
				return 5;
			case "2":
				return 10;
			case "3":
				return 1;
			default:
				return 0;
		}

	}
}
