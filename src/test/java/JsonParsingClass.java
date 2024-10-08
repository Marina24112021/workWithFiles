import com.fasterxml.jackson.databind.ObjectMapper;
import model.BookData;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class JsonParsingClass {
    private static final ObjectMapper om = new ObjectMapper();
    private final ClassLoader cl = JsonParsingClass.class.getClassLoader();
    String nameOfJsonFile = "book.json";
    InputStream is = cl.getResourceAsStream(nameOfJsonFile);

    @Test
    void jsonParsingTest() throws Exception {
        try (Reader reader = new InputStreamReader(is)) {
            BookData bookData = om.readValue(reader, BookData.class);
            assertThat(bookData.getTitle().contains("The Art of Software Development"));
            assertThat(bookData.getPublishedYear().equals(2024));
            assertThat(bookData.getAuthors().get(0).getName()).isEqualTo("John Doe");
            assertThat(bookData.getAuthors().get(0).getNationality()).isEqualTo("British");
            assertThat(bookData.getAuthors().get(1).getName()).isEqualTo("Jane Smith");
            assertThat(bookData.getAuthors().get(1).getNationality()).isEqualTo("Spain");
        }
    }
}
