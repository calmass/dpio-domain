package org.digitalpio.domain.crud;

import org.digitalpio.commons.logging.Log;
import javax.inject.Inject;
import javax.inject.Provider;
import org.digitalpio.commons.dao.LocatorDAOable;
import org.digitalpio.commons.crud.LocatorAbstractCrudSrvc;
import org.digitalpio.commons.crud.LocatorCacheSrvc;
import org.digitalpio.core.orgs.Domainable;
import org.digitalpio.domain.dao.DomainDAOSrvc;
import org.digitalpio.domain.entity.DomainEntity;
import java.util.UUID;


@javax.annotation.concurrent.NotThreadSafe
@org.checkthread.annotations.NotThreadSafe
public class DomainCrudImpl 
        extends LocatorAbstractCrudSrvc<Domainable, UUID, DomainEntity>
        implements DomainCrudSrvc
{    
    @Log
    private static org.slf4j.Logger log;
        
    private final Provider<DomainCacheSrvc> cachePrvdr;
    private final Provider<DomainDAOSrvc> daoPrvdr;
    
    @Inject
    DomainCrudImpl(final Provider<DomainCacheSrvc> cp,
                   final Provider<DomainDAOSrvc> dp) {
        this.cachePrvdr = cp;
        this.daoPrvdr = dp;
    }

    @Override
    protected LocatorDAOable<DomainEntity, Domainable, UUID> dao()
    {
        return daoPrvdr.get();
    }

    @Override
    protected LocatorCacheSrvc<Domainable, UUID, DomainEntity> cache()
    {
        return cachePrvdr.get();
    }
}