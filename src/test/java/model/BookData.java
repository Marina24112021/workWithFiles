package model;

import java.util.List;

public class BookData {
    private String title;
    private Integer publishedYear;
    private List<Authors> authors;

    public List<Authors> getAuthors() {
        return authors;
    }

    public String getTitle() {
        return title;
    }

    public Integer getPublishedYear() {
        return publishedYear;
    }

    public static class Authors {
        private String name;
        private String nationality;

        public String getName() {
            return name;
        }

        public String getNationality() {
            return nationality;
        }
    }
}
