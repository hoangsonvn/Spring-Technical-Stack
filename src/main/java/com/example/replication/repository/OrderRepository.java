package com.example.replication.repository;

import com.example.replication.entity.ShopeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface OrderRepository extends JpaRepository<ShopeeOrder, String> {
    List<ShopeeOrder> findAllByBuyerUserIdAndShopId(long buyerId, long shopId);

    ShopeeOrder findByOrderSn(String orderSn);

    @Transactional
    @Modifying
    @Query("update ShopeeOrder o set o.estimatedShippingFee = ?1, o.actualShippingFee = ?2, o.updateTime = ?3," +
            "o.buyerCancelReason = ?4, o.cancelBy = ?5, o.cancelReason = ?6, " +
            "o.actualShippingFeeConfirmed = ?7, o.orderStatus = ?8, o.messageToSeller = ?9," +
            "o.shippingCarrier = ?10, o.note = ?11, o.noteUpdateTime = ?12 " +
            "where o.orderSn = ?13")
    void updateOrder(float estimatedShippingFee, float actualShippingFee, long updateTime,
                     String buyerCancelReason, long cancelBy, String cancelReason,
                     boolean actualShippingFeeConfirmed, String orderStatus, String messageToSeller,
                     String shippingCarrier, String note, long noteUpdateTime, String orderSn);


    List<ShopeeOrder> findAllByShopIdAndOrderStatus(Long shopId, String orderStatus);
}
