package com.university.library.action;

import com.university.library.model.assets.Asset;
import com.university.library.model.assets.digital.NewsLetter;
import com.university.library.repository.AssetRepository;
import com.university.library.action.ViewNews;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mockStatic;

public class ViewNewsTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private AssetRepository mockRepository;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MMM yyyy");

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        mockRepository = mock(AssetRepository.class);
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testViewNewsletters() throws ParseException {
        List<Asset> mockAssets = new ArrayList<>();
        NewsLetter newsletter1 = new NewsLetter("https://www.concordia.ca/cunews/artsci/migs/newsletters/2024/January24.html", dateFormat.parse("Jan 2024"), "Concordia University");
        NewsLetter newsletter2 = new NewsLetter("https://www.concordia.ca/cunews/artsci/migs/newsletters/2023/Dec23.html", dateFormat.parse("Dec 2023"), "Concordia University");
        mockAssets.add(newsletter1);
        mockAssets.add(newsletter2);

        when(mockRepository.getAllAssets()).thenReturn(mockAssets);

        try (MockedStatic<AssetRepository> mockedStatic = mockStatic(AssetRepository.class)) {
            mockedStatic.when(AssetRepository::getInstance).thenReturn(mockRepository);
            ViewNews.viewNewsletters();

            String actualOutput = outContent.toString().trim();
            String expectedOutput = ("Available Newsletters:\n" +
                    "Date: Jan 2024, Title: Newsletter, Publication: Concordia University, Access Link: https://www.concordia.ca/cunews/artsci/migs/newsletters/2024/January24.html\n" +
                    "Date: Dec 2023, Title: Newsletter, Publication: Concordia University, Access Link: https://www.concordia.ca/cunews/artsci/migs/newsletters/2023/Dec23.html").trim();

            assertThat(actualOutput).isEqualToIgnoringWhitespace(expectedOutput);
        }
    }
}
