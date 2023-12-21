package com.example.replication.service.serviceimpl;

import com.example.replication.controller.dto.AllOrdersRequest;
import com.example.replication.entity.ShopeeOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final EntityManager entityManager;

    public PageImpl<ShopeeOrder> getAllShopeeOrders(AllOrdersRequest request) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ShopeeOrder> criteriaQuery = cb.createQuery(ShopeeOrder.class);
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);

        Root<ShopeeOrder> shopeeOrderRoot = criteriaQuery.from(ShopeeOrder.class);

        Root<ShopeeOrder> ordersRootCount = countQuery.from(ShopeeOrder.class);

        List<Predicate> predicates = new ArrayList<>();

        Predicate shopIdPredicate = cb.equal(shopeeOrderRoot.get("shopId"), request.getShopId());
        predicates.add(shopIdPredicate);

        String searchKeyRequest = request.getSearchKey();
        if (StringUtils.hasLength(searchKeyRequest)) {
            if (searchKeyRequest.startsWith("0")) searchKeyRequest = searchKeyRequest.replaceFirst("0", "");
            String searchKey = new StringBuilder().append("%").append(searchKeyRequest).append("%").toString().toLowerCase();
            Predicate buyerNamePredicate = cb.like(cb.lower(shopeeOrderRoot.get("buyerUsername")), searchKey);
            Predicate buyerPhonePredicate = cb.like(cb.lower(shopeeOrderRoot.get("buyerPhone")), searchKey);
            Predicate orderSnPredicate = cb.like(cb.lower(shopeeOrderRoot.get("orderSn")), searchKey);

            Predicate searchPredicate = cb.or(buyerNamePredicate, buyerPhonePredicate, orderSnPredicate);
            predicates.add(searchPredicate);
        }

        if (StringUtils.hasLength(request.getStatus())) {
            String status = request.getStatus();
            Predicate searchKeyPredicate = cb.like(shopeeOrderRoot.get("orderStatus"), status);
            predicates.add(searchKeyPredicate);
        }

        if (request.getFromDate() != null) {
            Predicate fromDatePredicate = cb.greaterThan(shopeeOrderRoot.get("createTime"), request.getFromDate());
            predicates.add(fromDatePredicate);
        }

        if (request.getToDate() != null) {
            Predicate toDatePredicate = cb.lessThan(shopeeOrderRoot.get("createTime"), request.getToDate());
            predicates.add(toDatePredicate);
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        criteriaQuery.orderBy(cb.desc(shopeeOrderRoot.get("createTime"))).distinct(true);
        TypedQuery<ShopeeOrder> query = entityManager.createQuery(criteriaQuery);
        query.setFirstResult((request.getPage() - 1) * request.getPageSize());
        query.setMaxResults(request.getPageSize());

        countQuery.select(cb.countDistinct(ordersRootCount)).where(predicates.toArray(new Predicate[0]));
        Long count = entityManager.createQuery(countQuery).getSingleResult();

        PageRequest pageable = PageRequest.of(request.getPage() - 1, request.getPageSize());
        PageImpl<ShopeeOrder> orderPage = new PageImpl<>(query.getResultList(), pageable, count);
        return orderPage;
    }
}
