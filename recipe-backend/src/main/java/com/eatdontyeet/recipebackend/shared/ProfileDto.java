package com.eatdontyeet.recipebackend.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProfileDto {

    private String userId;
    private String userName;

}
