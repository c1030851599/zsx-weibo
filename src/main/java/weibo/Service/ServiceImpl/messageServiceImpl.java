package weibo.Service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weibo.Service.messageService;
import weibo.mapper.plmessageMapper;
import weibo.mapper.zfmessageMapper;
import weibo.pojo.likemessage;
import weibo.pojo.plmessage;
import weibo.pojo.zfmessage;

import java.util.List;

@Service
public class messageServiceImpl implements messageService {
  @Autowired
  weibo.mapper.likemessageMapper likemessageMapper;

  @Autowired
  zfmessageMapper zfmessageMapper;

  @Autowired
  plmessageMapper plmessageMapper;

  @Override
  public void insetLikeMessage(likemessage likemessage) {
    likemessageMapper.insert(likemessage);
  }

  @Override
  public void insetplMessage(plmessage plmessage) {
    plmessageMapper.insert(plmessage);
  }
  @Override
  public void insetzfMessage(zfmessage zfmessage) {
    zfmessageMapper.insert(zfmessage);
  }

  @Override
  public List<likemessage> selectlikeMessage(String username) {
    return likemessageMapper.selectAll(username);
  }

  @Override
  public List<plmessage> selectplMessage(String username) {
    return plmessageMapper.selectAll(username);
  }

  @Override
  public List<zfmessage> selectzfMessage(String username) {
    return zfmessageMapper.selectAll(username);
  }


}
