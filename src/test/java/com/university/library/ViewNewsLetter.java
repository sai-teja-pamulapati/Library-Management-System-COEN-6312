package com.university.library;

import com.university.library.model.assets.digital.NewsLetter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ViewNewsLetter {

    public static void main(String[] args) throws ParseException {
        testViewNewsLetter();
    }

    public static void testViewNewsLetter() throws ParseException {
        // Create a Date object representing "Jan 2024"
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM yyyy");
        Date jan2024 = dateFormat.parse("Jan 2024");

        // Create a sample newsletter
        NewsLetter newsletter = new NewsLetter(
            "https://www.example.com/newsletter",
            jan2024
        );

        // Test the attributes of the newsletter
        System.out.println("Access Link: " + newsletter.getAccessLink());
        System.out.println("Date: " + dateFormat.format(newsletter.getDate()));
    }
}
