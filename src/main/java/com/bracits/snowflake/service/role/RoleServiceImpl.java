package com.bracits.snowflake.service.role;

import com.bracits.snowflake.entity.Role;
import com.bracits.snowflake.exception.AlreadyExistException;
import com.bracits.snowflake.exception.NotFoundException;
import com.bracits.snowflake.exception.ServiceException;
import com.bracits.snowflake.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Author Shaiful Islam Palash | kuvic16@gmail.com
 * @CreatedAt: 9/20/2020
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role create(Role role) throws ServiceException {
        Role _ex = this.findByName(role.getName());
        if(_ex != null) throw new AlreadyExistException("exception.already_exist");

        return roleRepository.save(new Role(role.getName(), role.getDescription(), role.getRights()));
    }

    @Override
    public Role update(long id, Role role) throws ServiceException {
        Role _role = this.findByName(role.getName());
        if(_role != null) throw new NotFoundException("exception.not_found");

        _role.setName(role.getName());
        _role.setDescription(role.getDescription());
        _role.setRights(role.getRights());
        return roleRepository.save(_role);
    }

    @Override
    public boolean delete(long id) throws ServiceException{
        Role _role = this.findById(id);
        if(_role != null) throw new NotFoundException("exception.not_found");

        roleRepository.deleteById(id);
        return true;
    }

    @Override
    public Role findById(long id) {
        Optional<Role> roleData = roleRepository.findById(id);
        return roleData.isPresent() ? roleData.get() : null;
    }

    @Override
    public Role findByName(String name) {
        Optional<Role> roleData = roleRepository.findByName(name);
        return roleData.isPresent() ? roleData.get() : null;
    }

    @Override
    public Page<Role> findAll(int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        return roleRepository.findAll(paging);
    }
}
