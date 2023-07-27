package com.sekawanmedia.soaltes.repository;

import com.sekawanmedia.soaltes.model.UserPlan;
import com.sekawanmedia.soaltes.model.UserPlanQuery;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserPlanRepo extends CrudRepository<UserPlan, Integer> {

    @Query("SELECT up.*, p.name, p.limit_per_hour FROM user_plans up "
            + "JOIN plans p ON p.id=up.plan_id "
            + "JOIN master_users mu ON mu.id=up.user_id "
            + "WHERE up.user_id=:userId AND up.is_active=true")
    Optional<UserPlanQuery> findUserPlanByUserId(@Param("userId") int userId);
}
