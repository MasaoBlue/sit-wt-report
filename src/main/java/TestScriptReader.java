import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestScriptReader {
  
  // �f�B���N�g�����̃e�X�g�X�N���v�g�t�@�C�������ׂēǂݍ���
  public List<TestScript> readRecursively(Path targetDir, Path reportFile) {
    List<TestScript> testScripts = new ArrayList<TestScript>();

    File folder = new File(targetDir.toString());
    for(File file : folder.listFiles()) {
      parseScript(file.getPath(), testScripts);
    }
    
    return testScripts;
  }
  
  // excel�ɋL�ڂ���Ă���X�N���v�g���p�[�X����
  private void parseScript(String path, List<TestScript> testScripts) {
    try {
      FileInputStream fileIn = new FileInputStream(path);
      try(Workbook wb = new XSSFWorkbook(fileIn)) { 
        Sheet sheet = wb.getSheetAt(0);
        Row headerRow = sheet.getRow(0);
        
        Iterator<Cell> cellIterator = headerRow.cellIterator();
        
        String pattern = "^�P�[�X_\\d+$";
        Pattern p = Pattern.compile(pattern);
        while (cellIterator.hasNext()) {
          Cell headerCell = cellIterator.next();
          // �P�[�X���L�q����񂪌��������牺�����ɃZ����H��
          String caseName = headerCell.getStringCellValue();
          if(p.matcher(caseName).find()){
            int cellIndex = headerCell.getColumnIndex();
            System.out.println(path + ": " + cellIndex);
            
            Iterator<Row> rowIterator = sheet.rowIterator();
            int count = 0;
            while(rowIterator.hasNext()) {
              Row row = rowIterator.next();
              Cell cell = row.getCell(cellIndex);
              if(cell == null) continue;
              String val = cell.getStringCellValue();
              if(val != null && val.length() > 0) {
                count++;
              };
            }
            
            TestScript script = new TestScript(caseName, count, path);
            testScripts.add(script);
          }
        }
        
        fileIn.close();
      }
    }catch (Exception e) {
      e.printStackTrace();
    }
  }
  
}
