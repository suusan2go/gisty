package gyst.infractructures.socialprofile

import gyst.domains.DateTime
import gyst.domains.socialprofile.*
import gyst.domains.user.UserId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class MyBatisSocialProfileRepository(
        @Autowired val socialProfileMapper: SocialProfileMapper
): SocialProfileRepository{
    override fun createSocialProfile(socialProfile: SocialProfile): SocialProfile {
        val record = SocialProfileRecord(
                socialProfile.id.value,
                socialProfile.userId.value,
                socialProfile.uid.value,
                socialProfile.provider.typeName(),
                socialProfile.auth.value,
                socialProfile.updatedDatetime.value,
                socialProfile.createdDateTime.value
        )
        socialProfileMapper.createSocialProfile(record)
        return recordToSocialProfile(record)
    }

    override fun findByUidAndProvider(uid: SocialProfileUid, provider: SocialProfileProvider): SocialProfile? {
        val record = socialProfileMapper.findByUidAndProvider(uid = uid.value, provider = provider.typeName())
        return record?.let{
            recordToSocialProfile(it)
        }
    }

    private fun recordToSocialProfile(record: SocialProfileRecord): SocialProfile {
        return SocialProfile(
                DefinedSocialProfileId(record.id!!),
                UserId(record.userId),
                SocialProfileUid(record.uid),
                SocialProfileProvider(record.provider),
                SocialProfileAuth(record.auth),
                DateTime(record.updatedDatetime),
                DateTime(record.createdDatetime)
        )
    }
}
