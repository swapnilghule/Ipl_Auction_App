package com.alloymobile.restapi.persistence;


import com.alloymobile.restapi.enums.AuctionStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class AuctionEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auctionId;
    private String auctionName;
    private int teamLimit;
    private double prizePool;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    @Enumerated(EnumType.STRING)
    private AuctionStatus status = AuctionStatus.PENDING;



    @ManyToOne
    @JoinColumn(name= "user_id", nullable = false)
    private Users userId;
    public AuctionEvent(){}



}
