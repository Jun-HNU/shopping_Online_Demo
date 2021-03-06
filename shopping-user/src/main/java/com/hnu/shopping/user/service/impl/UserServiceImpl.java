package com.hnu.shopping.user.service.impl;


import com.hnu.shopping.bean.UmsMember;
import com.hnu.shopping.bean.UmsMemberReceiveAddress;
import com.hnu.shopping.user.service.UserService;
import com.hnu.shopping.user.mapper.UmsMemberReceiveAddressMapper;
import com.hnu.shopping.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//import com.hnu.shopping.user.com.hnu.shopping.user.service.UserService0;
//import com.hnu.shopping.util.RedisUtils;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UmsMemberReceiveAddressMapper umsMemberReceiveAddressMapper;

  /*  @Autowired
    RedisUtils RedisUtils;*/

    @Override
    public List<UmsMember> getAllUser() {

        List<UmsMember> umsMembers = userMapper.selectAll();//userMapper.selectAllUser();

        return umsMembers;
    }

    //@Override
    public List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(String memberId) {

        // 封装的参数对象
        UmsMemberReceiveAddress umsMemberReceiveAddress = new UmsMemberReceiveAddress();
        umsMemberReceiveAddress.setMemberId(memberId);
        List<UmsMemberReceiveAddress> umsMemberReceiveAddresses = umsMemberReceiveAddressMapper.select(umsMemberReceiveAddress);


//       Example example = new Example(UmsMemberReceiveAddress.class);
//       example.createCriteria().andEqualTo("memberId",memberId);
//       List<UmsMemberReceiveAddress> umsMemberReceiveAddresses = umsMemberReceiveAddressMapper.selectByExample(example);

        return umsMemberReceiveAddresses;
    }





    @Override
    public UmsMember login(UmsMember umsMember) {
        return null;
    }




  /*  //@Override
    public UmsMember login(UmsMember umsMember) {
        Jedis jedis = null;
        try {
            jedis = RedisUtils.getJedis();

            if(jedis!=null){
                String umsMemberStr = jedis.get("user:" + umsMember.getPassword() + ":info");

                if (StringUtils.isNotBlank(umsMemberStr)) {
                    // 密码正确
                    UmsMember umsMemberFromCache = JSON.parseObject(umsMemberStr, UmsMember.class);
                    return umsMemberFromCache;
                }
            }
            // 链接redis失败，开启数据库
            UmsMember umsMemberFromDb =loginFromDb(umsMember);
            if(umsMemberFromDb!=null){
                jedis.setex("user:" + umsMember.getPassword() + ":info",60*60*24,JSON.toJSONString(umsMemberFromDb));
            }
            return umsMemberFromDb;
        }finally {
            jedis.close();
        }
    }*/

    private UmsMember loginFromDb(UmsMember umsMember) {

        List<UmsMember> umsMembers = userMapper.select(umsMember);

        if(umsMembers!=null){
            return umsMembers.get(0);
        }

        return null;

    }
}
