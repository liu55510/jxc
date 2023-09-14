package com.atguigu.jxc.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * title：
 * author:liuchun
 * date:2023/9/11
 * description:
 */

@NoArgsConstructor
@Data
public class DamageListVo {
    @JsonProperty("damageListId")
    private Integer damageListId;
    @JsonProperty("damageNumber")
    private String damageNumber;
    @JsonProperty("damageDate")
    private String damageDate;
    @JsonProperty("remarks")
    private String remarks;
    @JsonProperty("userId")
    private Integer userId;
    @JsonProperty("trueName")
    private String trueName;
}

