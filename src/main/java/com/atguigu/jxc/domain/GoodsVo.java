package com.atguigu.jxc.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * title：
 * author:liuchun
 * date:2023/9/11
 * description: 后端返回的商品实体
 */
@NoArgsConstructor
@Data
public class GoodsVo {
    @JsonProperty("goodsId")
    private Integer goodsId;
    @JsonProperty("goodsCode")
    private String goodsCode;
    @JsonProperty("goodsName")
    private String goodsName;
    @JsonProperty("inventoryQuantity")
    private Integer inventoryQuantity;
    @JsonProperty("lastPurchasingPrice")
    private Double lastPurchasingPrice;
    @JsonProperty("minNum")
    private Integer minNum;
    @JsonProperty("goodsModel")
    private String goodsModel;
    @JsonProperty("goodsProducer")
    private String goodsProducer;
    @JsonProperty("purchasingPrice")
    private Double purchasingPrice;
    @JsonProperty("remarks")
    private String remarks;
    @JsonProperty("sellingPrice")
    private Double sellingPrice;
    @JsonProperty("state")
    private Integer state;
    @JsonProperty("goodsUnit")
    private String goodsUnit;
    @JsonProperty("goodsTypeId")
    private Integer goodsTypeId;
    @JsonProperty("goodsTypeName")
    private String goodsTypeName;
    @JsonProperty("saleTotal")
    private Integer saleTotal;
}
