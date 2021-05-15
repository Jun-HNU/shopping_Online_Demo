package com.hnu.shopping.user.service;

import com.hnu.shopping.bean.UmsMember;
import com.hnu.shopping.bean.UmsMemberReceiveAddress;

import java.util.List;

public interface UserService {
    //List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(String memberId);

    List<UmsMember> getAllUser();
   // List<UmsMemberReceiveAddressmber> getAllUser();

    List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(String memberId);

    UmsMember login(UmsMember umsMember);
}
