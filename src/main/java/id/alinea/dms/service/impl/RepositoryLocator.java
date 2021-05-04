package id.alinea.dms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.alinea.dms.repository.SystemFileRepository;
import id.alinea.dms.repository.WhitelistRepository;
import id.alinea.dms.service.IRepositoryLocator;

@Service
public class RepositoryLocator implements IRepositoryLocator {

    @Autowired
    private SystemFileRepository systemFileRepository;

    @Autowired
    private WhitelistRepository whitelistRepository;

    @Override
    public SystemFileRepository getSystemFileRepository() {
        return systemFileRepository;
    }

    @Override
    public WhitelistRepository getWhitelistRepository() {
        return whitelistRepository;
    }
    
}
