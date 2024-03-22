package com.ken.taropojo.dto;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "用戶搜尋餐廳傳遞的data model")
public class RestaurantSearchDTO {
  private String name;
  private String location;
  private Float rating;
}
