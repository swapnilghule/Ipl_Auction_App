package com.alloymobile.restapi.resource;

import com.alloymobile.restapi.DTO.AuctionEventDTO;
import com.alloymobile.restapi.DTO.AuctionEventRequest;
import com.alloymobile.restapi.persistence.AuctionEvent;
import com.alloymobile.restapi.persistence.AuctionEventRepository;
import com.alloymobile.restapi.persistence.Users;
import com.alloymobile.restapi.persistence.UsersRepository;
import com.alloymobile.restapi.service.AuctionEventService;
import com.sun.jdi.connect.LaunchingConnector;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private AuctionEventService eventService;

    @Autowired
    private UsersRepository usersRepository;


//    public JwtUtil jwtUtil;
////    @PostMapping("/create")
////    public String createAuction(@RequestBody AuctionEvent auctionEvent, @RequestHeader("Authorization") String token){
////        String username = jwtUtil.extarctUsername(token.substring(7));
////        Users users= usersRepository.findByUsername(username)
////                .orElseThrow(() -> new RuntimeException("User Not Found"));
////
////
////        if(!users.getCreatedBy().equals("ADMIN")){
////            throw new RuntimeException("Unauthorised user");
////        }
////
////        auctionEventRepository.save(auctionEvent);
////        return "Auction created successfully";
////
////    }
//
    @GetMapping("/all")
    public String getAllAuction(){
        return "SUCCESS";
    }

//    public List<AuctionEvent> getUserAuction( @RequestHeader("Authorization") String token){
//         String username= jwtUtil.extarctUsername(token.substring(7));
//         return auctionEventRepository.findBy(username);
//    }
//


}
