/*package com.example.tamyrv1.service;

import com.example.tamyrv1.dto.MiBandDataDTO;
import com.example.tamyrv1.model.MiBandData;
import com.example.tamyrv1.model.User;
import com.example.tamyrv1.repository.MiBandDataRepository;
import org.springframework.stereotype.Service;
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
        // Просто передаем объект `User`, без запросов в БД
        MiBandData data = new MiBandData(
                user,
                miBandDataDTO.getHeartRate(),
                miBandDataDTO.getSteps(),
                miBandDataDTO.getDistance(),
                miBandDataDTO.getCalories(),
                miBandDataDTO.getSleepQuality(),
                miBandDataDTO.getBatteryLevel(),
                miBandDataDTO.getTimestamp()
        );
        miBandDataRepository.save(data);
    }

    @Override
    public List<MiBandDataDTO> getAllMiBandDataByUser(Long userId) {
        return miBandDataRepository.findByUserId(userId).stream()
                .map(data -> new MiBandDataDTO(
                        data.getUser().getId(),
                        data.getHeartRate(),
                        data.getSteps(),
                        data.getDistance(),
                        data.getCalories(),
                        data.getSleepQuality(),
                        data.getBatteryLevel(),
                        data.getTimestamp()
                )).collect(Collectors.toList());
    }
}
*/

package com.example.tamyrv1.service;

import com.example.tamyrv1.dto.MiBandDataDTO;
import com.example.tamyrv1.model.MiBandData;
import com.example.tamyrv1.model.User;
import com.example.tamyrv1.repository.MiBandDataRepository;
import org.springframework.stereotype.Service;
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
        MiBandData data = new MiBandData(
                user,
                miBandDataDTO.getHeartRate(),
                miBandDataDTO.getSteps(),
                miBandDataDTO.getSleepHours(),
                miBandDataDTO.getCaloriesBurned(),
                miBandDataDTO.getDistance(),
                miBandDataDTO.getBatteryLevel(),
                miBandDataDTO.getTimestamp()
        );
        miBandDataRepository.save(data);
    }

    @Override
    public List<MiBandDataDTO> getAllMiBandDataByUser(Long userId) {
        return miBandDataRepository.findByUserId(userId).stream()
                .map(data -> new MiBandDataDTO(
                        data.getUser().getId(),
                        data.getHeartRate(),
                        data.getSteps(),
                        data.getSleepHours(),
                        data.getCaloriesBurned(),
                        data.getDistance(),
                        data.getBatteryLevel(),
                        data.getTimestamp()
                )).collect(Collectors.toList());
    }
}
