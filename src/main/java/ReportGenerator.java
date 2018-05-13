import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/** 
 * �e�X�g�X�N���v�g�̃��|�[�g�𐶐�����
 * 
 * @author masao
 */
public class ReportGenerator {
  TestScriptReader reader;
  ReportWriter writer;

  /**
   * ���C������
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
   * �e�X�g�X�N���v�g��ǂݍ���Bean��ݒ�
   */
  public void setReader(TestScriptReader reader) {
    this.reader = reader;
  }

  /**
   * ���ʂ�html���o�͂���Bean��ݒ�
   */
  public void setWriter(ReportWriter writer) {
    this.writer = writer;
  }

  /**
   * �e�X�g�X�N���v�g��ǂݍ���Ō��ʂ��o�͂���
   */
  public void generate(Path targetDir, Path reportFile) {
    List<TestScript> testScripts = reader.readRecursively(targetDir, reportFile);
    
    writer.write(testScripts, reportFile);
  }
  
}
