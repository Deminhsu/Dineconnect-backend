package com.ken.taropojo.vo;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "餐廳搜尋返回資料")
public class RestaurantSearchVO implements Serializable{
    @ApiModelProperty("主键值")
    private Long id;

    @ApiModelProperty("餐廳名稱")
    private String name;

    @ApiModelProperty("餐廳地址")
    private String address;

    @ApiModelProperty("餐廳評分")
    private String rating;

    @ApiModelProperty("餐廳圖片") // I need to store picture in url
    private String url;
}