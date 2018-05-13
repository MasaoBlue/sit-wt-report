import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext {
    @Bean
    public ReportGenerator reportGenerator() {
        ReportGenerator generator = new ReportGenerator();
        generator.setReader(testScriptReader());
        generator.setWriter(reportWriter());
        return generator;
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