package com.example.demo;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ValidationErrorRepository extends ElasticsearchRepository<ValidationError, String> {
}
