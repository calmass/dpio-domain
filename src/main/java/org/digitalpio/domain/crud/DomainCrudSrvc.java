package org.digitalpio.domain.crud;

import com.google.inject.ImplementedBy;
import java.util.UUID;
import org.digitalpio.commons.crud.LocatorCrudSrvc;
import org.digitalpio.core.orgs.Domainable;
import org.digitalpio.domain.entity.DomainEntity;


@ImplementedBy(DomainCrudImpl.class)
@javax.annotation.concurrent.NotThreadSafe
@org.checkthread.annotations.NotThreadSafe
public interface DomainCrudSrvc
        extends LocatorCrudSrvc<Domainable, UUID, DomainEntity>
{
    
}