package javatraining.training.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Image.class)
public abstract class Image_ {

	public static volatile SingularAttribute<Image, String> fileName;
	public static volatile SingularAttribute<Image, String> filePath;
	public static volatile SingularAttribute<Image, Long> id;
	public static volatile SetAttribute<Image, Post> posts;

}

