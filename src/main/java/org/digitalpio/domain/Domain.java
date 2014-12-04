package org.digitalpio.domain;

import com.google.common.base.Joiner;
import java.util.UUID;
import javax.annotation.Nonnull;
import lombok.NonNull;
import org.apache.commons.codec.digest.DigestUtils;
import org.digitalpio.commons.misc.Privacy;
import org.digitalpio.core.orgs.Domainable;

/**
 * The Domain class hold basic string data relating to an organization's 
 * domain (or namespaces).
 * @author Owen
 */
@javax.annotation.concurrent.Immutable
@org.checkthread.annotations.ThreadSafe
@lombok.Value
@lombok.experimental.Builder
public final class Domain implements Domainable
{
    @Nonnull @NonNull private final String sub;
    @Nonnull @NonNull private final UUID uuid;
    @Nonnull @NonNull private final String full;
    @Nonnull @NonNull private final String abbr;    
    
    @Override
    public String buildSearchString()
    {
        final String[] searches = {
            sub,
            full,
            abbr
        };
        return Joiner.on(" ").join(searches);
    }

    @Override
    public UUID key()
    {
        return getUuid();
    }

    @Override
    public String getLocator()
    {
        return DigestUtils.sha1Hex(getSub());
    }    

    @Override
    public Privacy getPrivacy()
    {
        return Privacy.PUBLIC;
    }

    @Override
    public boolean isPrivate()
    {
        return false;
    }
}
