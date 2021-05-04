package id.alinea.dms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.alinea.dms.service.IRepositoryLocator;
import id.alinea.dms.service.IServiceLocator;
import id.alinea.dms.service.IStorageService;
import id.alinea.dms.service.ISystemFileService;

@Service
public class ServiceLocator extends BaseService implements IServiceLocator {
    
    @Autowired
    private IStorageService storageService;

    @Autowired
    private ISystemFileService systemFileService;

    @Override
    public IStorageService getStorageService() {
        return storageService;
    }

    @Override
    public ISystemFileService getSystemFileService() {
        return systemFileService;
    } 
}
