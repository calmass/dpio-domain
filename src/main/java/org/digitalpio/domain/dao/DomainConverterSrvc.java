package org.digitalpio.domain.dao;

import com.google.inject.ImplementedBy;
import org.digitalpio.commons.dao.Convertable;
import org.digitalpio.core.orgs.Domainable;
import org.digitalpio.domain.entity.DomainEntity;


@ImplementedBy(DomainConverterImpl.class)
@javax.annotation.concurrent.NotThreadSafe
@org.checkthread.annotations.NotThreadSafe
interface DomainConverterSrvc
        extends Convertable<DomainEntity, Domainable>
{    

}
