package com.bracits.snowflake.service.user;

import com.bracits.snowflake.entity.Profile;
import com.bracits.snowflake.entity.Role;
import com.bracits.snowflake.entity.User;
import com.bracits.snowflake.exception.AlreadyExistException;
import com.bracits.snowflake.exception.ServiceException;
import com.bracits.snowflake.repository.RoleRepository;
import com.bracits.snowflake.repository.UserRepository;
import com.bracits.snowflake.security.auth.external.SSOUser;
import com.bracits.snowflake.service.role.RoleService;
import com.bracits.snowflake.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Author Shaiful Islam Palash | kuvic16@gmail.com
 * @CreatedAt: 9/20/2020
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleService roleService;

    @Autowired
    private Environment env;

    @Override
    public User findByPin(String pin) {
        Optional<User> userData = userRepository.findByPin(pin);
        return userData.isPresent() ? userData.get() : null;
    }

    @Override
    public User findByEmail(String email) {
        Optional<User> userData = userRepository.findByEmail(email);
        return userData.isPresent() ? userData.get() : null;
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public User create(User user) throws ServiceException {
        User _ex = this.findByPin(user.getPin());
        if(_ex != null) throw new AlreadyExistException("exception.already_exist");

        return userRepository.save(user);
    }

    @Override
    public User update(long id, User user) throws ServiceException {
        User _ex = this.findById(id);
        if(_ex == null) throw new AlreadyExistException("exception.not_found");
        return userRepository.save(user);
    }

    @Override
    public boolean delete(long id) throws ServiceException {
        User _ex = this.findById(id);
        if(_ex == null) throw new AlreadyExistException("exception.not_found");

        userRepository.deleteById(id);
        return true;
    }

    @Override
    public User findById(long id) {
        Optional<User> userData = userRepository.findById(id);
        return userData.isPresent() ? userData.get() : null;
    }

    @Override
    public Page<User> findAll(int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        return userRepository.findAll(paging);
    }

    @Override
    public User createSSOUser(SSOUser ssoUser) {
        try {
            // prepare profile
            Profile profile = new Profile();
            profile.setPin(ssoUser.getPin());
            profile.setName(ssoUser.getFullname());
            profile.setDesignation(ssoUser.getDesignation());
            profile.setMobile(ssoUser.getMobile());
            //profile.setGrade(Integer.parseInt(ssoUser.getJoblevel()));

            // set role
            Role role = roleService.findByName(env.getProperty("role.guest"));

            User user = new User();
            user.setPin(ssoUser.getPin());
            user.setName(ssoUser.getFullname());
            user.setActive(true);
            user.setProfile(profile);
            user.setRole(role);
            return userRepository.save(user);
        }catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
        return null;
    }
}
