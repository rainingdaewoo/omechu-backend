package omechu.omechubackend.service;

import lombok.RequiredArgsConstructor;
import omechu.omechubackend.config.auth.PrincipalDetail;
import omechu.omechubackend.entity.Request;
import omechu.omechubackend.entity.User;
import omechu.omechubackend.exception.PostNotFound;
import omechu.omechubackend.repository.RequestRepository;
import omechu.omechubackend.request.PostSearch;
import omechu.omechubackend.request.RequestCreate;
import omechu.omechubackend.response.RequestResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RequestService {

    private final RequestRepository requestRepository;
    @Transactional
    public void postRequest(User user, RequestCreate requestCreate) {
        Request request = Request.builder()
                .title(requestCreate.getTitle())
                .category(requestCreate.getCategory())
                .content(requestCreate.getContent())
                .state("WAITING")
                .user(user)
                .build();
        requestRepository.save(request);
    }

    public  Page<RequestResponseDto> getRequestList(PostSearch postSearch, Pageable pageable) {

        PageRequest pageRequest = PageRequest.of(postSearch.getPage(), postSearch.getSize());
        Page<RequestResponseDto> requests = requestRepository.getList(postSearch, pageRequest);
        return requests;
    }

    public RequestResponseDto findById(Long requestId) {

        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new PostNotFound());
        RequestResponseDto requestResponse = new RequestResponseDto(request);

        return requestResponse;
    }

    @Transactional
    public void editRequest(Long requestId, PrincipalDetail user, RequestCreate request) {
        Request findRequest = requestRepository.findById(requestId)
                .orElseThrow(PostNotFound::new);

        Long findRequestUserId = findRequest.getUser().getId();

        if(findRequestUserId == user.getUser().getId()) {  // ????????? ID??? userId??? ????????? ????????? userId??? ????????? ?????? ??????
            findRequest.edit(request);
        } else {
            throw new RuntimeException("????????? ???????????? ???????????? ???????????? ????????????.");
        }
    }

    public void deleteRequest(Long requestId, PrincipalDetail user) {
        Request findRequest = requestRepository.findById(requestId)
                .orElseThrow(PostNotFound::new);

        Long findRequestUserId = findRequest.getUser().getId();

        if(Objects.equals(findRequestUserId, user.getUser().getId())) {  // ????????? ID??? userId??? ????????? ????????? userId??? ????????? ?????? ??????
            requestRepository.delete(findRequest);
        } else {
            throw new RuntimeException("????????? ???????????? ???????????? ???????????? ????????????.");
        }
    }
}
