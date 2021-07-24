package id.alinea.dms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.alinea.dms.service.IServiceLocator;
import id.alinea.dms.service.IStorageService;
import id.alinea.dms.service.IDbSystemFileService;
import id.alinea.dms.service.IImageKitService;

@Service
public class ServiceLocator extends BaseService implements IServiceLocator {
    
    @Autowired
    private IStorageService storageService;

    @Autowired
    private IDbSystemFileService systemFileService;

    @Autowired
    private IImageKitService imageKitService;

    @Override
    public IStorageService getStorageService() {
        return storageService;
    }

    @Override
    public IDbSystemFileService getSystemFileService() {
        return systemFileService;
    }

    @Override
    public IImageKitService getImageKitService() {
        return imageKitService;
    } 
}
