package omechu.omechubackend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import omechu.omechubackend.entity.Post;
import omechu.omechubackend.entity.PostEditor;
import omechu.omechubackend.exception.PostNotFound;
import omechu.omechubackend.repository.PostRepository;
import omechu.omechubackend.request.PostCreate;
import omechu.omechubackend.request.PostEdit;
import omechu.omechubackend.request.PostSearch;
import omechu.omechubackend.response.PostResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    /**
     * 게시글 작성
     * @param postCreate
     */
    public void write(PostCreate postCreate) {
        Post post = Post.builder()
                .title(postCreate.getTitle())
                .content(postCreate.getContent())
                .build();

        postRepository.save(post);
    }

    /**
     * 게시글 1개 읽기
     * @param id
     * @return
     */
    public PostResponse get(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(PostNotFound::new);

        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();
    }

    /**
     * 게시글 여러 개 읽기 - 페이징 처리 포함
     * @param postSearch
     * @return
     */
    public List<PostResponse> getList(PostSearch postSearch) {

        //Pageable pageable = PageRequest.of(page, 5, Sort.Direction.DESC, ("id"));

        return postRepository.getList(postSearch).stream()
                .map(post -> new PostResponse(post))
                .collect(Collectors.toList());
    }

    /**
     * 게시글 수정
     * @param id
     * @param postEdit
     */
    @Transactional
    public void edit(Long id, PostEdit postEdit) {
        Post post = postRepository.findById(id)
                .orElseThrow(PostNotFound::new);

        PostEditor.PostEditorBuilder editorBuilder = post.toEditor();

        PostEditor postEditor = editorBuilder
                .title(postEdit.getTitle())
                .content(postEdit.getContent())
                .build();

        post.edit(postEditor);
    }

    /**
     * 게시글 삭제
     * @param id
     */
    public void delete(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(PostNotFound::new);

        postRepository.delete(post);
    }
}
