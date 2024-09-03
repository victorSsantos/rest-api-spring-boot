package br.com.java.rest.api.services.dto;

import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.time.LocalDateTime;

public class BookDTO extends RepresentationModel<BookDTO> implements Serializable  {

    private Long id;
    private String author;
    private LocalDateTime launch_date;
    private Double price;
    private String title;

    public BookDTO() {
    }

    public BookDTO(Long id, String author, LocalDateTime launch_date, Double price, String title) {
        this.id = id;
        this.author = author;
        this.launch_date = launch_date;
        this.price = price;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getLaunch_date() {
        return launch_date;
    }

    public void setLaunch_date(LocalDateTime launch_date) {
        this.launch_date = launch_date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
