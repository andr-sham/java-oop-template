package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private SchoolBook[] schoolBooks = new SchoolBook[0];
    @Override
    public boolean save(SchoolBook book) {
        schoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length + 1);
        schoolBooks [schoolBooks.length - 1] = book;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        SchoolBook[] foundSchoolBooks = new SchoolBook[0];
        for (SchoolBook schoolBook: schoolBooks) {
            if (name.equals(schoolBook.getName())) {
                foundSchoolBooks = Arrays.copyOf(foundSchoolBooks, foundSchoolBooks.length + 1);
                foundSchoolBooks[foundSchoolBooks.length - 1] = schoolBook;
            }
        }
        return foundSchoolBooks;
    }

    @Override
    public boolean removeByName(String name) {
        SchoolBook[] schoolBooksResult = new SchoolBook[0];
        for (int i = 0; i < schoolBooks.length; i++){
            if (schoolBooks[i].getName().equals(name)){

                System.arraycopy(schoolBooks, i + 1, schoolBooks, i, schoolBooks.length - i - 1);
                schoolBooksResult = Arrays.copyOf(schoolBooks, schoolBooks.length - 1);
                schoolBooks = schoolBooksResult;
                i--;
            }
        }
        return schoolBooksResult.length > 0;

    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
