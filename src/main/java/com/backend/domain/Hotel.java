package com.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "hotels")
public class Hotel {

    @Id
    @Column(name = "hotelId")
    public String hotelId;

    @Column(name = "name")
    public String name;

    @Column(name = "price")
    public String price;

    @Column(name = "created")
    public LocalDateTime created = LocalDateTime.now();
}
