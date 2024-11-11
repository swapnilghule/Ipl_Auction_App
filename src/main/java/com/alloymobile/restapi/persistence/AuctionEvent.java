package com.alloymobile.restapi.persistence;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class AuctionEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auctionId;
    private String eventName;
    private int teamLimit;
    private double prizePool;
    private LocalDateTime startTime;


    @ManyToOne
    @JoinColumn(name= "user_id", nullable = false)
    private Users userId;
    public AuctionEvent(){}

    public AuctionEvent(Long auctionId, String eventName, int teamLimit, double prizePool, LocalDateTime startTime, Users userId) {
        this.auctionId = auctionId;
        this.eventName = eventName;
        this.teamLimit = teamLimit;
        this.prizePool = prizePool;
        this.startTime = startTime;
        this.userId = userId;
    }

}
