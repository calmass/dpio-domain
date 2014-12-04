package org.digitalpio.domain.dao;

import com.google.inject.ImplementedBy;
import java.util.UUID;
import org.digitalpio.commons.dao.LocatorDAOable;
import org.digitalpio.core.orgs.Domainable;
import org.digitalpio.domain.entity.DomainEntity;


@ImplementedBy(DomainDAOImpl.class)
@javax.annotation.concurrent.NotThreadSafe
@org.checkthread.annotations.NotThreadSafe
public interface DomainDAOSrvc
        extends LocatorDAOable<DomainEntity, Domainable, UUID>
{    
    
}
