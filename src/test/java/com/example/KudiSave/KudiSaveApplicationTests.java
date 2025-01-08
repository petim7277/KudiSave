package com.example.KudiSave;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KudiSaveApplicationTests {

	@Test
	void contextLoads() {
		System.getenv().forEach((key, value ) -> System.out.println(key + ":" + value));
	}

}
