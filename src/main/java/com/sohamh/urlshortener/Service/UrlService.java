package com.sohamh.urlshortener.Service;
import com.sohamh.urlshortener.Repository.UrlRepository;
import com.sohamh.urlshortener.Model.Url;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Base64;

@Service
public class UrlService {
    
    @Autowired
    private UrlRepository urlRepository;
    
    public String shortenUrl(String originalUrl) {
        try {
            
            //Using First 8 digits of MD5 hash (Considered SHA-256)
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(originalUrl.getBytes());
            String base64 = Base64.getUrlEncoder().encodeToString(digest);
            String shortUrl = base64.substring(0, 8);
            
            Url url = new Url();
            url.setOriginalUrl(originalUrl);
            url.setShortUrl(shortUrl);
            url.setCreatedAt(LocalDateTime.now());;
            
            urlRepository.save(url);
            return shortUrl;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating short URL", e);
        }
    }
    
    public String getOriginalUrl(String shortUrl) {
        return urlRepository.findById(shortUrl)
                .map(Url::getOriginalUrl)
                .orElseThrow(() -> new RuntimeException("URL not found"));
    }
}