import java.io.FileInputStream;
 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
 
public class MainTest {
     
    //�K���ȃf�B���N�g���ɏ��������Ă�������
    static final String INPUT_DIR = "SampleTestScript.xlsx";
 
    public static void main(String[] args) {
 
        try {
            //xlsx�̏ꍇ�͂������L����
            FileInputStream fileIn = new FileInputStream(INPUT_DIR);
            Workbook wb = new XSSFWorkbook(fileIn);
            
            Sheet sheet = wb.getSheetAt(0);
            Row row = sheet.getRow(2);
            Cell cell = row.getCell(3);
            
            fileIn.close();
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
             
        }
 
 
    }
 
}