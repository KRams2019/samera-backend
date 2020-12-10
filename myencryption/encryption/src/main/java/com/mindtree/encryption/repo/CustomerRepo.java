package com.mindtree.encryption.repo;




import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import com.mindtree.encryption.entity.Customer;


public interface CustomerRepo extends ElasticsearchRepository<Customer,String>
{
}
