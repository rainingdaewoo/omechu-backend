package omechu.omechubackend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import omechu.omechubackend.request.PostCreate;
import omechu.omechubackend.request.PostEdit;
import omechu.omechubackend.request.PostSearch;
import omechu.omechubackend.response.PostResponse;
import omechu.omechubackend.service.PostService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    /**
     * 게시판 작성
     * @param request
     */
    @PostMapping("/posts")
    public void post(@RequestBody @Valid PostCreate request) {
        request.validate();

        postService.write(request);
    }

    /**
     * 게시글 단건 조회
     * @param postId
     * @return
     */
    @GetMapping("/posts/{postId}")
    public PostResponse get(@PathVariable Long postId) {
        return postService.get(postId);
    }

    /**
     * 게시글 리스트 조회
     * @param postSearch
     * @return
     */
    @GetMapping("/posts")
    public List<PostResponse> getList(@ModelAttribute PostSearch postSearch) {
        return postService.getList(postSearch);
    }

    /**
     * 게시글 수정
     * @param postId
     * @param request
     */
    @PatchMapping("/posts/{postId}")
    public void edit(@PathVariable Long postId, @RequestBody @Valid PostEdit request) {
        postService.edit(postId, request);
        // 업데이트 후 데이터를 프론트에 넘겨줘야 하는지
    }

    /**
     * 게시글 삭제
     * @param postId
     */
    @DeleteMapping("/posts/{postId}")
    public void delete(@PathVariable Long postId) {
        postService.delete(postId);
    }
}
