package com.baomidou.springmvc.model.system;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 活动信息配置表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductConfig {

    private Long id;

    private Integer channel;

    private String prodNo;

    private String prdName;

    private String stTime;

    private String edTime;

    private String creater;

    private String inTime;

    private String updTime;
}
