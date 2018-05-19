import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/** 
 * テストスクリプトのレポートを生成する
 * 
 * @author masao
 */
public class ReportGenerator {
  TestScriptReader reader;
  ReportWriter writer;

  /**
   * メイン処理
   */
  public static void main(String[] args) {
    try(AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class)){
      ReportGenerator generator = ctx.getBean(ReportGenerator.class);
      generator.setReader(new TestScriptReader());
      generator.setWriter(new ReportWriter());
      generator.generate(Paths.get("testscript"), Paths.get("report.html"));      
    }
  }
  
  /**
   * テストスクリプトを読み込むBeanを設定
   */
  public void setReader(TestScriptReader reader) {
    this.reader = reader;
  }

  /**
   * 結果のhtmlを出力するBeanを設定
   */
  public void setWriter(ReportWriter writer) {
    this.writer = writer;
  }

  /**
   * テストスクリプトを読み込んで結果を出力する
   */
  public void generate(Path targetDir, Path reportFile) {
    List<TestScript> testScripts = reader.readRecursively(targetDir, reportFile);
    
    writer.write(testScripts, reportFile);
  }
  
}
