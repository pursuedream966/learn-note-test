package cn.pursuedream.bootmybatis.persistence.mapper;

import cn.pursuedream.bootmybatis.persistence.entity.TUser;

public interface TUserMapper {

    public TUser getById(Long id);

    public Integer save(TUser user);

}
