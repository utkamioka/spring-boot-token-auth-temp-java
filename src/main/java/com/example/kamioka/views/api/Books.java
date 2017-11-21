package com.example.kamioka.views.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/api/books")
public class Books {
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Book {
        @NonNull
        private String id;
        @NonNull
        private String title;
    }

    private static final List<Book> BOOKS = Arrays.asList(
            new Book("dd5eb6e3-f524-4354-af09-62d64198cbe2", "alpha"),
            new Book("9e1891c0-5b9d-4055-8e06-347ecf6eeba8", "bravo"),
            new Book("969f7376-c419-4cfc-a5db-1063dc05c0a1", "charlie")
    );

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Book> get(@AuthenticationPrincipal UserDetails userDetails) {
        System.out.println("Books.get()");
        System.out.println("userDetails.getUsername() = " + userDetails.getUsername());
        System.out.println("userDetails.getPassword() = " + userDetails.getPassword());
        System.out.println("userDetails.getAuthorities() = " + userDetails.getAuthorities());
        return BOOKS;
    }

    @Secured({"ROLE_admin", "ROLE_user"})
    @RequestMapping(method = RequestMethod.GET, path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Book get(@PathVariable(name = "id") int id, @AuthenticationPrincipal UserDetails userDetails) {
        System.out.println("Books.get(id)");
        System.out.println("userDetails.getUsername() = " + userDetails.getUsername());
        System.out.println("userDetails.getPassword() = " + userDetails.getPassword());
        System.out.println("userDetails.getAuthorities() = " + userDetails.getAuthorities());
        return BOOKS.get(id);
    }
}
