package org.learning.spring.bean.factory;

import org.learning.spring.ioc.overview.domain.User;

/**
 * {@link User} 工厂类
 */
public interface UserFactory {

    default User createUser() {
        return User.createUser();
    }

    void initUserFactory();

    void doDestroy() throws Exception;
}
