package org.digitalpio.domain.crud;

import java.util.UUID;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import org.digitalpio.commons.dao.LocatorDAOable;
import org.digitalpio.commons.crud.LocatorAbstractCacheSrvc;
import org.digitalpio.commons.logging.Log;
import org.digitalpio.core.orgs.Domainable;
import org.digitalpio.domain.dao.DomainDAOSrvc;
import org.digitalpio.domain.entity.DomainEntity;
import org.slf4j.Logger;


@Singleton
@javax.annotation.concurrent.ThreadSafe
@org.checkthread.annotations.ThreadSafe
class DomainCacheImpl
        extends LocatorAbstractCacheSrvc<Domainable, UUID, DomainEntity>
        implements DomainCacheSrvc
{
    @Log 
    private static org.slf4j.Logger log;
    
    private static final int EXPIRY = 60 * 60 * 24 * 3; // 3 days
        
    private final Provider<DomainDAOSrvc> daoPrvdr;
    
    @Inject
    DomainCacheImpl(final Provider<DomainDAOSrvc> dp) {
        this.daoPrvdr = dp;
    }

    @Override
    protected LocatorDAOable<DomainEntity, Domainable, UUID> dao()
    {
        return daoPrvdr.get();
    }

    @Override
    protected int expirySecs()
    {
        return EXPIRY;
    }

    @Override
    protected Logger log()
    {
        return log;
    }
}