package com.test.spring_data_neo4j.movie;

import com.test.spring_data_neo4j.person.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import java.util.List;

@RelationshipProperties
@NoArgsConstructor
@Getter
@Setter
public class Role {

    @RelationshipId
    @GeneratedValue
    private Long id;

    private List<String> roles;

    @TargetNode
    private Person person;

}