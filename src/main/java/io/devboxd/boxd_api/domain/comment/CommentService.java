package io.devboxd.boxd_api.domain.comment;

import io.devboxd.boxd_api.domain.profile.Profile;

public interface CommentService {

    Comment getByAuthor(Profile author);

    Comment getByAuthorId(Long id);

    Comment getByAuthorUsername(String username);

    Comment getByBody(String body);

}
