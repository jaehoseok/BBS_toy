package com.example.BBS.repository;

import com.example.BBS.model.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Optional<Board> findByContents(String contents);

    Optional<Board> findByContentsAndTitle(String contents,String title);

    @Query("select b From Board b join fetch b.user u where u.name = :name")
    List<Board> findWithUserByEmail(@Param("name") String name);

}
