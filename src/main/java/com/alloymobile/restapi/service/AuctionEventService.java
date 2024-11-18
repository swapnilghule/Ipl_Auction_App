package com.alloymobile.restapi.service;


import com.alloymobile.restapi.DTO.AuctionEventDTO;
import com.alloymobile.restapi.DTO.AuctionEventRequest;
import com.alloymobile.restapi.enums.AuctionStatus;
import com.alloymobile.restapi.persistence.AuctionEvent;
import com.alloymobile.restapi.persistence.AuctionEventRepository;
import com.alloymobile.restapi.persistence.Users;
import com.alloymobile.restapi.persistence.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuctionEventService {

    @Autowired
    public AuctionEventRepository auctionEventRepository;

    @Autowired
    public UsersRepository usersRepository;

    public AuctionEventService(AuctionEventRepository auctionEventRepository,UsersRepository usersRepository){
        this.auctionEventRepository = auctionEventRepository;
        this.usersRepository = usersRepository;
    }

    public AuctionEvent createAuctionEvent(AuctionEventRequest request, Users user){
        AuctionEvent auctionEvent= new AuctionEvent();
        auctionEvent.setAuctionId(request.getAuctionId());
        auctionEvent.setAuctionName(request.getAuctionName());
        auctionEvent.setPrizePool(request.getPrizePool());
        auctionEvent.setTeamLimit(request.getTeamLimit());
        auctionEvent.setStartDate(request.getStartDate());
        auctionEvent.setEndDate(request.getEndDate());
        auctionEvent.setStatus(AuctionStatus.PENDING);
        auctionEvent.setUserId(user);

        return auctionEventRepository.save(auctionEvent);

    }

    public AuctionEvent updateAuctionEvent(Long auctionId, AuctionEventRequest request, Users user){

        AuctionEvent auctionEvent= auctionEventRepository.findByAuctionId(auctionId)
                        .orElseThrow(() -> new RuntimeException("AuctionEvent not found"));

        if(!auctionEvent.getUserId().equals(user))
        {
            throw new RuntimeException("You are not authorised to Edit this auction");
        }

        auctionEvent.setAuctionId(request.getAuctionId());
        auctionEvent.setAuctionName(request.getAuctionName());
        auctionEvent.setPrizePool(request.getPrizePool());
        auctionEvent.setTeamLimit(request.getTeamLimit());
        auctionEvent.setStartDate(request.getStartDate());
        auctionEvent.setEndDate(request.getEndDate());

        auctionEvent.setStatus(request.getStatus());

        auctionEvent.setUserId(user);


        return auctionEventRepository.save(auctionEvent);

    }

    public List<AuctionEvent> getAllAuctionEvent(Users user){
        if(user.getCreatedBy() == Users.CreatedBy.ADMIN){
            return auctionEventRepository.findAll();
        }
        else{
            return auctionEventRepository.findByUserId(user);
        }
    }

    public List<AuctionEvent> getOpenAuction(){
        return auctionEventRepository.findByStatus(AuctionStatus.OPEN);
    }
}
