package org.digitalpio.domain.dao;

import java.util.UUID;
import javax.inject.Inject;
import org.digitalpio.commons.annotations.CheckValid;
import org.digitalpio.commons.dao.AbstractConverter;
import org.digitalpio.commons.error.DataAccessException;
import org.digitalpio.core.orgs.Domainable;
import org.digitalpio.commons.validation.SimpleValSrvc;
import org.digitalpio.commons.validation.Validation_Type;
import org.digitalpio.domain.Domain;
import org.digitalpio.domain.entity.DomainEntity;


@javax.annotation.concurrent.NotThreadSafe
@org.checkthread.annotations.NotThreadSafe
class DomainConverterImpl
        extends AbstractConverter<DomainEntity, Domainable>
        implements DomainConverterSrvc
{
    private final SimpleValSrvc valSrvc;

    @Inject
    DomainConverterImpl(final SimpleValSrvc vs)
    {
        this.valSrvc = vs;
    }
    
    @Override
    @CheckValid
    public DomainEntity to(final Domainable impl)
            throws DataAccessException
    {
        final DomainEntity entity = new DomainEntity();
        entity.setUuid(impl.getUuid().toString());
        setUpdateableFields(entity, impl);
        return entity;
    }
    
    @Override
    @CheckValid
    public void setUpdateableFields(final DomainEntity entity, final Domainable impl) 
            throws DataAccessException
    {
        entity.setLocator(impl.getLocator());
        entity.setSub(impl.getSub());
        entity.setAbbr(impl.getAbbr());
        entity.setFull(impl.getFull());
    }

    @Override
    @CheckValid
    public Domainable from(final DomainEntity entity)
            throws DataAccessException
    {        
        final String full = valSrvc.validateFromDB(
                Validation_Type.TITLE, entity.getFull());
        final String abbr = valSrvc.validateFromDB(
                Validation_Type.TITLE, entity.getAbbr());
        final String domain = valSrvc.validateFromDB(
                Validation_Type.DOMAIN, entity.getSub());
        final UUID id = valSrvc.validateUuidFromDB(entity.getUuid());

        return Domain.builder()
                .sub(domain)
                .uuid(id)
                .full(full)
                .abbr(abbr)
                .build();
    }

}
