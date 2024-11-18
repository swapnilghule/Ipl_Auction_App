package com.alloymobile.restapi.DTO;

import com.alloymobile.restapi.enums.AuctionStatus;
import com.alloymobile.restapi.persistence.AuctionEvent;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AuctionEventRequest {

    private Long auctionId;
    private String auctionName;
    private int teamLimit;
    private double prizePool;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private AuctionStatus status = AuctionStatus.PENDING;
    private Long userId;


}
