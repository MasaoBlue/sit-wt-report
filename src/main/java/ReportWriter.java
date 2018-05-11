import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.List;

import j2html.tags.ContainerTag;

import static j2html.TagCreator.*;

public class ReportWriter {

  public void write(List<TestScript> testScripts, Path reportFile) {
    try {
      FileOutputStream outputStream = new FileOutputStream(reportFile.toString());
      
      OutputStreamWriter writer = new OutputStreamWriter(outputStream, "UTF-8");
      ContainerTag[] tbodyInner = testScripts.stream().map((t) ->  
        tr(
          th(t.formatScriptInfo()),
          td(t.formatStepCount())
        )
      ).toArray(ContainerTag[]::new);
      String str = html(
        head(
          title("Title"),
          link().withRel("stylesheet").withHref("https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"),
          meta().withCharset("UTF-8")
        ),
        body(
          table(
            tbody(tbodyInner)
          )
        )
      ).renderFormatted();
      
      writer.write(str);
      
      writer.close();
    } catch(java.io.IOException e){
      System.out.println("ReportWrite failed:" + e);
    } finally {
      
    }
  }
  
}
