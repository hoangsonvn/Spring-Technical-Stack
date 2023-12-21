package com.example.replication.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class AllOrdersRequest {
    private long shopId;

    private int page;

    private int pageSize;

    private String searchKey;

    private String status;

    private List<Long> productIdList;

    private Long fromDate;

    private Long toDate;
}
