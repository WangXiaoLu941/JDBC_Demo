package wxl.service.user;



import wxl.bean.Users;
import wxl.dao.user.UserDao;
import wxl.dao.user.UserDaoImpl;
import wxl.util.PageUtil;

import java.io.Serializable;
import java.util.List;


public class UserServiceImpl implements UserService {

    private UserDao userDao=new UserDaoImpl();

    @Override
    public int add(Users users) {
        return userDao.add(users);
    }

    @Override
    public int deleteByCondition(Serializable id) {
        return 0;
    }

    @Override
    public int update(Users users) {
        return 0;
    }

    @Override
    public Users findByCondition(Serializable id) {
        return null;
    }

    @Override
    public List<Users> findAll() {
        return null;
    }

    @Override
    public int findRownum() {
        return 0;
    }

    @Override
    public List<Users> findAllByPage(PageUtil util, Object... params) {
        return null;
    }
}
