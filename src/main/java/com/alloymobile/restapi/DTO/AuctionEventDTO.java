package com.alloymobile.restapi.DTO;

import com.alloymobile.restapi.persistence.Users;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AuctionEventDTO {
    private Long auctionId;
    private String eventName;
    private int teamLimit;
    private double prizePool;
    private LocalDateTime startTime;
    private Long userId;

    public AuctionEventDTO(Long auctionId, String eventName, int teamLimit, double prizePool, LocalDateTime startTime, Long userId) {
        this.auctionId = auctionId;
        this.eventName = eventName;
        this.teamLimit = teamLimit;
        this.prizePool = prizePool;
        this.startTime = startTime;
        this.userId = userId;
    }

}
