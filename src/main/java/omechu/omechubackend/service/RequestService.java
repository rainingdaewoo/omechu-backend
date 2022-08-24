package omechu.omechubackend.service;

import lombok.RequiredArgsConstructor;
import omechu.omechubackend.entity.Request;
import omechu.omechubackend.entity.User;
import omechu.omechubackend.repository.RequestRepository;
import omechu.omechubackend.request.PostSearch;
import omechu.omechubackend.request.RequestCreate;
import omechu.omechubackend.response.PostResponse;
import omechu.omechubackend.response.RequestResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class RequestService {

    private final RequestRepository requestRepository;
    public Request postRequest(User user, RequestCreate requestCreate) {
        Request request = Request.builder()
                .title(requestCreate.getTitle())
                .category(requestCreate.getCategory())
                .content(requestCreate.getContent())
                .state("WAITING")
                .user(user)
                .build();

        return requestRepository.save(request);
    }

    public  Page<Request> getRequestList(PostSearch postSearch, Pageable pageable) {

        PageRequest pageRequest = PageRequest.of(postSearch.getPage(), postSearch.getSize());
        Page<Request> requests = requestRepository.getList(postSearch, pageRequest);

        return requests;

//        return requestRepository.getList(postSearch, pageable).stream()
//                .map(request -> new RequestResponse(request, finalIsLast))
//                .collect(Collectors.toList());
    }

    public RequestResponse findById(Long requestId) {

        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("id를 확인해주세요"));
        RequestResponse requestResponse = new RequestResponse(request);

        return requestResponse;
    }
}
