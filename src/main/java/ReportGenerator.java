import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/** 
 * テストスクリプトのレポートを生成する
 * 
 * @author masao
 */
@Component
public class ReportGenerator {
  @Autowired
  TestScriptReader reader;

  @Autowired
  ReportWriter writer;

  /**
   * メイン処理
   */
  public static void main(String[] args) {
    try(AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class)){
      ReportGenerator generator = ctx.getBean(ReportGenerator.class);
      generator.generate(Paths.get("testscript"), Paths.get("report.html"));      
    }
  }

  /**
   * テストスクリプトを読み込んで結果を出力する
   */
  public void generate(Path targetDir, Path reportFile) {
    List<TestScript> testScripts = reader.readRecursively(targetDir, reportFile);
    
    writer.write(testScripts, reportFile);
  }
  
}
