package com.ken.taropojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String restaurantName;
    private String rating; 
    private String url;
    private String address;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}