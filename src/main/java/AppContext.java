import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext {
    @Bean
    public ReportGenerator reportGenerator() {
        return new ReportGenerator();
    }
 
    @Bean
    public ReportWriter reportWriter() {
        return new ReportWriter();
    }
 
    @Bean
    public TestScriptReader testScriptReader() {
        return new TestScriptReader();
    }
}