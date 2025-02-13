package com.sohamh.urlshortener.Repository;

import com.sohamh.urlshortener.Model.Url;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<Url, String> {
}