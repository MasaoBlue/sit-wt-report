import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class MainTestTest {
	@Test
	public void test() throws Exception {
		MainTest.main(new String[0]);
		List<String> actual = Files.lines(Paths.get("output.html"),
				StandardCharsets.UTF_8).collect(Collectors.toList());
		List<String> expected = Files.lines(Paths.get("expected.html"),
				StandardCharsets.UTF_8).collect(Collectors.toList());
		
		for(int i = 0; i < expected.size(); i++) {
			assertThat("Line:" + i, actual.get(i), is(expected.get(i)));
		}
	}
}