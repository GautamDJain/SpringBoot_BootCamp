package com;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CalcTest {
 Calculate cal = new Calculate();

	@Test
	public void add() {
		Assertions.assertEquals(30, cal.add());
	}

	@Test
	public void sub() {
		Assertions.assertEquals(10, cal.sub());
	}

	@Test
	public void mul() {
		Assertions.assertEquals(200, cal.mul());
	}

	@Test
	public void div() {
		Assertions.assertEquals(2, cal.div());
	}

}
