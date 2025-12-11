package com.cristianmartinez.api.backendventa.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cristianmartinez.api.backendventa.Entity.Views;


@Repository
public interface ViewsRepository extends JpaRepository<Views, Long> {

    @Query("""
    SELECT v FROM Views v
    WHERE (:name IS NULL OR :name = '' OR LOWER(v.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND (:father IS NULL OR v.father.id = :father)
""")
Page<Views> search(@Param("name") String name,@Param("father") Long father, Pageable pageable);

}
