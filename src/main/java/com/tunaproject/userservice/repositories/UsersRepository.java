package com.tunaproject.userservice.repositories;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.query.QueryResult;
import com.tunaproject.userservice.domain.User;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UsersRepository {
    private final Cluster couchbaseCluster;
    private  final Collection pitsCollection;

    public UsersRepository(Cluster couchbaseCluster, Collection pitsCollection) {
        this.couchbaseCluster = couchbaseCluster;
        this.pitsCollection = pitsCollection;
    }

    public void insert(User user){
        pitsCollection.insert(user.getId(), user);
    }

    public List<User> getUsersByName(String name){
        String statement = String.format("Select id,name,surname,email from users where name = '%s'", name);
        QueryResult query = couchbaseCluster.query(statement);
        return query.rowsAs(User.class);
    }

    public void deleteUserById(String id){
        String statement = String.format("Delete from users where users.id = \"%s\"",id);
        QueryResult query = couchbaseCluster.query(statement);
    }
}
