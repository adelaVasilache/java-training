package javatraining.training.repositories.specs;

import javatraining.training.models.Post;
import javatraining.training.models.Post_;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

/**
 * Created by Adela Vasilache on 27.04.2018
 */
@NoArgsConstructor
public class PostSpecifications {

    public static Specification<Post> hasNameOrShortDescriptionLike(String like) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            String containsLikePattern = getContainsLikePattern(like);
            return criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.lower(root.get(Post_.title)), containsLikePattern)
            );
        };
    }

    public static Specification<Post> seachBySubmitDate(final Date startDate, final Date endDate) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.between(root.get(Post_.created), startDate, endDate);
    }

    public static Specification<Post> searchByExistingGrade() {
        return ((root, criteriaQuery, criteriaBuilder) ->
        criteriaBuilder.isTrue(root.get(Post_.grade).isNotNull()));
    }

    private static String getContainsLikePattern(String title) {
        if (title == null || title.isEmpty()) {
            return "%";
        }
        return "%" + title.toLowerCase() + "%";
    }

}
