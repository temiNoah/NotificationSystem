package com.publisher.dto;

public class Topic {
    private String message;
    private String title;


    public Topic (String title)
    {
        this.title = title;

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object obj){
        if (!(obj instanceof Topic))
            return false;

        return this.getTitle().equalsIgnoreCase(  ((Topic) obj).getTitle()  );

    }
}
