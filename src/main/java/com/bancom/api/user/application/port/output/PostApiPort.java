
package com.bancom.api.user.application.port.output;

import com.bancom.api.user.application.domain.Post;
import com.bancom.api.user.application.domain.User;

import java.util.List;

public interface PostApiPort {

   List<Post> list();

   Post create(Post post);

   Post update (Long id, Long user, Post post);

   Post remove (Long id);

}
