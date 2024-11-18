package com.alloymobile.restapi.persistence;

import com.alloymobile.restapi.enums.AuctionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuctionEventRepository extends JpaRepository<AuctionEvent, Long> {


    Optional<AuctionEvent> findByAuctionId(Long auctionId);

    List<AuctionEvent> findByStatus(AuctionStatus status);
    List<AuctionEvent>  findByUserId(Users user);

}
