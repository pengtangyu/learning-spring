package org.learning.spring.ioc.overview.domain;


import org.learning.spring.ioc.overview.annotation.Super;

/**
 * 超级用户
 */
@Super
public class SuperUser extends User {
    private String address;

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "SuperUser{" +
                "address='" + address + '\'' +
                "} " + super.toString();
    }
}
