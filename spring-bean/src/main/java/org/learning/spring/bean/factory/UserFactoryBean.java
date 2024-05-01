package org.learning.spring.bean.factory;

import org.learning.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * {@link org.learning.spring.ioc.overview.domain.User} Bean的 {@link FactoryBean}实现
 */
public class UserFactoryBean implements FactoryBean<User> {

    @Override
    public User getObject() throws Exception {
        return User.createUser();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
