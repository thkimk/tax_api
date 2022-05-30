package com.example.apiserver.repository;

/*
import com.example.apiserver.dto.QUserDto;
import com.example.apiserver.dto.UserDto;
//import com.example.apiserver.entity.User;
import com.example.apiserver.vo.UserVo;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import java.util.List;
import static org.springframework.util.StringUtils.hasText;
*/

//import static com.example.apiserver.entity.QUser.user;

/*
public class UserJpaRepositoryImpl extends QuerydslRepositorySupport implements CustomUserJpaRepository {

    private final JPAQueryFactory queryFactory;

    public UserJpaRepositoryImpl(EntityManager em) {
        super(User.class);
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public Page<UserDto> search(UserVo condition, Pageable pageable) {

        List<UserDto> content = queryFactory
                .select(new QUserDto(
                        user.msrl,
                        user.uid,
                        user.name,
                        user.age))
                .from(user)
                .where(
                        usernameEq(condition.getName()),
                        uidEq(condition.getUid()),
                        ageLoe(condition.getAge())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // count query를 최적화 하고 싶다면 분리하라!
        JPAQuery<User> countQuery = queryFactory
                .select(user)
                .from(user)
                .where( usernameEq(condition.getName()),
                        uidEq(condition.getUid()),
                        ageLoe(condition.getAge())
                );

        // content size가 전체보다 작을 경우 count 쿼리를 날릴 필요가 없음!!!
        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }

    private BooleanExpression usernameEq(String name) {
        return hasText(name) ? user.name.eq(name) : null;
    }

    private BooleanExpression uidEq(String uid) {
        return hasText(uid) ? user.uid.eq(uid) : null;
    }

    private BooleanExpression ageLoe(Integer ageLoe) {
        return ageLoe != null ? user.age.loe(ageLoe) : null;
    }
}
*/
