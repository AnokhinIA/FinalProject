package FinalProject.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

@Getter
@Setter
@NoArgsConstructor
public class FillInsurerDb {
    private ArrayList<String> result = new ArrayList<>();

    public ArrayList<String> parse() {
        File fileFinded = new File("resources/list_ssd.xlsx");
        try {
            FileInputStream file = new FileInputStream(fileFinded);
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowInterator = sheet.iterator();
            while (rowInterator.hasNext()) {
                Row row = rowInterator.next();
                Iterator<Cell> cells = row.iterator();
                while (cells.hasNext()) {
                    Cell cell = cells.next();
                    String value = cell.getStringCellValue();
                    result.add(value);
                }
            }
            return result;
        } catch (Exception e) {
            result.add(String.valueOf(e));
            result.add("Файл в каталоге" + fileFinded.getAbsolutePath()+ "   "+ fileFinded.getName());
            return result;
        }

    }
}
