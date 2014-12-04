package org.digitalpio.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.digitalpio.commons.dao.LocatorAbstractEntity;
import org.digitalpio.commons.misc.Privacy;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Fields;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.bridge.builtin.EnumBridge;


@Entity
@Table(name = "domains")
@Indexed
@javax.annotation.concurrent.NotThreadSafe
@org.checkthread.annotations.NotThreadSafe
@lombok.Data
@lombok.EqualsAndHashCode(callSuper=true, of = {"sub"})
@NamedQueries({
    @NamedQuery(
            name = "DomainEntity.sub",
            query = "select e from DomainEntity e where e.sub = :subDomain"
    )
})
public class DomainEntity extends LocatorAbstractEntity
{    
    @NotNull
    @Fields({
        @Field(name = "full_standard_search", 
               index = Index.YES, analyze = Analyze.YES, store = Store.NO,
               analyzer = @Analyzer(definition = "dpiostandard")),
        @Field(name = "full_ngram_search", 
               index = Index.YES, analyze = Analyze.YES, store = Store.NO,
               analyzer = @Analyzer(definition = "dpiongram")),
        @Field(name = "full_edgengram_search", 
               index = Index.YES, analyze = Analyze.YES, store = Store.NO,
               analyzer = @Analyzer(definition = "dpioedgengram"))
    })    
    @Column(name = "full_name", nullable = false, updatable = true, unique=true)
    private String full;
    
    @NotNull
    @Fields({
        @Field(name = "abbr_standard_search", 
               index = Index.YES, analyze = Analyze.YES, store = Store.NO,
               analyzer = @Analyzer(definition = "dpiostandard")),
        @Field(name = "abbr_ngram_search", 
               index = Index.YES, analyze = Analyze.YES, store = Store.NO,
               analyzer = @Analyzer(definition = "dpiongram")),
        @Field(name = "abbr_edgengram_search", 
               index = Index.YES, analyze = Analyze.YES, store = Store.NO,
               analyzer = @Analyzer(definition = "dpioedgengram"))
    })    
    @Column(name = "abbr_name", nullable = false, updatable = true)
    private String abbr;
    
    @NotNull
    @Fields({
        @Field(name = "sub_standard_search", 
               index = Index.YES, analyze = Analyze.YES, store = Store.NO,
               analyzer = @Analyzer(definition = "dpiostandard")),
        @Field(name = "sub_ngram_search", 
               index = Index.YES, analyze = Analyze.YES, store = Store.NO,
               analyzer = @Analyzer(definition = "dpiongram")),
        @Field(name = "sub_edgengram_search", 
               index = Index.YES, analyze = Analyze.YES, store = Store.NO,
               analyzer = @Analyzer(definition = "dpioedgengram"))
    })    
    @Pattern(regexp="^[a-z]{1,64}$")
    @Column(name = "sub_domain", nullable = false, updatable = true, unique=true)
    private String sub;
        
    @Field(
            bridge=@FieldBridge(impl=EnumBridge.class), 
            index = Index.YES, analyze = Analyze.NO, store = Store.NO
    )
    @Column(name="privacy", updatable=true, unique=false, nullable=false)
    @Enumerated(EnumType.STRING)
    private Privacy privacy;
}
