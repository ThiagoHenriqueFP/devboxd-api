package io.devboxd.boxd_api.domain.like;

import jakarta.persistence.EntityNotFoundException;

public class LikeServiceImpl implements LikeService{

    private final LikeRepository likeRepository;

    public LikeServiceImpl(LikeRepository likeRepository){
        this.likeRepository = likeRepository;
    }

    @Override
    public Like findByProfileId(Long id) {
        return likeRepository.findByProfileId(id).orElseThrow( () -> new EntityNotFoundException("Like não encontrado."));
    }

    @Override
    public Like findByContentId(Long id) {
        return likeRepository.findByContentId(id).orElseThrow( () -> new EntityNotFoundException("Like não encontrado."));
    }
}
