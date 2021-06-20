package net.jsrois.libritos.models;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void returnsTheImagePathIfTheBookHasAnImage() {
        Book book = new Book();
        book.setId(14L);
        book.setTitle("The Lord of the rings");
        book.setAuthor("J.R.R. Tolkien");
        book.setCategory("Fantasy");
        book.setImageName("lotr.jpg");

        assertThat(book.getImagePath(), equalTo("/data/14/lotr.jpg"));
    }

    @Test
    void returnsTheDefaultImageIfTheBookDoesNotHaveAnImage() {
        Book book = new Book();
        book.setId(14L);
        book.setTitle("The Lord of the rings");
        book.setAuthor("J.R.R. Tolkien");
        book.setCategory("Fantasy");

        assertThat(book.getImagePath(), equalTo("/images/default-book.png"));
    }
    @Test
    void returnsTheDefaultImageIfTheBookDoesNotHaveAnId() {
        Book book = new Book();
        book.setTitle("The Lord of the rings");
        book.setAuthor("J.R.R. Tolkien");
        book.setCategory("Fantasy");
        book.setImageName("lotr.jpg");

        assertThat(book.getImagePath(), equalTo("/images/default-book.png"));
    }
}