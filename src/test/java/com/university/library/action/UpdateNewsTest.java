package com.university.library.action;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.university.library.repository.AssetRepository;
import com.university.library.model.assets.digital.NewsLetter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class UpdateNewsTest {

    @Mock
    private AssetRepository mockRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        AssetRepository.setInstance(mockRepository);
    }


    @Test
    void testSuccessfulUpdate() {
        String input = "1\nFeb 2024\nTest Publication\nhttp://newlink.com";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        NewsLetter mockNewsletter = mock(NewsLetter.class);
        when(mockRepository.getNewsLetterByAssetId("1")).thenReturn(mockNewsletter);

        UpdateNews.updateNewsletterProcess();

        verify(mockNewsletter).setDate(any(Date.class));
        verify(mockNewsletter).setPublication("Test Publication");
        verify(mockNewsletter).setAccessLink("http://newlink.com");
        verify(mockRepository).update(mockNewsletter);
    }

    @Test
    void testNonExistentNewsletter() {

        String input = "2\nFeb 2024\nTest Publication\nhttp://newlink.com";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        when(mockRepository.getNewsLetterByAssetId("2")).thenReturn(null);
        UpdateNews.updateNewsletterProcess();
        verify(mockRepository, never()).update(any());
    }

    @Test
void testNewsletterUpdateWithSameValues() {
    String existingDate = "Mar 2023";
    String existingPublication = "Existing Publication";
    String existingAccessLink = "http://existinglink.com";
    String input = String.format("1\n%s\n%s\n%s", existingDate, existingPublication, existingAccessLink);
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    NewsLetter existingNewsletter = new NewsLetter();
    existingNewsletter.setAssetId("1");

    try {
        existingNewsletter.setDate(new SimpleDateFormat("MMM yyyy").parse(existingDate));
    } catch (ParseException e) {
        e.printStackTrace();
    }

    existingNewsletter.setPublication(existingPublication);
    existingNewsletter.setAccessLink(existingAccessLink);

    when(mockRepository.getNewsLetterByAssetId("1")).thenReturn(existingNewsletter);
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    try {
        assertThat(existingNewsletter.getDate()).isEqualTo(new SimpleDateFormat("MMM yyyy").parse(existingDate));
    } catch (ParseException e) {
        e.printStackTrace();
    }
}

    @Test
    void testInvalidDateFormat() {
        String input = "1\nFeb 2024\nTest Publication\nhttp://newlink.com";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        when(mockRepository.getNewsLetterByAssetId("1")).thenReturn(null);
        UpdateNews.updateNewsletterProcess();
        verify(mockRepository, never()).update(any());
    }

}