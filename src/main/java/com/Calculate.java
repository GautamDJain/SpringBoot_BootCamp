package com;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Calculate {

    
	@RequestMapping("/add")
	public int add() {
		int a=10, b=20;
		return a+b;
	}

	@RequestMapping("/sub")
	public int sub() {
		int a=10, b=20;
		return b-a;
	}
	@RequestMapping("/mul")
	public int mul() {
		int a=10, b=20;
		return a*b;
	}
	@RequestMapping("/div")
	public int div() {
		int a=10, b=20;
		return b/a;
	}


}