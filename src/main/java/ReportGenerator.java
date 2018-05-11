import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReportGenerator {

  TestScriptReader reader = new TestScriptReader();
  
  ReportWriter writer = new ReportWriter();
  
  public static void main(String[] args) {
    new ReportGenerator().generate(Paths.get("testscript"), Paths.get("report.html"));
  }
  
  public void generate(Path targetDir, Path reportFile) {
    List<TestScript> testScripts = reader.readRecursively(targetDir, reportFile);
    
    writer.write(testScripts, reportFile);
  }
  
}
