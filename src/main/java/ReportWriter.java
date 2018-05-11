import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class ReportWriter {

  public void write(List<TestScript> testScripts, Path reportFile) {
    OutputStreamWriter writer;
    FileOutputStream outputStream;
    try {
      outputStream = new FileOutputStream(reportFile.toString());
      writer = new OutputStreamWriter(outputStream, "UTF-8");
    
      try {
        Configuration cfg = new Configuration();
        
        Map<String, Object> input = new HashMap<String, Object>();

        input.put("title", "Hello, world!");
        input.put("scripts", testScripts);
        
        Template template = cfg.getTemplate("template.ftl");
        
        template.process(input, writer);
      } catch(TemplateException e){
        e.printStackTrace();
      } finally {
        writer.close();      
      }
    } catch (java.io.IOException e) {
      e.printStackTrace();
    }
  }
  
}
