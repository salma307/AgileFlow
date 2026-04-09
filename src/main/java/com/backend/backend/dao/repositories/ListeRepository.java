package com.backend.backend.dao.repositories;

import com.backend.backend.dao.entities.Folder;
import com.backend.backend.dao.entities.Liste;
import com.backend.backend.dao.entities.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListeRepository extends JpaRepository<Liste,String> {
    List<Liste>findByFolder(Folder folder);
    List<Liste>findBySprint(Sprint sprint);
}
