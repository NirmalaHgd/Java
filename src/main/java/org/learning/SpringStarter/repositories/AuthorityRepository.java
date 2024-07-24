package org.learning.SpringStarter.repositories;

import org.learning.SpringStarter.models.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository  extends JpaRepository<Authority,Long>{
    
}
