package gisty.domain.repository.socialprofile

import gisty.domain.model.SocialProfile
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

@Mapper
interface SocialProfileRepository {
    @Select("""
        SELECT id, user_id, uid, provider, auth::text, updated_datetime, created_datetime FROM social_profiles
        WHERE provider = #{provider} AND uid = #{uid}
        """
    )
    fun findByUidAndProvider(uid: String, provider: String): SocialProfile?

    @Insert("""
        INSERT INTO social_profiles (user_id, uid, provider, auth, updated_datetime, created_datetime)
        VALUES(#{userId}, #{uid}, #{provider}, to_json(#{auth}::json), #{updatedDatetime}, #{createdDatetime})
        """
    )
    fun createSocialProfile(socialProfile: SocialProfile)
}