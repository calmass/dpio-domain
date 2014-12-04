package org.digitalpio.domain.crud;

import org.digitalpio.core.orgs.Domainable;
import com.google.inject.ImplementedBy;
import java.util.UUID;
import org.digitalpio.commons.crud.LocatorCacheSrvc;
import org.digitalpio.domain.entity.DomainEntity;


@ImplementedBy(DomainCacheImpl.class)
@javax.annotation.concurrent.ThreadSafe
@org.checkthread.annotations.ThreadSafe
interface DomainCacheSrvc
        extends LocatorCacheSrvc<Domainable, UUID, DomainEntity>
{
    
}
