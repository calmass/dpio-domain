package org.digitalpio.domain.dao;

import java.util.UUID;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.digitalpio.commons.dao.LocatorAbstractDAO;
import org.digitalpio.commons.dao.Convertable;
import org.digitalpio.core.orgs.Domainable;
import org.digitalpio.domain.entity.DomainEntity;


@javax.annotation.concurrent.NotThreadSafe
@org.checkthread.annotations.NotThreadSafe
public class DomainDAOImpl
        extends LocatorAbstractDAO<DomainEntity, Domainable, UUID>
        implements DomainDAOSrvc
{    
    @org.digitalpio.commons.logging.Log
    private static org.slf4j.Logger log;

    private final Provider<EntityManager> emPrvdr;
    private final DomainConverterSrvc converterSrvc;

    @Inject
    DomainDAOImpl(final Provider<EntityManager> emp,
            final DomainConverterSrvc ics)
    {
        this.emPrvdr = emp;
        this.converterSrvc = ics;
    }

    @Override
    public Convertable<DomainEntity, Domainable> getConverterSrvc()
    {
        return converterSrvc;
    }

    @Override
    public EntityManager getEntityManager()
    {
        return emPrvdr.get();
    }

    @Override
    public Class<DomainEntity> getEntityClass()
    {
        return DomainEntity.class;
    }
    
    @Override
    public TypedQuery<DomainEntity> buildCustomQuery(
            final Domainable domain, final EntityManager em)
    {
        return em.createNamedQuery("DomainEntity.sub", DomainEntity.class)
                .setParameter("subDomain", domain.getSub());
    }
    
    @Override
    public TypedQuery<DomainEntity> buildIdQuery(
            final UUID uuid, final EntityManager em)
    {
        return buildUuidQuery(uuid, em);
    }
      
//    @Override
//    @CheckValid
//    public Optional<Domainable> find(final UUID id)
//            throws DataAccessException
//    {
//        final Optional<DomainEntity> entity = findEntity(id, getEntityManager());
//        if (entity.isPresent()) {
//            return Optional.of(getConverterSrvc().from(entity.get()));
//        }
//        return Optional.absent();        
//    }        
}