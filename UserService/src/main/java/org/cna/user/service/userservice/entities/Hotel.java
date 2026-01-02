package org.cna.user.service.userservice.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Hotel {
    private String id;
    private String name;
    private String about;
    private String location;
}
