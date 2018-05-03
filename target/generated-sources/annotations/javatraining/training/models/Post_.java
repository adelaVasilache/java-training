package javatraining.training.models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Post.class)
public abstract class Post_ {

	public static volatile SetAttribute<Post, Image> images;
	public static volatile SetAttribute<Post, Comment> comments;
	public static volatile SingularAttribute<Post, Date> created;
	public static volatile SingularAttribute<Post, Double> grade;
	public static volatile SingularAttribute<Post, Long> id;
	public static volatile SingularAttribute<Post, String> title;
	public static volatile SingularAttribute<Post, User> user;
	public static volatile SingularAttribute<Post, String> content;
	public static volatile SetAttribute<Post, Tag> tags;

}

