package org.cna.user.service.userservice.entities;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Rating {

    private String ratingId;
    private String feedback;
    private String userId;
    private String hotelId;

}
