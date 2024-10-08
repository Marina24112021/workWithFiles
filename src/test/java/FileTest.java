import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileTest {

    private final ClassLoader cl = FileTest.class.getClassLoader();
    PDF pdfFile = null;
    XLS xlsFile = null;
    CSVReader csvFile = null;
    String nameOfArchive = "archive.zip";
    InputStream is = cl.getResourceAsStream(nameOfArchive);

    @Test
    void checkPdfFileTest() throws Exception {
        try (ZipInputStream zip = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zip.getNextEntry()) != null) {
                if (entry.getName().equals("file_pdf.pdf")) {
                    pdfFile = new PDF(zip);
                    break;
                }
            }
            Assertions.assertTrue(pdfFile.text.contains("Тестовый PDF-документ"));
        }
    }

    @Test
    void checkCSVFileTest() throws Exception {
        try (ZipInputStream zip = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zip.getNextEntry()) != null) {
                if (entry.getName().equals("file_csv.csv")) {
                    csvFile = new CSVReader(new InputStreamReader(zip));
                    break;
                }
            }
            List<String[]> data = csvFile.readAll();
            Assertions.assertEquals(3, data.size());
            Assertions.assertArrayEquals(
                    new String[]{"User1", "Job1"},
                    data.get(0)
            );
            Assertions.assertArrayEquals(
                    new String[]{"User2", "Jot2"},
                    data.get(1)
            );
        }
    }

    @Test
    void checkXLSXFileTest() throws Exception {
        try (ZipInputStream zip = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zip.getNextEntry()) != null) {
                if (entry.getName().equals("file_xlsx.xlsx")) {
                    xlsFile = new XLS(zip);
                    break;
                }
            }
            String actualValue = xlsFile.excel.getSheetAt(0).getRow(4).getCell(6).getStringCellValue();
            Assertions.assertTrue(actualValue.contains("agulina@company.ru"));
        }
    }
}
