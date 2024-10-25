package com.br.dojo360.exam.studentexam;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class StudenExamCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<StudentExamEntity> findByExamId() {
        return null;
    }
}
