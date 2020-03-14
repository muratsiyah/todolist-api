package com.murat.siyah.todolistapi.infrastructure.config;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.query.N1qlQuery;
import com.murat.siyah.todolistapi.infrastructure.repository.TodoRepository;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CouchbaseConfig {

    private Bucket bucket;

    public CouchbaseConfig(TodoRepository todoRepository) {
        bucket = todoRepository.getCouchbaseOperations().getCouchbaseBucket();
        createIndices();
    }

    private void createIndices() {
        bucket.bucketManager().createN1qlPrimaryIndex(true, false);
        bucket.query(N1qlQuery.simple(String.format("CREATE PRIMARY INDEX ON `%s` USING GSI", bucket.name())));
    }

}