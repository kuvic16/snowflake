package com.bracits.snowflake.service.right;

import com.bracits.snowflake.entity.Right;
import com.bracits.snowflake.exception.AlreadyExistException;
import com.bracits.snowflake.exception.NotFoundException;
import com.bracits.snowflake.exception.ServiceException;
import com.bracits.snowflake.repository.RightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 * @Author Shaiful Islam Palash | kuvic16@gmail.com
 * @CreatedAt: 9/20/2020
 */
@Service
public class RightServiceImpl implements RightService {

    @Autowired
    RightRepository rightRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public Right create(Right right) throws ServiceException {
        Right _ex = this.findByName(right.getName());
        if(_ex != null) throw new AlreadyExistException("exception.already_exist");

        return rightRepository.save(new Right(right.getName(), right.getDescription()));
    }

    @Override
    public Right update(long id, Right right) throws ServiceException {
        Right _right = this.findById(id);
        if(_right == null) throw new NotFoundException("exception.not_found");

        _right.setName(right.getName());
        _right.setDescription(right.getDescription());
        return rightRepository.save(_right);
    }

    @Override
    public boolean delete(long id) throws ServiceException{
        Right _right = this.findById(id);
        if(_right == null) throw new NotFoundException("exception.not_found");

        rightRepository.deleteById(id);
        return true;
    }

    @Override
    public Right findById(long id) {
        Optional<Right> rightData = rightRepository.findById(id);
        return rightData.isPresent() ? rightData.get() : null;
    }

    @Override
    public Right findByName(String name) {
        Optional<Right> rightData = rightRepository.findByName(name);
        return rightData.isPresent() ? rightData.get() : null;
    }

    @Override
    public Page<Right> findAll(int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        return rightRepository.findAll(paging);
    }

    @Override
    public List<Right> findByJdbcTemplate(long id) {
        final String sql = "select `name`, `description` from rights where id = ?";
        return jdbcTemplate.query(sql, new Object[]{id} , (resultSet, i) -> {
            return new Right(resultSet.getString("name"), resultSet.getString("description"));
        });
    }

    @Override
    public List<Right> findByJPA(int page, int size, long id) {
        return entityManager.createQuery("select r  from Right r order by r.createdAt")
                .setFirstResult(page)
                .setMaxResults(size)
                .getResultList();
    }
}
