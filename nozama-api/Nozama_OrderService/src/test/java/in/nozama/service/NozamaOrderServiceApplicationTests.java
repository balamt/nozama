package in.nozama.service;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Because of Kafka server is not available will cause timeout and fails the test.
 */
@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest
public class NozamaOrderServiceApplicationTests {

	@Test
	public void contextLoads() {
		/**
		 * Not required
		 */
	}

}
