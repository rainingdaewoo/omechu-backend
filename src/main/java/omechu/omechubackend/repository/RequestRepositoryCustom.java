package omechu.omechubackend.repository;

import omechu.omechubackend.entity.Request;
import omechu.omechubackend.request.PostSearch;
import omechu.omechubackend.response.RequestResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RequestRepositoryCustom {

    List<Request> getList(PostSearch postSearch);

    Page<RequestResponseDto> getList(PostSearch postSearch, Pageable pageable);
}
