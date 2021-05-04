package id.alinea.dms.service;

import id.alinea.dms.repository.SystemFileRepository;
import id.alinea.dms.repository.WhitelistRepository;

public interface IRepositoryLocator {
    
    SystemFileRepository getSystemFileRepository();

    WhitelistRepository getWhitelistRepository();
}
