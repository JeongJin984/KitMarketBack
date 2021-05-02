package com.siy.siyresource.repository.ApplicationRepositoy;


import com.siy.siyresource.domain.entity.Application;

public interface ApplicationRepositoryCustom {
    Application findByUserName(String username,Long postId);



}
