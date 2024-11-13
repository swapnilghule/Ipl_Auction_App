package com.alloymobile.restapi.resource;

import com.alloymobile.restapi.DTO.AuctionEventDTO;
import com.alloymobile.restapi.persistence.AuctionEvent;
import com.alloymobile.restapi.persistence.AuctionEventRepository;
import com.alloymobile.restapi.persistence.Users;
import com.alloymobile.restapi.persistence.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auction-events")
@CrossOrigin(origins= "http://localhost:4200")
public class AuctionEventController {

    @Autowired
    private AuctionEventRepository auctionEventRepository;

    @Autowired
    private UsersRepository usersRepository;

    @PostMapping("/create")
    public ResponseEntity<String> createAuctionEvent(@RequestBody AuctionEventDTO auctionEventDTO){
        Optional<Users> userOpt= usersRepository.findById(auctionEventDTO.getUserId());
        if(userOpt.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        Users user = userOpt.get();
        AuctionEvent auctionEvent = new AuctionEvent();
        auctionEvent.setEventName(auctionEventDTO.getEventName());
        auctionEvent.setTeamLimit(auctionEventDTO.getTeamLimit());
        auctionEvent.setPrizePool(auctionEventDTO.getPrizePool());
        auctionEvent.setStartTime(auctionEventDTO.getStartTime());
        auctionEvent.setUserId(user);

        auctionEventRepository.save(auctionEvent);
        return  ResponseEntity.status(HttpStatus.CREATED).body("Event Has been created");

    }


    @GetMapping("/{userId}")
    public ResponseEntity<List<AuctionEvent>> getAuctionEventbyId(@PathVariable Long userId)
    {
        Optional<Users> userOpt = usersRepository.findById(userId);

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        List<AuctionEvent> auctionEvents = auctionEventRepository.findByUserId(userOpt.get());
        return ResponseEntity.ok(auctionEvents);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AuctionEvent>> getAllauctionDetails() {
        List<AuctionEvent> auctionEvents= auctionEventRepository.findAll();
        return ResponseEntity.ok(auctionEvents);
    }





}
