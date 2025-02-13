package com.sohamh.urlshortener.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Url {
    @Id
    private String shortUrl;
    private String originalUrl;
    private long createdAt;
}

