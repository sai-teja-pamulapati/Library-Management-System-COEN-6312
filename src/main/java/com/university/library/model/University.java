package com.university.library.model;

import java.util.List;

public class University {
    private String universityName;
    private String universityAddress;

    public University(String universityName , String universityAddress , List<Library> libraries) {
        this.universityName = universityName;
        this.universityAddress = universityAddress;
        this.libraries = libraries;
    }

    public University() {
    }

    public List<Library> getLibraries() {
        return libraries;
    }

    public void setLibraries(List<Library> libraries) {
        this.libraries = libraries;
    }

    private List<Library> libraries;

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getUniversityAddress() {
        return universityAddress;
    }

    public void setUniversityAddress(String universityAddress) {
        this.universityAddress = universityAddress;
    }
}
