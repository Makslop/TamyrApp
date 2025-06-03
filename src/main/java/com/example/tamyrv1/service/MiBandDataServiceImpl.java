package com.example.tamyrv1.service;

import com.example.tamyrv1.dto.MiBandDataDTO;
import com.example.tamyrv1.model.MiBandData;
import com.example.tamyrv1.model.User;
import com.example.tamyrv1.repository.MiBandDataRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MiBandDataServiceImpl implements MiBandDataService {

    private final MiBandDataRepository miBandDataRepository;

    public MiBandDataServiceImpl(MiBandDataRepository miBandDataRepository) {
        this.miBandDataRepository = miBandDataRepository;
    }

    @Override
    public void saveMiBandData(User user, MiBandDataDTO miBandDataDTO) {
        // Парсим timestamp из строки в LocalDateTime
        LocalDateTime parsedTimestamp = LocalDateTime.parse(miBandDataDTO.getTimestamp(), DateTimeFormatter.ISO_DATE_TIME);

        MiBandData data = new MiBandData(
                user,
                miBandDataDTO.getHeartRateListJson(),
                miBandDataDTO.getSteps(),
                miBandDataDTO.getCaloriesBurned(),
                miBandDataDTO.getDistance(),
                miBandDataDTO.getBatteryLevel(),
                parsedTimestamp
        );
        miBandDataRepository.save(data);
    }

    @Override
    public List<MiBandDataDTO> getAllMiBandDataByUser(Long userId) {
        return miBandDataRepository.findByUserId(userId).stream()
                .map(data -> new MiBandDataDTO(
                        data.getUser().getId(),
                        data.getHeartRateListJson(),
                        data.getSteps(),
                        data.getCaloriesBurned(),
                        data.getDistance(),
                        data.getBatteryLevel(),
                        data.getTimestamp().toString()  // Возвращаем как строку ISO8601
                ))
                .collect(Collectors.toList());
    }
}
