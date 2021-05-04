package id.alinea.dms.config;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import id.alinea.dms.entity.Whitelist;
import id.alinea.dms.repository.WhitelistRepository;

@Component
public class CustomIpAuthenticationProvider implements AuthenticationProvider {

    private Logger logger = LoggerFactory.getLogger(CustomIpAuthenticationProvider.class);

    @Autowired
    WhitelistRepository whitelistRepository;

    public CustomIpAuthenticationProvider() {
        // whitelist.add("0:0:0:0:0:0:0:1");
    }

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        WebAuthenticationDetails details = (WebAuthenticationDetails) auth.getDetails();
        String userIp = details.getRemoteAddress();
        logger.info("Access from ip {} ", userIp);

        List<Whitelist> whitelists = whitelistRepository.findByType("ip_address");
        if (whitelists.stream().anyMatch(ip -> ip.getValue().equals("*"))) {
            return auth;
        } else if (!whitelists.stream().anyMatch(ip -> ip.getValue().equals(userIp))) {
            return auth;
        } else {
            throw new BadCredentialsException("Not Authorized!");
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}