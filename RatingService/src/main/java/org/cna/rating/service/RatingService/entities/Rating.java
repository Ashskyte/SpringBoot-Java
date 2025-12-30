package org.cna.rating.service.RatingService.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document("rating_services")
public class Rating {
    @Id
    private String ratingId;
    private String feedback;
    private String userId;
    private String hotelId;
    private  int rating;
}
