package com.university.library.model.assets.digital;

import java.util.Date;

public class DigitalBook extends DigitalAsset {

    private int bookSize;
    private String isbn;
    private String publisher;
    private Date published;
    private String author;
    private String subject;
    private String description;

    public DigitalBook(String accessLink, int bookSize, String isbn, String publisher, Date published, String author, String subject, String description) {
        super(accessLink);
        this.bookSize = bookSize;
        this.isbn = isbn;
        this.publisher = publisher;
        this.published = published;
        this.author = author;
        this.subject = subject;
        this.description = description;
    }

    public int getBookSize() {
        return bookSize;
    }

    public void setBookSize(int bookSize) {
        this.bookSize = bookSize;
    }


    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getPublished() {
        return published;
    }

    public void setPublished(Date published) {
        this.published = published;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
