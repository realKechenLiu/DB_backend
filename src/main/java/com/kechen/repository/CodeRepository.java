package com.kechen.repository;

import com.kechen.domain.Code;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeRepository extends CrudRepository<Code,Integer>{

    public Code findByCodeId(int id);
}
