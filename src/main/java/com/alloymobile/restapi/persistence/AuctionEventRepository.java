package com.alloymobile.restapi.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuctionEventRepository extends JpaRepository<AuctionEvent, Long> {
    List<AuctionEvent> findByUserId(Users user);
}
