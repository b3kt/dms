package id.alinea.dms.service;

public interface IServiceLocator {

    IStorageService getStorageService();

    ISystemFileService getSystemFileService(); 
    
}
