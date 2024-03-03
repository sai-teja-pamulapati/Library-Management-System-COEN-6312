package com.university.library.model.assets.physical;

import java.util.Date;

public class Book extends PhysicalAsset {
    private String isbn;
    private String publisher;
    private Date published;
    private String author;
    private String subject;
    private String description;

    public Book( String assetID, String title, String urlPreview, String urlLogo, Boolean availability, String floor, String row, String section, String shelf, String isbn, String publisher, Date published, String author, String subject, String description) {
        super(assetID, title, urlPreview, urlLogo, availability, floor, row, section, shelf);
        this.isbn = isbn;
        this.publisher = publisher;
        this.published = published;
        this.author = author;
        this.subject = subject;
        this.description = description;
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

    @Override
    public String toString() {
        return "Book Details: \n" +
                "assetId : " + getAssetId() + '\n' +
                "isbn : " + isbn + '\n' +
                "publisher : " + publisher + '\n' +
                "published : " + published +
                "author : " + author + '\n' +
                "subject : " + subject + '\n' +
                "description : " + description + '\n' +
                "floor : " + getFloor() + '\n' +
                "row : " + getRow() + '\n' +
                "section : " + getSection() + '\n' +
                "shelf : " + getShelf() + '\n' +
                "title : " + getTitle() + '\n' +
                "preview : " + getPreview() + '\n' +
                "logo : " + getLogo() + '\n' +
                "availability : " + isAvailable();
    }
}
