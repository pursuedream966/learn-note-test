package cn.pursuedream.bootmybatis.service;

import cn.hutool.core.util.RandomUtil;
import cn.pursuedream.bootmybatis.persistence.entity.TUser;
import cn.pursuedream.bootmybatis.persistence.mapper.TUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TUserService {

    @Autowired
    private TUserMapper mapper;

    @Transactional
    public void save(boolean rollback){
        TUser user = new TUser();
        user.setUsername(RandomUtil.randomString(7));
        System.out.println(user);
        mapper.save(user);
        if(rollback){
            throw new RuntimeException("回滚");
        }
    }

    public TUser getById(Long id){
        TUser user = mapper.getById(id);
        mapper.getById(id);
        return user;
    }

}
