package omechu.omechubackend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import omechu.omechubackend.repository.StoreRepository;
import omechu.omechubackend.response.StoreResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    /**
     * 가게 정보 전체 불러오기
     * @return
     */
    public List<StoreResponseDto> getAllYoutubeContent() {
        System.out.println("getAllYoutubeContent" + storeRepository.findAll());

        return storeRepository.findAll().stream().map(store -> new StoreResponseDto(store)).collect(Collectors.toList());
    }
}
