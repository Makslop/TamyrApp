package com.example.tamyrv1.controller;

import com.example.tamyrv1.dto.MiBandDataDTO;
import com.example.tamyrv1.model.User;
import com.example.tamyrv1.service.MiBandDataService;
import com.example.tamyrv1.service.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/miband")
public class MiBandDataController {

    private final MiBandDataService miBandDataService;
    private final UserServiceImpl userServiceImpl;

    public MiBandDataController(MiBandDataService miBandDataService, UserServiceImpl userServiceImpl) {
        this.miBandDataService = miBandDataService;
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping("/data")
    public ResponseEntity<String> receiveMiBandData(@RequestBody MiBandDataDTO miBandDataDTO) {
        try {
            User user = userServiceImpl.getUserById(miBandDataDTO.getUserId());
            miBandDataService.saveMiBandData(user, miBandDataDTO);
            return ResponseEntity.ok("Mi Band data received successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/data/{userId}")
    public ResponseEntity<List<MiBandDataDTO>> getUserMiBandData(@PathVariable Long userId) {
        try {
            userServiceImpl.getUserById(userId);
            return ResponseEntity.ok(miBandDataService.getAllMiBandDataByUser(userId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
