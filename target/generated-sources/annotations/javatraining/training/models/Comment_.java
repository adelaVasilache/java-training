package javatraining.training.models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Comment.class)
public abstract class Comment_ {

	public static volatile SingularAttribute<Comment, Post> post;
	public static volatile SingularAttribute<Comment, Date> created;
	public static volatile SingularAttribute<Comment, Long> id;
	public static volatile SingularAttribute<Comment, User> user;
	public static volatile SingularAttribute<Comment, String> content;

}

