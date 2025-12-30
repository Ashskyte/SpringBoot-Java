package org.cna.hotel.service.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "hotels")
public class Hotel {
    @Id
    private String id;
    private String name;
    private String about;
    private String location;
}
