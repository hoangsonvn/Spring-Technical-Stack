package com.example.replication.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "shopee_order")
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShopeeOrder {

    @Id
    private String orderSn;

    @Column(columnDefinition = "bigint default 0")
    private long shopId;

    private boolean cod;

    private float totalAmount;

    private String orderStatus;

    private String shippingCarrier;

    private String packageNumber;

    private String paymentMethod;

    private float estimatedShippingFee;

    private String messageToSeller;

    private long createTime;

    private long updateTime;

    private long buyerUserId;

    private String buyerUsername;

    private String recipientName;

    @Column(columnDefinition = "text")
    private String recipientAddress;

    private String buyerPhone;

    private float actualShippingFee;

    private String note;

    private long noteUpdateTime;

    private String buyerCancelReason;

    private long cancelBy;

    private String cancelReason;

    private boolean actualShippingFeeConfirmed;


    public String getRecipientName() {
        if (StringUtils.hasLength(recipientName)) {
            return recipientName;
        }
        return buyerUsername;
    }
}
