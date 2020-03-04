package weibo.Service;

import weibo.pojo.likemessage;
import weibo.pojo.plmessage;
import weibo.pojo.zfmessage;

import java.util.List;

public interface messageService {
    void insetLikeMessage(likemessage likemessage);

    void insetplMessage(plmessage plmessage);

    void insetzfMessage(zfmessage zfmessage);

    List<likemessage> selectlikeMessage(String username);

    List<plmessage> selectplMessage(String username);

    List<zfmessage> selectzfMessage(String username);

}
